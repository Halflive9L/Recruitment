package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.ImportException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Named
public class ImportProspectsUseCase implements ImportProspects {
    private CreateProspect createProspect;

    public ImportProspectsUseCase(CreateProspect createProspect) {
        this.createProspect = createProspect;
    }

    @Override
    public void importProspects(ImportProspectsRequest request, Consumer<ImportProspectsResponseModel> consumer) {
        List<ImportProspectsFailure> failures = new ArrayList<>();
        List<ProspectResponseModel> successes = new ArrayList<>();
        CSVParser parsed;
        ImportProspectsResponseModel response;
        try (Reader reader = new InputStreamReader(request.getStream())) {
            parsed = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : parsed) {
                processRecord(successes, failures, record);
            }
            response = new ImportProspectsResponseModel(failures, successes);
            consumer.accept(response);
        }
        catch (IOException ex) {
            throw new ImportException();
        }
    }

    private void processRecord(List<ProspectResponseModel> success, List<ImportProspectsFailure> fails, CSVRecord rec) {
        CreateProspectRequest request = createRequest(rec);
        try {
            createProspect.createProspect(request, resp -> {
                success.add(resp);
            });
        } catch (InvalidEmailException ex) {
            fails.add(new ImportProspectsFailure("Invalid e-mail", formatRecord(rec)));
        } catch (InvalidPhoneException ex) {
            fails.add(new ImportProspectsFailure("Invalid phone number", formatRecord(rec)));
        }
    }

    private String formatRecord(CSVRecord record) {
        StringBuilder sb = new StringBuilder();
        record.forEach(col -> {
            sb.append(col);
            sb.append(",");
        });
        return sb.toString();
    }

    private CreateProspectRequest createRequest(CSVRecord record) {
        String firstName = getOrNull(record, 0);
        String lastName = getOrNull(record, 1);
        String email = getOrNull(record, 2);
        String phoneNr = getOrNull(record, 3);

        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = firstName;
        request.lastName = lastName;
        request.email = email;
        request.phone = phoneNr;
        return request;
    }

    private String getOrNull(CSVRecord record, int i) {
        if (record.size() <= i) {
            return null;
        }
        String s = record.get(i).trim();
        return s.isEmpty() ? null : s;
    }
}

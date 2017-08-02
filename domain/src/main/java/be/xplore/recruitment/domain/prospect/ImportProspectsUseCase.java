package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.apache.commons.csv.CSVFormat;
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
        try {
            List<ImportProspectsFailure> failures = new ArrayList<>();
            List<ProspectResponseModel> successes = new ArrayList<>();
            Reader reader = new InputStreamReader(request.getStream());
            for (CSVRecord record : CSVFormat.DEFAULT.parse(reader)) {
                processRecord(successes, failures, record);
            }
            ImportProspectsResponseModel response = new ImportProspectsResponseModel(failures, successes);
            consumer.accept(response);
        } catch (IOException ex) {
            // TODO: afhandelen, eigen ex throwne
            System.out.println(ex);
        }
    }

    private void processRecord(List<ProspectResponseModel> successes, List<ImportProspectsFailure> failures, CSVRecord record) {
        CreateProspectRequest request = createRequest(record);
        try {
            createProspect.createProspect(request, resp -> {
                successes.add(resp);
            });
        } catch (InvalidEmailException ex) {
            failures.add(new ImportProspectsFailure("Invalid e-mail", record.toString()));
        } catch (InvalidPhoneException ex) {
            failures.add(new ImportProspectsFailure("Invalid phone number", record.toString()));
        }
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

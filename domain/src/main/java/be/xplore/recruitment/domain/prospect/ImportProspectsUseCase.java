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
import java.util.function.Consumer;

@Named
public class ImportProspectsUseCase implements ImportProspects {
    private CreateProspect createProspect;

    public ImportProspectsUseCase(CreateProspect createProspect) {
        this.createProspect = createProspect;
    }

    @Override
    public void importProspects(ImportProspectsRequest request, Consumer<ImportProspectsResponseModel> consumer) {
        ImportProspectsResponseModel response = new ImportProspectsResponseModel();
        tryProcessCsv(request, response);
        consumer.accept(response);
    }

    private void tryProcessCsv(ImportProspectsRequest request, ImportProspectsResponseModel response) {
        try (Reader reader = new InputStreamReader(request.getStream())) {
            processCsv(response, reader);
        } catch (IOException ex) {
            throw new ImportException();
        }
    }

    private void processCsv(ImportProspectsResponseModel response, Reader reader)
            throws IOException {
        CSVParser parsed = CSVFormat.DEFAULT.parse(reader);
        for (CSVRecord record : parsed) {
            processRecord(response, record);
        }
    }

    private void processRecord(ImportProspectsResponseModel response, CSVRecord rec) {
        CreateProspectRequest request = createRequest(rec);
        tryCreateProspect(response, rec, request);
    }

    private void tryCreateProspect(ImportProspectsResponseModel response, CSVRecord rec,
                                   CreateProspectRequest request) {
        try {
            createProspect.createProspect(request, response.getProspects()::add);
        } catch (InvalidEmailException ex) {
            response.getFailed().add(new ImportProspectsFailure("Invalid e-mail", formatRecord(rec)));
        } catch (InvalidPhoneException ex) {
            response.getFailed().add(new ImportProspectsFailure("Invalid phone number", formatRecord(rec)));
        }
    }

    private String formatRecord(CSVRecord record) {
        StringBuilder sb = new StringBuilder();
        record.forEach(col -> {
            sb.append(col).append(",");
        });
        return sb.toString();
    }

    private CreateProspectRequest createRequest(CSVRecord record) {
        return CreateProspectRequest.builder()
                .withFirstName(getOrNull(record, 0))
                .withLastName(getOrNull(record, 1))
                .withPhone(getOrNull(record, 3))
                .withEmail(getOrNull(record, 2))
                .build();
    }

    private String getOrNull(CSVRecord record, int i) {
        if (record.size() <= i) {
            return null;
        }
        String s = record.get(i).trim();
        return s.isEmpty() ? null : s;
    }
}

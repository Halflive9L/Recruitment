package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ImportProspectsResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

public class JsonProspectImportResponseModelPresenter implements Consumer<ImportProspectsResponseModel> {
    private ResponseEntity<JsonImportResult> responseEntity;

    @Override
    public void accept(ImportProspectsResponseModel importProspectsResponseModel) {
        JsonImportResult body = JsonImportResult.asJsonImportResult(importProspectsResponseModel);
        responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<JsonImportResult> getResponseEntity() {
        return responseEntity;
    }
}

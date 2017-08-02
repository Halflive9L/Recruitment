package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.FileResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class CreateFilePresenter implements Consumer<FileResponseModel> {
    private ResponseEntity<JsonFile> responseEntity;

    @Override
    public void accept(FileResponseModel file) {
        JsonFile jsonFile = new JsonFile();
        jsonFile.setFileName(file.getFileName());
        responseEntity = new ResponseEntity<>(jsonFile, HttpStatus.CREATED);
    }

    ResponseEntity<JsonFile> getResponseEntity() {
        return responseEntity;
    }
}

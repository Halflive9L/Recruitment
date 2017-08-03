package be.xplore.recruitment.web.applicant.file;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class GetAllFilesForApplicantPresenter implements Consumer<List<File>> {
    private ResponseEntity<List<JsonFile>> responseEntity;

    @Override
    public void accept(List<File> files) {
        List<JsonFile> jsonFiles = new ArrayList<>(files.size());
        files.forEach(file -> {
            JsonFile jsonFile = new JsonFile();
            jsonFile.setFileName(file.getName());
            jsonFiles.add(jsonFile);
        });
        responseEntity = new ResponseEntity<>(jsonFiles, HttpStatus.OK);
    }

    ResponseEntity<List<JsonFile>> getResponseEntity() {
        return responseEntity;
    }
}

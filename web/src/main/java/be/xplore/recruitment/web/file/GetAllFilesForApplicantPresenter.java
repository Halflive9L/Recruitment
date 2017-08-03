package be.xplore.recruitment.web.file;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class GetAllFilesForApplicantPresenter implements Consumer<List<String>> {
    private ResponseEntity<List<JsonFile>> responseEntity;

    @Override
    public void accept(List<String> fileNames) {
        List<JsonFile> jsonFiles = new ArrayList<>(fileNames.size());
        fileNames.forEach(fileName -> {
            JsonFile jsonFile = new JsonFile();
            jsonFile.setFileName(fileName);
            jsonFiles.add(jsonFile);
        });
        responseEntity = new ResponseEntity<>(jsonFiles, HttpStatus.OK);
    }

    ResponseEntity<List<JsonFile>> getResponseEntity() {
        return responseEntity;
    }
}

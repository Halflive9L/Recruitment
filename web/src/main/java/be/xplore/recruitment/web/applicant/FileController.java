package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.CreateFile;
import be.xplore.recruitment.domain.applicant.CreateFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@RestController
public class FileController {

    private final CreateFile createFile;

    @Autowired
    public FileController(CreateFile createFile) {
        this.createFile = createFile;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/applicant/{applicantId}",
            consumes = "multipart/form-data")
    public ResponseEntity<JsonFile> uploadFile(@PathVariable long applicantId, @RequestParam("file") MultipartFile file)
            throws IOException {
        System.out.println(applicantId);
        CreateFileRequest request = new CreateFileRequest(applicantId, file.getInputStream());
        CreateFilePresenter presenter = new CreateFilePresenter();
        createFile.createFile(request, presenter);
        return presenter.getResponseEntity();
    }
}

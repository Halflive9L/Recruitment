package be.xplore.recruitment.web.applicant.file;

import be.xplore.recruitment.domain.applicant.file.CreateFile;
import be.xplore.recruitment.domain.applicant.file.CreateFileRequest;
import be.xplore.recruitment.domain.applicant.file.DownloadFileRequest;
import be.xplore.recruitment.domain.applicant.file.GetAllFilesForApplicantRequest;
import be.xplore.recruitment.domain.applicant.file.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@RestController
public class FileController {

    private final CreateFile createFile;
    private final ReadFile readFile;

    @Autowired
    public FileController(CreateFile createFile, ReadFile readFile) {
        this.createFile = createFile;
        this.readFile = readFile;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/applicant/{applicantId}/file",
            consumes = "multipart/form-data")
    public ResponseEntity<JsonFile> uploadFile(@PathVariable long applicantId,
                                               @RequestParam("file") MultipartFile file)
            throws IOException {
        CreateFileRequest request = getCreateFileRequest(applicantId, file);
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        CreateFilePresenter presenter = new CreateFilePresenter();
        createFile.createFile(request, presenter);
        return presenter.getResponseEntity();
    }

    private CreateFileRequest getCreateFileRequest(long applicantId, MultipartFile file) throws IOException {
        CreateFileRequest request = new CreateFileRequest();
        request.setInput(file.getInputStream());
        request.setApplicantId(applicantId);
        System.out.println("file name: " + file.getOriginalFilename());
        request.setExtension(getExtension(file.getOriginalFilename()));
        return request;
    }

    private String getExtension(String fileName) {
        String ext = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            ext = fileName.substring(i);
        }
        System.out.println("file ext: " + ext);
        return ext;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applicant/{applicantId}/files")
    public ResponseEntity<List<JsonFile>> getAllFilesForApplicant(@PathVariable long applicantId) {
        GetAllFilesForApplicantRequest request = new GetAllFilesForApplicantRequest(applicantId);
        GetAllFilesForApplicantPresenter presenter = new GetAllFilesForApplicantPresenter();
        readFile.readAllFilesForApplicant(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applicant/{applicantId}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("applicantId") long applicantId,
                                                          @RequestParam("file") String fileName) throws IOException {
        DownloadFileRequest request = new DownloadFileRequest(applicantId, fileName);
        DownloadFilePresenter presenter = new DownloadFilePresenter();

        readFile.downloadFile(request, presenter);

        return presenter.getResponseEntity();
    }
}

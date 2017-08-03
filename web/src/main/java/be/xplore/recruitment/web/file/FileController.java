package be.xplore.recruitment.web.file;

import be.xplore.recruitment.domain.file.CreateFile;
import be.xplore.recruitment.domain.file.CreateFileRequest;
import be.xplore.recruitment.domain.file.DownloadFileRequest;
import be.xplore.recruitment.domain.file.ReadAllFilesForApplicantRequest;
import be.xplore.recruitment.domain.file.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
        CreateFilePresenter presenter = new CreateFilePresenter();
        createFile.createFile(request, presenter);
        return presenter.getResponseEntity();
    }

    private CreateFileRequest getCreateFileRequest(long applicantId, MultipartFile file) throws IOException {
        CreateFileRequest request = new CreateFileRequest();
        request.setInput(file.getInputStream());
        request.setApplicantId(applicantId);

        request.setExtension(getExtension(file.getOriginalFilename()));
        return request;
    }

    private String getExtension(String fileName) {
        String ext = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            ext = fileName.substring(i);
        }

        return ext;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applicant/{applicantId}/files")
    public ResponseEntity<List<JsonFile>> getAllFilesForApplicant(@PathVariable long applicantId) {
        ReadAllFilesForApplicantRequest request = new ReadAllFilesForApplicantRequest(applicantId);
        GetAllFilesForApplicantPresenter presenter = new GetAllFilesForApplicantPresenter();
        readFile.readAllFilesForApplicant(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applicant/{applicantId}/{fileName:.+}")
    public void downloadFile(@PathVariable("applicantId") long applicantId,
                             @PathVariable("fileName") String fileName,
                             HttpServletResponse response)
            throws IOException {
        System.out.println(fileName);
        DownloadFileRequest request = new DownloadFileRequest(applicantId, fileName, response.getOutputStream());
        DownloadFilePresenter presenter = new DownloadFilePresenter();
        readFile.downloadFile(request, presenter);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + presenter.getFileName().replace(" ", "_"));

        response.flushBuffer();
    }
}

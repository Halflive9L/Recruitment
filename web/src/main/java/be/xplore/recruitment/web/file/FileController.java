package be.xplore.recruitment.web.file;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@RestController
public class FileController {
  /*
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
    }*/
}

package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class UploadFileResponseModel {
    private String fileName;

    public UploadFileResponseModel(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

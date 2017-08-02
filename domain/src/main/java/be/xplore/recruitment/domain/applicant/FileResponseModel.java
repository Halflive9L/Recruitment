package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class FileResponseModel {
    private String fileName;

    public FileResponseModel(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

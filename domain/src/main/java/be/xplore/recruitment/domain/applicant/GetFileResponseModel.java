package be.xplore.recruitment.domain.applicant;

import java.io.File;

/**
 * @author Stijn Schack
 * @since 8/3/2017
 */
public class GetFileResponseModel {
    private File file;
    private String contentType;

    public GetFileResponseModel(File file, String contentType) {
        this.file = file;
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public File getFile() {
        return file;
    }
}

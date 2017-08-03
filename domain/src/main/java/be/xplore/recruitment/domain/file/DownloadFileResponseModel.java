package be.xplore.recruitment.domain.file;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Stijn Schack
 * @since 8/3/2017
 */
public class DownloadFileResponseModel {
    private InputStream inputStream;
    private String fileName;
    private OutputStream outputStream;

    public DownloadFileResponseModel(InputStream inputStream, String fileName, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public String getFileName() {
        return fileName;
    }
}

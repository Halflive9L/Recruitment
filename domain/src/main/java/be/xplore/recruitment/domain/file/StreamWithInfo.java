package be.xplore.recruitment.domain.file;

import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/3/2017
 */
public class StreamWithInfo {
    private InputStream inputStream;
    private String fileName;

    public StreamWithInfo(InputStream inputStream, String fileName) {
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getFileName() {
        return fileName;
    }
}

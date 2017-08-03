package be.xplore.recruitment.domain.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public interface FileRepository {
    File createFile(long applicantId, InputStream input, String extension) throws IOException;

    Optional<List<String>> readAllFiles(long applicantId);

    Optional<StreamWithInfo> downloadFile(long applicantId, String fileName) throws IOException;
}

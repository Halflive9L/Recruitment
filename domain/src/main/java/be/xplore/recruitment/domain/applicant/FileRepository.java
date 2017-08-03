package be.xplore.recruitment.domain.applicant;

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

    Optional<List<File>> readAllFiles(long applicantId);

    Optional<File> downloadFile(long applicantId, String fileName) throws IOException;
}

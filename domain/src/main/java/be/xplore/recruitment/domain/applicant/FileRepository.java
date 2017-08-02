package be.xplore.recruitment.domain.applicant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public interface FileRepository {
    File createFile(long applicantId, InputStream input) throws IOException;
}

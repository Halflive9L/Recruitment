package be.xplore.recruitment.domain.applicant;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public interface CreateFile {
    void createFile(CreateFileRequest request, Consumer<FileResponseModel> response) throws IOException;
}

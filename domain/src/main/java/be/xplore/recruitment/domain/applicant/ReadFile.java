package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public interface ReadFile {
    void readAllFilesForApplicant(GetAllFilesForApplicantRequest request, Consumer<List<File>> response)
            throws NotFoundException;

    void downloadFile(DownloadFileRequest request, Consumer<GetFileResponseModel> response) throws IOException;
}

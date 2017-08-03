package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@Named
public class ReadFileUseCase implements ReadFile {

    private FileRepository repository;

    public ReadFileUseCase(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void readAllFilesForApplicant(GetAllFilesForApplicantRequest request,
                                         Consumer<List<File>> response) throws NotFoundException {
        List<File> files = repository.readAllFiles(request.getApplicantId()).orElseThrow(NotFoundException::new);
        response.accept(files);
    }

    @Override
    public void downloadFile(DownloadFileRequest request, Consumer<GetFileResponseModel> response) throws IOException {
        File file = repository.downloadFile(request.getApplicantId(), request.getFileName())
                .orElseThrow(NotFoundException::new);
        String contentType = Files.probeContentType(file.toPath());
        System.out.println(contentType);
        response.accept(new GetFileResponseModel(file, contentType));
    }
}

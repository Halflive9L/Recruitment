package be.xplore.recruitment.domain.applicant.file;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.io.IOException;
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
    public void readAllFilesForApplicant(ReadAllFilesForApplicantRequest request,
                                         Consumer<List<String>> response) throws NotFoundException {
        List<String> files = repository.readAllFiles(request.getApplicantId()).orElseThrow(NotFoundException::new);
        response.accept(files);
    }

    @Override
    public void downloadFile(DownloadFileRequest request, Consumer<DownloadFileResponseModel> response) throws IOException {
        StreamWithInfo stream = repository.downloadFile(request.getApplicantId(), request.getFileName())
                .orElseThrow(() -> new NotFoundException("File with name: " + request.getFileName()
                        + " does not exist."));
        response.accept(new DownloadFileResponseModel(stream.getInputStream(),
                stream.getFileName(), request.getResponseStream()));
    }
}

package be.xplore.recruitment.domain.applicant.file;

import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@Named
class CreateFileUseCase implements CreateFile{
    private FileRepository repository;

    public CreateFileUseCase(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createFile(CreateFileRequest request, Consumer<UploadFileResponseModel> response) throws IOException {
        File file = repository.createFile(request.getApplicantId(), request.getInput(), request.getExtension());
        response.accept(new UploadFileResponseModel(file.getName()));
    }
}

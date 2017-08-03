package be.xplore.recruitment.domain.applicant.file;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@Named
class CreateFileUseCase implements CreateFile {
    private final FileRepository fileRepository;
    private final ApplicantRepository applicantRepository;

    public CreateFileUseCase(FileRepository fileRepository, ApplicantRepository applicantRepository) {
        this.fileRepository = fileRepository;
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void createFile(CreateFileRequest request, Consumer<UploadFileResponseModel> response)
            throws IOException, NotFoundException {
        throwExceptionIfApplicantDoesNotExist(request);
        File file = fileRepository.createFile(request.getApplicantId(), request.getInput(), request.getExtension());
        request.getInput().close();
        response.accept(new UploadFileResponseModel(file.getName()));
    }

    private void throwExceptionIfApplicantDoesNotExist(CreateFileRequest request) throws NotFoundException,
            IOException {
        if (!applicantRepository.findApplicantById(request.getApplicantId()).isPresent()) {
            throw new NotFoundException("Applicant with id: '" + request.getApplicantId() + "' does not exist.");
        }
    }
}

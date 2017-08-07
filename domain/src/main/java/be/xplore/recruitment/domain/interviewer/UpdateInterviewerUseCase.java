package be.xplore.recruitment.domain.interviewer;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

@Named
public class UpdateInterviewerUseCase implements UpdateInterviewer {
    private final InterviewerRepository repository;

    public UpdateInterviewerUseCase(InterviewerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateInterviewer(UpdateInterviewerRequest request, Consumer<InterviewerResponseModel> consumer) {
        Interviewer updated = Interviewer.builder()
                .withInterviewerId(request.getInterviewerId())
                .withFirstName(request.getFirstName())
                .withLastName(request.getLastName())
                .build();
        Interviewer interviewer = repository.updateInterviewer(updated).orElseThrow(NotFoundException::new);
        consumer.accept(new InterviewerResponseModel(interviewer));
    }
}

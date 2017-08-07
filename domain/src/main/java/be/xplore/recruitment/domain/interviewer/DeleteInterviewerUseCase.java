package be.xplore.recruitment.domain.interviewer;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

@Named
public class DeleteInterviewerUseCase implements DeleteInterviewer {
    private final InterviewerRepository repository;

    public DeleteInterviewerUseCase(InterviewerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteInterviewer(DeleteInterviewerRequest request, Consumer<InterviewerResponseModel> consumer) {
        Interviewer result = repository.deleteInterviewer(request.getInterviewerId())
                .orElseThrow(NotFoundException::new);
        consumer.accept(new InterviewerResponseModel(result));
    }
}

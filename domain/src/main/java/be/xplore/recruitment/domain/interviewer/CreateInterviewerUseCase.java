package be.xplore.recruitment.domain.interviewer;

import javax.inject.Named;
import java.util.function.Consumer;

@Named
public class CreateInterviewerUseCase implements CreateInterviewer {
    private final InterviewerRepository repository;

    public CreateInterviewerUseCase(InterviewerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createInterviewer(CreateInterviewerRequest request, Consumer<InterviewerResponseModel> response) {
        Interviewer interviewer = Interviewer.builder()
                .withFirstName(request.getFirstName())
                .withLastName(request.getLastName())
                .build();
        // TODO: validation
        Interviewer result = repository.createInterviewer(interviewer);
        response.accept(new InterviewerResponseModel(result));
    }
}

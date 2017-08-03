package be.xplore.recruitment.domain.interviewer;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Named
public class ReadInterviewerUseCase implements ReadInterviewer {
    private final InterviewerRepository repository;

    public ReadInterviewerUseCase(InterviewerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void readInterviewerById(ReadInterviewerRequest request, Consumer<InterviewerResponseModel> consumer) {
        Optional<Interviewer> result = repository.findById(request.getInterviewerId());
        Interviewer interviewer = result.orElseThrow(NotFoundException::new);
        consumer.accept(new InterviewerResponseModel(interviewer));
    }

    @Override
    public void readAll(Consumer<List<InterviewerResponseModel>> consumer) {
        List<InterviewerResponseModel> result = repository.findAll()
                .stream()
                .map(InterviewerResponseModel::new)
                .collect(Collectors.toList());
        consumer.accept(result);
    }
}

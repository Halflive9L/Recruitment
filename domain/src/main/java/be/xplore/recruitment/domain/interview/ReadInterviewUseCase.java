package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Named
public class ReadInterviewUseCase implements ReadInterview {
    private final InterviewRepository repository;

    public ReadInterviewUseCase(InterviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public void readInterview(ReadInterviewRequest request, Consumer<InterviewResponseModel> consumer) {
        Interview interview = repository.findById(request.getInterviewId())
                .orElseThrow(NotFoundException::new);
        consumer.accept(InterviewResponseModel.fromInterview(interview));
    }

    @Override
    public void readAll(Consumer<List<InterviewResponseModel>> consumer) {
        List<InterviewResponseModel> responseModels = repository.findAll().stream()
                .map(InterviewResponseModel::fromInterview)
                .collect(Collectors.toList());
        consumer.accept(responseModels);
    }
}

package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

@Named
public class CancelInterviewUseCase implements CancelInterview {
    private final InterviewRepository repository;

    public CancelInterviewUseCase(InterviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cancelInterview(CancelInterviewRequest request, Consumer<InterviewResponseModel> consumer) {
        Interview interview = repository.findById(request.getInterviewId()).orElseThrow(NotFoundException::new);
        interview.setCancelled(!interview.isCancelled());
        interview = repository.updateInterview(interview).orElseThrow(NotFoundException::new);
        consumer.accept(InterviewResponseModel.fromInterview(interview));
    }
}

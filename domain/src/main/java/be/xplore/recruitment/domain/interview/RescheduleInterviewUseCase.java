package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.exception.InvalidSchedulingException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

@Named
public class RescheduleInterviewUseCase implements RescheduleInterview {
    private final InterviewRepository repository;

    public RescheduleInterviewUseCase(InterviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public void reschedule(RescheduleInterviewRequest request, Consumer<InterviewResponseModel> consumer) {
        Interview interview = repository.findById(request.getInterviewId()).orElseThrow(NotFoundException::new);
        checkRequestValidity(request);
        updateDomainObject(request, interview);
        consumer.accept(InterviewResponseModel.fromInterview(
                repository.updateInterview(interview).orElseThrow(RuntimeException::new)));
    }

    private void updateDomainObject(RescheduleInterviewRequest request, Interview interview) {
        if (request.getNewLocation() != null) {
            interview.setLocation(request.getNewLocation());
        }
        if (request.getNewScheduledTime() != null) {
            interview.setScheduledTime(request.getNewScheduledTime());
        }
    }

    private void checkRequestValidity(RescheduleInterviewRequest request) {
        if (request.getNewLocation() == null && request.getNewScheduledTime() == null) {
            throw new InvalidSchedulingException();
        }
    }
}

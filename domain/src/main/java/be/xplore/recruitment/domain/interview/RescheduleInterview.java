package be.xplore.recruitment.domain.interview;

import java.util.function.Consumer;

public interface RescheduleInterview {
    void reschedule(RescheduleInterviewRequest request, Consumer<InterviewResponseModel> consumer );
}

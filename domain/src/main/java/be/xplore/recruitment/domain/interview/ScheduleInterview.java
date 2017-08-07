package be.xplore.recruitment.domain.interview;

import java.util.function.Consumer;

public interface ScheduleInterview {
    void scheduleInterview(ScheduleInterviewRequest request, Consumer<InterviewResponseModel> consumer);
}

package be.xplore.recruitment.domain.interview;

import java.util.function.Consumer;

public interface CancelInterview {
    void cancelInterview(CancelInterviewRequest request, Consumer<InterviewResponseModel> consumer);
}

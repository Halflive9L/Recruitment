package be.xplore.recruitment.domain.interview;

import java.util.function.Consumer;

public interface ReadInterview {
    void readInterview(ReadInterviewRequest request, Consumer<InterviewResponseModel> consumer);
}

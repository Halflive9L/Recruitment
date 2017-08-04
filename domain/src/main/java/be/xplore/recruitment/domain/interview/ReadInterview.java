package be.xplore.recruitment.domain.interview;

import java.util.List;
import java.util.function.Consumer;

public interface ReadInterview {
    void readInterview(ReadInterviewRequest request, Consumer<InterviewResponseModel> consumer);
    void readAll(Consumer<List<InterviewResponseModel>> consumer);
}

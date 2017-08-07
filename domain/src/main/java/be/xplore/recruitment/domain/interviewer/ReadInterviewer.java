package be.xplore.recruitment.domain.interviewer;

import java.util.List;
import java.util.function.Consumer;

public interface ReadInterviewer {
    void readInterviewerById(ReadInterviewerRequest request, Consumer<InterviewerResponseModel> consumer);

    void readAll(Consumer<List<InterviewerResponseModel>> consumer);
}

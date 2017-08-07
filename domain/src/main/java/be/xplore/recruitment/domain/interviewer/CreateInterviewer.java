package be.xplore.recruitment.domain.interviewer;

import java.util.function.Consumer;

public interface CreateInterviewer {
    void createInterviewer(CreateInterviewerRequest request, Consumer<InterviewerResponseModel> response);
}

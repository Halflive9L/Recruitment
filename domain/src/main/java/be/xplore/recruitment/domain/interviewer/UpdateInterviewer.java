package be.xplore.recruitment.domain.interviewer;

import java.util.function.Consumer;

public interface UpdateInterviewer {
    void updateInterviewer(UpdateInterviewerRequest request, Consumer<InterviewerResponseModel> consumer);
}

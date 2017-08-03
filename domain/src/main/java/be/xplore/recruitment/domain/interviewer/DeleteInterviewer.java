package be.xplore.recruitment.domain.interviewer;

import java.util.function.Consumer;

public interface DeleteInterviewer {
    void deleteInterviewer(DeleteInterviewerRequest request, Consumer<InterviewerResponseModel> consumer);
}

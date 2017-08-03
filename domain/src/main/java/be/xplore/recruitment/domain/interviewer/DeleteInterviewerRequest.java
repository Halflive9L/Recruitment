package be.xplore.recruitment.domain.interviewer;

public class DeleteInterviewerRequest {
    private long interviewerId;

    public DeleteInterviewerRequest(long interviewerId) {
        this.interviewerId = interviewerId;
    }

    public long getInterviewerId() {
        return interviewerId;
    }
}

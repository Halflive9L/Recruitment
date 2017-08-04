package be.xplore.recruitment.domain.interview;

public class CancelInterviewRequest {
    private long interviewId;

    public CancelInterviewRequest(long interviewId) {
        this.interviewId = interviewId;
    }

    public long getInterviewId() {
        return interviewId;
    }
}

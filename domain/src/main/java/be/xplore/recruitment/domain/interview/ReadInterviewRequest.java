package be.xplore.recruitment.domain.interview;

public class ReadInterviewRequest {
    private long interviewId;

    public ReadInterviewRequest(long interviewId) {
        this.interviewId = interviewId;
    }

    public long getInterviewId() {
        return interviewId;
    }
}

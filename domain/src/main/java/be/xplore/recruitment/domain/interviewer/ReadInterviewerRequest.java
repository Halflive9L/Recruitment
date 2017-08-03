package be.xplore.recruitment.domain.interviewer;

public class ReadInterviewerRequest {
    private long interviewerId;

    public ReadInterviewerRequest(long interviewerId) {
        this.interviewerId = interviewerId;
    }

    public long getInterviewerId() {
        return interviewerId;
    }
}

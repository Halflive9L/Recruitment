package be.xplore.recruitment.domain.interview;

public class RemindParticipantsRequest {
    private long interviewId;

    public RemindParticipantsRequest(long interviewId) {
        this.interviewId = interviewId;
    }

    public long getInterviewId() {
        return interviewId;
    }
}

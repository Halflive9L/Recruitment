package be.xplore.recruitment.domain.interview;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public class UpdateInterviewLocationRequest {
    private long interviewId;
    private String location;

    public UpdateInterviewLocationRequest(long interviewId, String location) {
        this.interviewId = interviewId;
        this.location = location;
    }

    long getInterviewId() {
        return interviewId;
    }

    String getLocation() {
        return location;
    }
}

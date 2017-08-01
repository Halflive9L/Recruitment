package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public class DeleteApplicantRequest {
    public final long id;

    public DeleteApplicantRequest(long id) {
        this.id = id;
    }
}

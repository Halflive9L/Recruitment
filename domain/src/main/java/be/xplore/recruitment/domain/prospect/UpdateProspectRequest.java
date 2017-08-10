package be.xplore.recruitment.domain.prospect;

/**
 * @author Lander
 * @since 27/07/2017
 */
public class UpdateProspectRequest {
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    private final long prospectId;

    public UpdateProspectRequest(long prospectId) {
        this.prospectId = prospectId;
    }

    long getProspectId() {
        return prospectId;
    }
}

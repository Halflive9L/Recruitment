package be.xplore.recruitment.domain.prospect;

/**
 * @author Lander
 * @since 27/07/2017
 */
public class UpdateProspectRequest {
    public long prospectId;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;

    @Override
    public String toString() {
        return "UpdateProspectRequest{" +
                "prospectId=" + prospectId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

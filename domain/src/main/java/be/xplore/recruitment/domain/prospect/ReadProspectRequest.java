package be.xplore.recruitment.domain.prospect;

/**
 * @author Lander
 * @since 27/07/2017
 */
public class ReadProspectRequest {
    public long prospectId;
    public String firstName;
    public String lastName;
    public String phone;
    public String email;

    Prospect toProspect(){
        return Prospect.builder(firstName, lastName)
                .withEmail(email)
                .withPhone(phone)
                .build();
    }

}

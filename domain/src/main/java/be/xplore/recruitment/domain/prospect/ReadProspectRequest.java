package be.xplore.recruitment.domain.prospect;

import java.util.Collections;

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

    Prospect toProspect() {
        return Prospect.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPhone(phone)
                .withTags(Collections.emptySet())
                .build();
    }

}

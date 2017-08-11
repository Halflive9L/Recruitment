package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.tag.Tag;

import java.util.Set;

public final class ProspectBuilder {
    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<Tag> tags;

    private ProspectBuilder() {
    }

    public static ProspectBuilder aProspect() {
        return new ProspectBuilder();
    }

    public ProspectBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ProspectBuilder withProspectId(long id) {
        this.prospectId = id;
        return this;
    }

    public ProspectBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ProspectBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ProspectBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ProspectBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Prospect build() {
        Prospect prospect = new Prospect();
        prospect.setFirstName(firstName);
        prospect.setLastName(lastName);
        prospect.setEmail(email);
        prospect.setPhone(phone);
        prospect.setProspectId(prospectId);
        prospect.setTags(tags);
        return prospect;
    }
}

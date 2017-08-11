package be.xplore.recruitment.persistence.prospect;

import be.xplore.recruitment.persistence.tag.JpaTag;

import java.util.Set;

public final class JpaProspectBuilder {
    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<JpaTag> tags;

    private JpaProspectBuilder() {
    }

    public static JpaProspectBuilder aJpaProspect() {
        return new JpaProspectBuilder();
    }

    public JpaProspectBuilder withProspectId(long prospectId) {
        this.prospectId = prospectId;
        return this;
    }

    public JpaProspectBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public JpaProspectBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public JpaProspectBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public JpaProspectBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    JpaProspectBuilder withTags(Set<JpaTag> tags) {
        this.tags = tags;
        return this;
    }

    public JpaProspect build() {
        JpaProspect jpaProspect = new JpaProspect();
        jpaProspect.setProspectId(prospectId);
        jpaProspect.setFirstName(firstName);
        jpaProspect.setLastName(lastName);
        jpaProspect.setEmail(email);
        jpaProspect.setPhone(phone);
        jpaProspect.setTags(tags);
        return jpaProspect;
    }
}

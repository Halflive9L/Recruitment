package be.xplore.recruitment.web.prospect;

import java.util.Set;

public final class JsonProspectBuilder {
    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<String> tags;

    private JsonProspectBuilder() {
    }

    public static JsonProspectBuilder aJsonProspect() {
        return new JsonProspectBuilder();
    }

    public JsonProspectBuilder withProspectId(long prospectId) {
        this.prospectId = prospectId;
        return this;
    }

    public JsonProspectBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public JsonProspectBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public JsonProspectBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public JsonProspectBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public JsonProspectBuilder withTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public JsonProspect build() {
        JsonProspect jsonProspect = new JsonProspect();
        jsonProspect.setProspectId(prospectId);
        jsonProspect.setFirstName(firstName);
        jsonProspect.setLastName(lastName);
        jsonProspect.setEmail(email);
        jsonProspect.setPhone(phone);
        jsonProspect.setTags(tags);
        return jsonProspect;
    }
}

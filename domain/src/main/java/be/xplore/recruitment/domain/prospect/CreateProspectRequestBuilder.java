package be.xplore.recruitment.domain.prospect;

public final class CreateProspectRequestBuilder {
    public long prospectId;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;

    private CreateProspectRequestBuilder() {
    }

    public static CreateProspectRequestBuilder aCreateProspectRequest() {
        return new CreateProspectRequestBuilder();
    }

    public CreateProspectRequestBuilder withProspectId(long prospectId) {
        this.prospectId = prospectId;
        return this;
    }

    public CreateProspectRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateProspectRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateProspectRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateProspectRequestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CreateProspectRequest build() {
        CreateProspectRequest createProspectRequest = new CreateProspectRequest();
        createProspectRequest.setProspectId(prospectId);
        createProspectRequest.setFirstName(firstName);
        createProspectRequest.setLastName(lastName);
        createProspectRequest.setEmail(email);
        createProspectRequest.setPhone(phone);
        return createProspectRequest;
    }
}

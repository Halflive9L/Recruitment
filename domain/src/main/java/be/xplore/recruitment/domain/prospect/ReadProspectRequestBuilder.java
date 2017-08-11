package be.xplore.recruitment.domain.prospect;

public final class ReadProspectRequestBuilder {
    public long prospectId;
    public String firstName;
    public String lastName;
    public String phone;
    public String email;

    private ReadProspectRequestBuilder() {
    }

    public static ReadProspectRequestBuilder aReadProspectRequest() {
        return new ReadProspectRequestBuilder();
    }

    public ReadProspectRequestBuilder withProspectId(long prospectId) {
        this.prospectId = prospectId;
        return this;
    }

    public ReadProspectRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ReadProspectRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ReadProspectRequestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ReadProspectRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ReadProspectRequest build() {
        ReadProspectRequest readProspectRequest = new ReadProspectRequest();
        readProspectRequest.setProspectId(prospectId);
        readProspectRequest.setFirstName(firstName);
        readProspectRequest.setLastName(lastName);
        readProspectRequest.setPhone(phone);
        readProspectRequest.setEmail(email);
        return readProspectRequest;
    }
}

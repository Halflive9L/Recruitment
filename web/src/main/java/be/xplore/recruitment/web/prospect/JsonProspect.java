package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.CreateProspectRequest;
import be.xplore.recruitment.domain.prospect.ProspectResponseModel;
import be.xplore.recruitment.domain.prospect.ReadProspectRequest;
import be.xplore.recruitment.domain.prospect.UpdateProspectRequest;
import be.xplore.recruitment.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@JsonComponent class JsonProspect implements Serializable {
    private static final long serialVersionUID = -1155194210930940184L;
    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<String> tags;

    @JsonCreator
    public JsonProspect() {
    }

    public static JsonProspect asJsonProspect(ProspectResponseModel responseModel) {
        return JsonProspectBuilder.aJsonProspect()
                .withProspectId(responseModel.getProspectId())
                .withFirstName(responseModel.getFirstName())
                .withLastName(responseModel.getLastName())
                .withPhone(responseModel.getPhone())
                .withEmail(responseModel.getEmail())
                .withTags(responseModel.getTags().stream().map(Tag::getTagName).collect(Collectors.toSet()))
                .build();
    }

    public ReadProspectRequest toReadRequest() {
        ReadProspectRequest request = new ReadProspectRequest();
        setReadRequestProperties(request);
        return request;
    }

    private void setReadRequestProperties(ReadProspectRequest request) {
        request.firstName = this.getFirstName();
        request.lastName = this.getLastName();
        request.email = this.getEmail();
        request.phone = this.getPhone();
    }

    public UpdateProspectRequest toUpdateRequest(long prospectId) {
        UpdateProspectRequest request = new UpdateProspectRequest(prospectId);
        setUpdateRequestProperties(request);
        return request;
    }

    private void setUpdateRequestProperties(UpdateProspectRequest request) {
        request.firstName = this.getFirstName();
        request.lastName = this.getLastName();
        request.email = this.getEmail();
        request.phone = this.getPhone();
    }

    public CreateProspectRequest toCreateRequest() {
        CreateProspectRequest request = new CreateProspectRequest();
        setCreateRequestProperties(request);
        return request;
    }

    private void setCreateRequestProperties(CreateProspectRequest request) {
        request.firstName = this.getFirstName();
        request.lastName = this.getLastName();
        request.email = this.getEmail();
        request.phone = this.getPhone();
    }

    @JsonProperty
    public long getProspectId() {
        return prospectId;
    }

    @JsonProperty
    public void setProspectId(long prospectId) {
        this.prospectId = prospectId;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public String getPhone() {
        return phone;
    }

    @JsonProperty
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty
    public Set<String> getTags() {
        return tags;
    }

    @JsonProperty
    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @JsonIgnore
    boolean isEmpty() {
        return firstName == null
                && lastName == null
                && email == null
                && phone == null;
    }

    @Override
    public String toString() {
        return "JsonProspect{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
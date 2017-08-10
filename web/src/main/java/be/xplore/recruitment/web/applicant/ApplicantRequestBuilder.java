package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.CreateApplicantRequest;
import be.xplore.recruitment.domain.applicant.ReadApplicantRequest;
import be.xplore.recruitment.domain.applicant.UpdateApplicantRequest;

public class ApplicantRequestBuilder {
    public static CreateApplicantRequest buildCreateRequest(JsonApplicant applicant) {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        return request;
    }

    public static ReadApplicantRequest buildReadRequest(JsonApplicant applicant) {
        ReadApplicantRequest request = new ReadApplicantRequest();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        return request;
    }

    public static UpdateApplicantRequest buildUpdateRequest(long id, JsonApplicant applicant) {
        UpdateApplicantRequest request = new UpdateApplicantRequest(id);
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        return request;
    }
}

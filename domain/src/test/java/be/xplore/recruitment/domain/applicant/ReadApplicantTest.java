package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public class ReadApplicantTest {
    private ReadApplicant useCase;

    private List<Applicant> mockApplicants;

    @Before
    public void initUseCase() {
        MockApplicantRepo repository = new MockApplicantRepo();
        mockApplicants = repository.mockApplicants;
        useCase = new ReadApplicantUseCase(repository);
    }

    @Test
    public void testReadAllApplicants() {
        List<Applicant> applicantResponse = new ArrayList<>();
        useCase.readAllApplicants(response -> {
            applicantResponse.addAll(getApplicantListFromResponseModelList(response));
        });
        assertThat(mockApplicants, is(applicantResponse));
    }

    @Test
    public void testReadApplicantById() {
        ReadApplicantRequest request = getRequestFromApplicant(Applicant.builder().withId(1).build());
        final Applicant[] responseApplicant = new Applicant[1];
        useCase.readApplicantById(request, applicantResponseModel -> {
            responseApplicant[0] = getApplicantFromApplicantResponseModel(applicantResponseModel);
        });
        assertEquals(responseApplicant[0], mockApplicants.get(0));
    }

    @Test(expected = NotFoundException.class)
    public void testReadApplicantById_IdDoesNotExist() {
        ReadApplicantRequest request = getRequestFromApplicant(Applicant.builder().withId(500).build());
        useCase.readApplicantById(request, applicantResponseModel -> {
        });
    }

    /*
        @Test
        public void testReadApplicantsByParam() {
            ReadApplicantRequest request = getRequestFromApplicant(Applicant.builder().withFirstName("leeroy").build());
            List<Applicant> applicantResponse = new ArrayList<>();
            useCase.readApplicantById(request, response -> {
                applicantResponse.addAll(getApplicantListFromResponseModelList(response));
            });
            assertEquals(applicantResponse.get(0), mockApplicants.get(1));
        }
    */
    @Ignore
    private ReadApplicantRequest getRequestFromApplicant(Applicant applicant) {
        ReadApplicantRequest request = new ReadApplicantRequest();
        request.applicantId = applicant.getApplicantId();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        return request;
    }

    @Ignore
    private List<Applicant> getApplicantListFromResponseModelList(List<ApplicantResponseModel> responseModels) {
        List<Applicant> applicants = new ArrayList<>();
        for (ApplicantResponseModel responseModel : responseModels) {
            applicants.add(getApplicantFromApplicantResponseModel(responseModel));
        }

        return applicants;
    }

    @Ignore
    private Applicant getApplicantFromApplicantResponseModel(ApplicantResponseModel responseModel) {
        return Applicant.builder()
                .withId(responseModel.getApplicantId())
                .withFirstName(responseModel.getFirstName())
                .withLastName(responseModel.getLastName())
                .withAddress(responseModel.getAddress())
                .withEducation(responseModel.getEducation())
                .withEmail(responseModel.getEmail())
                .withDateOfBirth(responseModel.getDateOfBirth())
                .withPhone(responseModel.getPhone())
                .build();
    }
}

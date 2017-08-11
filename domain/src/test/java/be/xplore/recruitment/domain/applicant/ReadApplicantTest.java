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
        final Applicant[] responseApplicant = new Applicant[1];
        useCase.readApplicantById(getRequestFromApplicant(Applicant.builder().withApplicantId(1).build()), resp -> {
            responseApplicant[0] = getApplicantFromApplicantResponseModel(resp);
        });
        assertEquals(responseApplicant[0], mockApplicants.get(0));
    }

    @Test(expected = NotFoundException.class)
    public void testReadApplicantById_IdDoesNotExist() {
        ReadApplicantRequest request = getRequestFromApplicant(Applicant.builder().withApplicantId(500).build());
        useCase.readApplicantById(request, applicantResponseModel -> {
        });
    }

    @Test
    public void testReadApplicantsByParam() {
        ReadApplicantRequest request = getRequestFromApplicant(Applicant.builder().withFirstName("leeroy").build());
        useCase.readApplicantsByParam(request, responseModel -> {
           assertEquals(1, responseModel.size());
           assertEquals(mockApplicants.get(1), getApplicantFromApplicantResponseModel(responseModel.get(0)));
        });
    }

    @Ignore
    private ReadApplicantRequest getRequestFromApplicant(Applicant applicant) {
        return ReadApplicantRequestBuilder.aReadApplicantRequest()
                .withApplicantId(applicant.getApplicantId())
                .withAddress(applicant.getAddress())
                .withDateOfBirth(applicant.getDateOfBirth())
                .withEducation(applicant.getEducation())
                .withFirstName(applicant.getFirstName())
                .withLastName(applicant.getLastName())
                .withEmail(applicant.getEmail())
                .withPhone(applicant.getPhone())
                .build();
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
                .withApplicantId(responseModel.getApplicantId())
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

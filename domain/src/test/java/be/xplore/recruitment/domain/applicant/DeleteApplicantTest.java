package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class DeleteApplicantTest {
    private DeleteApplicant useCase;
    private List<Applicant> mockApplicants;

    @Before
    public void initUseCase() {
        MockApplicantRepo repository = new MockApplicantRepo();
        mockApplicants = repository.mockApplicants;
        useCase = new DeleteApplicantUseCase(repository);
    }

    @Test
    public void testDeleteApplicantTest() {
        useCase.deleteApplicant(new DeleteApplicantRequest(1), applicantId -> {
        });
        assertEquals(1, mockApplicants.size());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNonExistingApplicantTest() {
        useCase.deleteApplicant(new DeleteApplicantRequest(500), applicantId -> {
        });
    }
}

package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class DeleteApplicantTest {
    private DeleteApplicant useCase;
    private Applicant[] mockApplicants;

    @Before
    public void initUseCase() {
        MockApplicantRepo repository = new MockApplicantRepo();
        mockApplicants = repository.mockApplicants;
        useCase = new DeleteApplicantUseCase(repository);
    }

    @Test
    public void testDeleteApplicantTest() {
        useCase.deleteApplicant(1);
        assertNull(mockApplicants[0]);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNonExistingApplicantTest() {
        useCase.deleteApplicant(500);
    }
}

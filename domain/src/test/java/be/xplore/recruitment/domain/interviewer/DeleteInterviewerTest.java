package be.xplore.recruitment.domain.interviewer;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DeleteInterviewerTest {
    private MockInterviewerRepo repository;
    private DeleteInterviewer useCase;

    private List<Interviewer> seed = Arrays.asList(
            new Interviewer(1, "Casandra", "Kleinveld"),
            new Interviewer(2, "Tyra", "van Vlymen"),
            new Interviewer(3, "Jitte", "Slotboom"));

    @Before
    public void setup() {
        repository = new MockInterviewerRepo(seed);
        useCase = new DeleteInterviewerUseCase(repository);
    }

    @Test
    public void deletesInterviewer() {
        useCase.deleteInterviewer(new DeleteInterviewerRequest(1), response -> {
            assertResponseEquals(seed.get(0), response);
            assertThat(repository.findById(1).isPresent(), is(false));
        });
    }

    @Test(expected = NotFoundException.class)
    public void deleteNonExistent() {
        useCase.deleteInterviewer(new DeleteInterviewerRequest(5), response -> {
        });
    }

    private void assertResponseEquals(Interviewer interviewer, InterviewerResponseModel response) {
        assertThat(interviewer.getFirstName(), is(response.getFirstName()));
        assertThat(interviewer.getLastName(), is(response.getLastName()));
    }
}

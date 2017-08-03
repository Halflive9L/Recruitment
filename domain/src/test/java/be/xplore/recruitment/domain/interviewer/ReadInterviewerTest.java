package be.xplore.recruitment.domain.interviewer;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ReadInterviewerTest {
    private MockInterviewerRepo repository;
    private ReadInterviewer useCase;

    private List<Interviewer> seed = Arrays.asList(
            new Interviewer(1, "Casandra", "Kleinveld"),
            new Interviewer(2, "Tyra", "van Vlymen"),
            new Interviewer(3, "Jitte", "Slotboom"));

    @Before
    public void setup() {
        repository = new MockInterviewerRepo(seed);
        useCase = new ReadInterviewerUseCase(repository);
    }

    @Test
    public void readsById() {
        useCase.readInterviewerById(new ReadInterviewerRequest(1), response -> {
            assertResponseEquals(seed.get(0), response);
        });
    }

    @Test
    public void readsAll() {
        useCase.readAll(response -> {
            assertThat(response.size(), is(seed.size()));
            for (int i = 0; i < seed.size(); i++) {
                assertResponseEquals(seed.get(i), response.get(i));
            }
        });
    }

    @Test(expected = NotFoundException.class )
    public void readsNonExistent() {
        useCase.readInterviewerById(new ReadInterviewerRequest(9999), response -> {});
    }

    private void assertResponseEquals(Interviewer interviewer, InterviewerResponseModel response) {
        assertThat(interviewer.getFirstName(), is(response.getFirstName()));
        assertThat(interviewer.getLastName(), is(response.getLastName()));
    }
}

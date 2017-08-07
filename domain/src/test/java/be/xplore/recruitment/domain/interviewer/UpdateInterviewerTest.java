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
public class UpdateInterviewerTest {
    private MockInterviewerRepo repository;
    private UpdateInterviewer useCase;

    private List<Interviewer> seed = Arrays.asList(
            new Interviewer(1, "Casandra", "Kleinveld"),
            new Interviewer(2, "Tyra", "van Vlymen"),
            new Interviewer(3, "Jitte", "Slotboom"));

    @Before
    public void setup() {
        repository = new MockInterviewerRepo(seed);
        useCase = new UpdateInterviewerUseCase(repository);
    }

    @Test
    public void updates() {
        UpdateInterviewerRequest request = UpdateInterviewerRequest.builder()
                .withFirstName("Lies")
                .withLastName("Achten")
                .withInterviewerId(1)
                .withEmail("lies.achten@email.com")
                .build();
        useCase.updateInterviewer(request, response -> {
            assertThat(response.getFirstName(), is(request.getFirstName()));
            assertThat(response.getLastName(), is(request.getLastName()));
            assertThat(response.getInterviewerId(), is(request.getInterviewerId()));

            Interviewer persisted = repository.findById(1).get();
            assertThat(persisted.getFirstName(), is(response.getFirstName()));
            assertThat(persisted.getLastName(), is(response.getLastName()));
        });
    }

    @Test(expected = NotFoundException.class)
    public void updateNonExistent() {
        UpdateInterviewerRequest request = UpdateInterviewerRequest.builder()
                .withFirstName("Lies")
                .withLastName("Achten")
                .withInterviewerId(99999)
                .withEmail("lies.achten@email.com")
                .build();
        useCase.updateInterviewer(request, response -> {
        });
    }

    private void assertResponseEquals(Interviewer interviewer, InterviewerResponseModel response) {
        assertThat(interviewer.getFirstName(), is(response.getFirstName()));
        assertThat(interviewer.getLastName(), is(response.getLastName()));
    }
}

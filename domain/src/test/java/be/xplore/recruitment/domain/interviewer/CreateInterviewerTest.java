package be.xplore.recruitment.domain.interviewer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class CreateInterviewerTest {
    private MockInterviewerRepo repository;
    private CreateInterviewer useCase;

    @Before
    public void setup() {
        repository = new MockInterviewerRepo();
        useCase = new CreateInterviewerUseCase(repository);
    }

    @Test
    public void createsInterviewer() {
        CreateInterviewerRequest request = new CreateInterviewerRequest("Maarten", "Billiet");
        useCase.createInterviewer(request, response -> {
            assertThat(response.getFirstName(), is("Maarten"));
            assertThat(response.getLastName(), is("Billiet"));
            Interviewer interviewer = repository.findById(response.getInterviewerId()).get();
            assertThat(interviewer.getFirstName(), is("Maarten"));
            assertThat(interviewer.getLastName(), is("Billiet"));
        });
    }
}

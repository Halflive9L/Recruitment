package be.xplore.recruitment.domain.interviewer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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
        CreateInterviewerRequest request = CreateInterviewerRequest.builder()
                .withFirstName("Maarten")
                .withLastName("Billiet")
                .withEmail("mb@email.com")
                .build();
        useCase.createInterviewer(request, response -> {
            assertResponseMatchesRequest(response, request);
            assertInterviewerPersisted(response, request);
        });
    }

    private void assertInterviewerPersisted(InterviewerResponseModel response, CreateInterviewerRequest request) {
        Interviewer interviewer = repository.findById(response.getInterviewerId()).get();
        assertThat(interviewer.getFirstName(), is(request.getFirstName()));
        assertThat(interviewer.getLastName(), is(request.getLastName()));
        assertThat(interviewer.getEmail(), is(request.getEmail()));
    }

    private void assertResponseMatchesRequest(InterviewerResponseModel response, CreateInterviewerRequest request) {
        assertThat(response.getFirstName(), is(request.getFirstName()));
        assertThat(response.getLastName(), is(request.getLastName()));
        assertThat(response.getEmail(), is(request.getEmail()));
    }
}

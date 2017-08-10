package be.xplore.recruitment.domain.interview;


import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CancelInterviewTest {
    @Mock
    private InterviewRepository mockRepo;

    private CancelInterview useCase;

    @Before
    public void setup() {
        mockRepo = new MockInterviewRepo(Arrays.asList(
                Interview.builder()
                        .withApplicant(Applicant.builder()
                                .withApplicantId(1)
                                .withFirstName("Maarten")
                                .build())
                        .withInterviewId(1)
                        .withInterviewers(Arrays.asList(
                                Interviewer.builder()
                                        .withInterviewerId(1)
                                        .build(),
                                Interviewer.builder()
                                        .withInterviewerId(3)
                                        .build()
                        ))
                        .build()
        ));
        useCase = new CancelInterviewUseCase(mockRepo);
    }

    @Test
    public void cancelsInterview() {
        useCase.cancelInterview(new CancelInterviewRequest(1), response -> {
            assertThat(response.isCancelled(), is(true));
        });
    }

    @Test
    public void uncancelInterview() {
        useCase.cancelInterview(new CancelInterviewRequest(1), response -> {
        });
        useCase.cancelInterview(new CancelInterviewRequest(1), response -> {
            assertThat(response.isCancelled(), is(false));
        });
    }

    @Test(expected = NotFoundException.class)
    public void cancelNonExistent() {
        useCase.cancelInterview(new CancelInterviewRequest(999999), response -> {
        });
    }
}

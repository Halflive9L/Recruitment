package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class ReadInterviewTest {
    @Mock
    private InterviewRepository mockInterviewRepo;

    private ReadInterview useCase;

    @Before
    public void setup() {
        this.mockInterviewRepo = new MockInterviewRepo(Arrays.asList(
                Interview.builder()
                        .withApplicant(Applicant.builder()
                                .withId(1)
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
                        .build(),
                Interview.builder()
                        .withApplicant(Applicant.builder()
                                .withId(2)
                                .withFirstName("sqf")
                                .build())
                        .withInterviewId(2)
                        .withInterviewers(Arrays.asList(
                                Interviewer.builder()
                                        .withInterviewerId(2)
                                        .build()
                        ))
                        .build()
        ));
        useCase = new ReadInterviewUseCase(mockInterviewRepo);
    }

    @Test
    public void readsById() {
        useCase.readInterview(new ReadInterviewRequest(1), response -> {
            assertThat(response.getApplicantId(), is(1L));
            assertThat(response.getInterviewId(), is(1L));
            assertThat(response.getInterviewerIds(), is(Arrays.asList(1L, 3L)));
        });
    }

    @Test(expected = NotFoundException.class)
    public void readNonExistent() {
        useCase.readInterview(new ReadInterviewRequest(99999), response -> {
        });
    }

    @Test
    public void readAll() {
        useCase.readAll(response -> {
            assertThat(response.size(), is(2));
        });
    }
}

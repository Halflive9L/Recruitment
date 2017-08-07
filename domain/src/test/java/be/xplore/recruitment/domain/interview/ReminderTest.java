package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class ReminderTest {
    @Mock
    private MockInterviewRepo mockInterviewRepo;

    @Before
    private void setup() {
        mockInterviewRepo = new MockInterviewRepo(Arrays.asList(
                Interview.builder()
                    .withInterviewId(1)
                    .withCancelled(false)
                    .withPreInterviewReminderSent(false)
                    .withCreatedTime(LocalDateTime.now().minusDays(3))
                    .withScheduledTime(LocalDateTime.now().plusHours(12))
                    .withApplicant(Applicant.builder()
                            .withEmail("test@test.com")
                            .build())
                    .withInterviewers(Arrays.asList(
                        new Interviewer(1, "Maarten", "Billiet")
                    ))
                    .build()
        ));
    }

    @Test
    public void onlyInterviewsAfterCutoff() {
    }

    @Test
    public void ignoreInterviewsWithReminderAlreadySent() {

    }

    @Test
    public void remindsApplicant() {

    }

    @Test
    public void remindsInterviewers() {

    }
}

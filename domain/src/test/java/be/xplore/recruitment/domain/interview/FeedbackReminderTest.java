package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackReminderTest {
    private long nextId;
    private RemindInterviewersFeedback useCase;
    private InterviewRepository repo;
    private ReminderSender reminderSender;
    private List<Applicant> applicants = Arrays.asList(
            Applicant.builder()
                    .withApplicantId(1)
                    .withEmail("CaseyStarrenburg@dayrep.com")
                    .withFirstName("Casey")
                    .withLastName("Starrenburg")
                    .build(),
            Applicant.builder()
                    .withApplicantId(2)
                    .withEmail("CorneevanDoorne@teleworm.us")
                    .withFirstName("Cornee")
                    .withLastName("van Doorne")
                    .build()
    );
    private List<Interviewer> interviewers = Arrays.asList(
            Interviewer.builder()
                    .withInterviewerId(1)
                    .withFirstName("Cyrelle")
                    .withLastName("IJsseldijk")
                    .build(),
            Interviewer.builder()
                    .withInterviewerId(2)
                    .withFirstName("Clazina")
                    .withLastName("van Hoorn")
                    .build()
    );

    @Before
    public void setup() {
        nextId = 1;
        reminderSender = mock(ReminderSender.class);
    }

    @Test
    public void testDoesntSendTooEarly() {
        useCase = setupUseCase(reminderSender, interviewBuilder(12, applicants.get(0), interviewers)
                .withFeedbackReminderSent(false)
                .build());
        useCase.remind();
        assertReminderSent(1, false);
    }

    private void assertReminderSent(long interviewId, boolean sent) {
        Interview interview = repo.findById(interviewId).get();
        assertThat(interview.isFeedbackReminderSent(), is(sent));
    }


    @Test
    public void testRemindsOnce() {
        useCase = setupUseCase(reminderSender, interviewBuilder(60, applicants.get(0), interviewers)
                .withFeedbackReminderSent(true)
                .build());
        useCase.remind();
        verify(reminderSender, times(0)).remindInterviewer(any(), any(), any());
    }

    @Test
    public void testOnlyRemindsInterviewers() {
        useCase = setupUseCase(reminderSender, interviewBuilder(60, applicants.get(0), interviewers)
                .withFeedbackReminderSent(false)
                .build());
        useCase.remind();
        verify(reminderSender, times(0)).remindApplicant(any(), any(), any());
    }

    @Test
    public void testSendsReminder() {
        useCase = setupUseCase(reminderSender, interviewBuilder(60, applicants.get(0), interviewers)
                .withFeedbackReminderSent(false)
                .build());
        useCase.remind();
        verify(reminderSender, times(interviewers.size())).remindInterviewer(any(), any(), any());
        assertReminderSent(1, true);
    }

    @Test
    public void testDontRemindCancelledInterviews() {
        useCase = setupUseCase(reminderSender, interviewBuilder(60, applicants.get(0), interviewers)
                .withFeedbackReminderSent(false)
                .withCancelled(true)
                .build());
        useCase.remind();
        assertReminderSent(1, false);
    }

    private RemindInterviewersFeedback setupUseCase(ReminderSender sender, Interview... interviews) {
        List<Interview> list = Arrays.asList(interviews);
        repo = new MockInterviewRepo(list);
        return new RemindInterviewersFeedbackUseCase(repo, sender);
    }

    private InterviewBuilder interviewBuilder(long hoursSince, Applicant applicant,
                                              List<Interviewer> interviewers) {
        return Interview.builder()
                .withInterviewId(nextId++)
                .withScheduledTime(LocalDateTime.now().minusHours(hoursSince))
                .withCreatedTime(LocalDateTime.now().minusDays(5))
                .withApplicant(applicant)
                .withInterviewers(interviewers);
    }
}

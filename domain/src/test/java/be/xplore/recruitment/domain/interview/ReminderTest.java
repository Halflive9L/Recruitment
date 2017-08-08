package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class ReminderTest {
    @Mock
    private MockInterviewRepo mockInterviewRepo;
    private RemindParticipants useCase;

    private List<Applicant> applicants = Arrays.asList(
            Applicant.builder()
                    .withId(1)
                    .withEmail("CaseyStarrenburg@dayrep.com")
                    .withFirstName("Casey")
                    .withLastName("Starrenburg")
                    .build(),
            Applicant.builder()
                    .withId(2)
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

    private InterviewRepository setupRepo(Interview... interviews) {
        List<Interview> list = Arrays.asList(interviews);
        return new MockInterviewRepo(list);
    }

    private RemindParticipants setupUseCase(ReminderSender sender, Interview... interviews) {
        List<Interview> list = Arrays.asList(interviews);
        mockInterviewRepo = new MockInterviewRepo(list);
        return new RemindParticipantsUseCase(mockInterviewRepo, sender);
    }

    private long nextId;

    private Interview.InterviewBuilder interviewBuilder(long inHours, Applicant applicant,
                                                        List<Interviewer> interviewers) {
        return Interview.builder()
                .withInterviewId(nextId++)
                .withScheduledTime(LocalDateTime.now().plusHours(inHours))
                .withCreatedTime(LocalDateTime.now().minusDays(5))
                .withApplicant(applicant)
                .withInterviewers(interviewers);
    }

    @Before
    public void setup() {
        nextId = 1;
    }

    @Test
    public void onlyInterviewsAfterCutoff() {
        ReminderSender sender = mock(ReminderSender.class);
        useCase = setupUseCase(
                sender,
                interviewBuilder(12, applicants.get(0), interviewers).build(),
                interviewBuilder(50, applicants.get(0), interviewers).build(),
                interviewBuilder(100, applicants.get(0), interviewers).build()
        );
        useCase.checkReminders();
        verify(sender, times(1)).remindApplicant(any(), any());
    }

    @Test
    public void reminderSentFlagUpdated() {
        ReminderSender sender = mock(ReminderSender.class);
        useCase = setupUseCase(
                sender,
                interviewBuilder(12, applicants.get(0), interviewers).build()
        );
        Interview interview = mockInterviewRepo.findById(1).get();
        assertThat(interview.isPreInterviewReminderSent(), is(false));
        useCase.checkReminders();
        interview = mockInterviewRepo.findById(1).get();
        assertThat(interview.isPreInterviewReminderSent(), is(true));
    }

    @Test
    public void ignoreCancelledInterviews() {
        ReminderSender sender = mock(ReminderSender.class);
        useCase = setupUseCase(
                sender,
                interviewBuilder(12, applicants.get(0), interviewers)
                        .withCancelled(true)
                        .build()
        );
        useCase.checkReminders();
        verify(sender, times(0)).remindApplicant(any(), any());
        verify(sender, times(0)).remindInterviewer(any(), any());
    }

    @Test
    public void ignoreInterviewsWithReminderAlreadySent() {
        ReminderSender sender = mock(ReminderSender.class);
        useCase = setupUseCase(
                sender,
                interviewBuilder(12, applicants.get(0), interviewers)
                        .withPreInterviewReminderSent(true)
                        .build()
        );
        useCase.checkReminders();
        verify(sender, times(0)).remindApplicant(any(), any());
        verify(sender, times(0)).remindInterviewer(any(), any());
    }

    @Test
    public void remindsApplicant() {
        Applicant applicant = applicants.get(0);
        ReminderSender sender = mock(ReminderSender.class);
        useCase = setupUseCase(
                sender,
                interviewBuilder(12, applicant, interviewers).build()
        );
        useCase.checkReminders();
        verify(sender, times(1)).remindApplicant(any(), any());
    }

    @Test
    public void remindsInterviewers() {
        Applicant applicant = applicants.get(0);
        ReminderSender sender = mock(ReminderSender.class);
        useCase = setupUseCase(
                sender,
                interviewBuilder(12, applicant, interviewers).build()
        );
        useCase.checkReminders();
        verify(sender, times(interviewers.size())).remindInterviewer(any(), any());
    }

}

package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.exception.InvalidSchedulingException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class RescheduleInterviewTest {
    private MockInterviewRepo mockInterviewRepo;
    private RescheduleInterview useCase;

    @Before
    public void setup() {
        mockInterviewRepo = new MockInterviewRepo(Arrays.asList(
                Interview.builder()
                        .withInterviewId(1)
                        .withPreInterviewReminderSent(false)
                        .withCancelled(false)
                        .withApplicant(Applicant.builder()
                                .withApplicantId(1)
                                .withFirstName("Maarten")
                                .withLastName("Billiet")
                                .build())
                        .withCreatedTime(LocalDateTime.now())
                        .withScheduledTime(LocalDateTime.now().plusDays(1))
                        .withLocation("Old Location")
                        .withInterviewers(Arrays.asList(
                                Interviewer.builder()
                                        .withFirstName("Casandra")
                                        .withLastName("Kleinveld")
                                        .withEmail("casandra.kleinveld@email.com")
                                        .withInterviewerId(1)
                                        .build()))
                        .build()
        ));
        useCase = new RescheduleInterviewUseCase(mockInterviewRepo);
    }

    @Test(expected = NotFoundException.class)
    public void rescheduleNonExistent() {
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(9999)
                .withNewLocation("New Location")
                .withNewScheduledTime(LocalDateTime.now().plusDays(5))
                .build();
        useCase.reschedule(request, response -> {
        });
    }

    @Test
    public void rescheduleTimeOnly() {
        LocalDateTime newScheduledTime = LocalDateTime.now().plusDays(5);
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(1)
                .withNewScheduledTime(newScheduledTime)
                .build();
        useCase.reschedule(request, response -> {
            assertThat(response.getInterviewId(), is(1L));
            assertThat(response.getScheduledTime(), is(newScheduledTime));
            assertThat(response.getLocation(), is("Old Location"));
            assertThat(response.getApplicantId(), is(1L));
        });
    }

    @Test
    public void rescheduleLocationOnly() {
        LocalDateTime scheduledTime = mockInterviewRepo.findById(1).get().getScheduledTime();
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(1)
                .withNewLocation("New Location")
                .build();
        useCase.reschedule(request, response -> {
            assertThat(response.getInterviewId(), is(1L));
            assertThat(response.getScheduledTime(), is(scheduledTime));
            assertThat(response.getLocation(), is("New Location"));
            assertThat(response.getApplicantId(), is(1L));
        });
    }

    @Test
    public void rescheduleLocationAndTime() {
        LocalDateTime newScheduledTime = LocalDateTime.now().plusDays(5);
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(1)
                .withNewLocation("New Location")
                .withNewScheduledTime(newScheduledTime)
                .build();
        useCase.reschedule(request, response -> {
            assertThat(response.getInterviewId(), is(1L));
            assertThat(response.getLocation(), is("New Location"));
            assertThat(response.getScheduledTime(), is(newScheduledTime));
            assertThat(response.getApplicantId(), is(1L));
        });
    }

    @Test(expected = InvalidSchedulingException.class)
    public void rescheduleNoLocationOrTime() {
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(1)
                .build();
        useCase.reschedule(request, response -> {});
    }
}

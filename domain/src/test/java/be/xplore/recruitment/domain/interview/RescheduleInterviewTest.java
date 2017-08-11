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
        useCase.reschedule(request, response -> verifyResponse(response, InterviewResponseModelBuilder
                .anInterviewResponseModel()
                .withInterviewId(1L)
                .withScheduledTime(newScheduledTime)
                .withLocation("Old Location")
                .withApplicantId(1L)
                .build()));
    }

    private void verifyResponse(InterviewResponseModel response, InterviewResponseModel expected) {
        assertThat(response.getInterviewId(), is(expected.getInterviewId()));
        assertThat(response.getScheduledTime(), is(expected.getScheduledTime()));
        assertThat(response.getLocation(), is(expected.getLocation()));
        assertThat(response.getApplicantId(), is(expected.getApplicantId()));
    }

    @Test
    public void rescheduleLocationOnly() {
        LocalDateTime scheduledTime = mockInterviewRepo.findById(1).get().getScheduledTime();
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(1)
                .withNewLocation("New Location")
                .build();
        useCase.reschedule(request, response -> InterviewResponseModelBuilder.anInterviewResponseModel()
                .withInterviewId(1L)
                .withScheduledTime(scheduledTime)
                .withLocation("New Location")
                .withApplicantId(1L)
                .build());
    }

    @Test
    public void rescheduleLocationAndTime() {
        LocalDateTime newScheduledTime = LocalDateTime.now().plusDays(5);
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(1)
                .withNewLocation("New Location")
                .withNewScheduledTime(newScheduledTime)
                .build();
        useCase.reschedule(request, response -> InterviewResponseModelBuilder.anInterviewResponseModel()
                .withInterviewId(1L)
                .withLocation("New Location")
                .withScheduledTime(newScheduledTime)
                .withApplicantId(1L)
                .build());
    }

    @Test(expected = InvalidSchedulingException.class)
    public void rescheduleNoLocationOrTime() {
        RescheduleInterviewRequest request = RescheduleInterviewRequest.builder()
                .withInterviewId(1)
                .build();
        useCase.reschedule(request, response -> {
        });
    }
}

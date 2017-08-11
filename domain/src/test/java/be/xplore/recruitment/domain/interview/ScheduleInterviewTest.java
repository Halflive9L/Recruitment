package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.applicant.MockApplicantRepo;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import be.xplore.recruitment.domain.interviewer.MockInterviewerRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@RunWith(MockitoJUnitRunner.class)
public class ScheduleInterviewTest {
    @Mock
    private MockInterviewRepo mockInterviewRepo;
    @Mock
    private MockInterviewerRepo mockInterviewerRepo;
    @Mock
    private MockApplicantRepo mockApplicantRepo;

    private ScheduleInterview useCase;

    @Before
    public void setup() {
        setupRepos();
        useCase = new ScheduleInterviewUseCase(mockInterviewerRepo, mockInterviewRepo, mockApplicantRepo);
    }

    private void setupRepos() {
        mockInterviewerRepo = new MockInterviewerRepo(Arrays.asList(
                new Interviewer(1, "Casandra", "Kleinveld"),
                new Interviewer(2, "Tyra", "van Vlymen"),
                new Interviewer(3, "Jitte", "Slotboom")
        ));
        mockApplicantRepo = new MockApplicantRepo();
        mockApplicantRepo.createApplicant(Applicant.builder()
                .withFirstName("Maarten")
                .withLastName("Billiet")
                .withEmail("mbilliet@email.com")
                .withAddress("Asffqsdf 123")
                .withEducation("Blah")
                .withPhone("+320496123123")
                .build());
        mockInterviewRepo = new MockInterviewRepo();
    }

    @Test
    public void schedulesInterview() {
        ScheduleInterviewRequest request = ScheduleInterviewRequest.builder()
                .withApplicantId(1)
                .withInterviewerIds(Arrays.asList(1L, 3L))
                .build();
        useCase.scheduleInterview(request, response -> {
            verifyResponse(response);
            verifyInterview();
        });
    }

    private void verifyInterview() {
        Interview interview = mockInterviewRepo.findById(1).get();
        assertThat(interview.getInterviewId(), is(1L));
        assertThat(interview.getApplicant().getApplicantId(), is(1L));
    }

    private void verifyResponse(InterviewResponseModel response) {
        assertThat(response.getApplicantId(), is(1L));
        assertThat(response.getInterviewId(), is(1L));
        assertThat(response.getInterviewerIds(), is(Arrays.asList(1L, 3L)));
    }
}

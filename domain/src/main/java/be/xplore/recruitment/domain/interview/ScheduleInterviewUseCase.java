package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import be.xplore.recruitment.domain.interviewer.InterviewerRepository;

import javax.inject.Named;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Named
public class ScheduleInterviewUseCase implements ScheduleInterview {
    private final InterviewerRepository interviewerRepository;
    private final InterviewRepository interviewRepository;
    private final ApplicantRepository applicantRepository;

    public ScheduleInterviewUseCase(InterviewerRepository interviewerRepository,
                                    InterviewRepository interviewRepository, ApplicantRepository applicantRepository) {
        this.interviewerRepository = interviewerRepository;
        this.interviewRepository = interviewRepository;
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void scheduleInterview(ScheduleInterviewRequest request, Consumer<InterviewResponseModel> consumer) {
        Applicant applicant = applicantRepository.findApplicantById(request.getApplicantId())
                .orElseThrow(NotFoundException::new);
        List<Interviewer> interviewers = request.getInterviewerIds().stream()
                .map(id -> interviewerRepository.findById(id).orElseThrow(NotFoundException::new))
                .collect(Collectors.toList());
        Interview interview= Interview.builder()
                .withCreatedTime(request.getCreatedTime())
                .withScheduledTime(request.getScheduledTime())
                .withInterviewers(interviewers)
                .withApplicant(applicant)
                .withPreInterviewReminderSent(request.isPreInterviewReminderSent())
                .build();
        Interview created = interviewRepository.createInterview(interview);
        consumer.accept(InterviewResponseModel.fromInterview(created));
    }
}

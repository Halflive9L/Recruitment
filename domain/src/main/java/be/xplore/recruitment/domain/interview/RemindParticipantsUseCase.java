package be.xplore.recruitment.domain.interview;

import javax.inject.Named;

@Named
public class RemindParticipantsUseCase implements RemindParticipants {
    private final InterviewRepository repository;
    private final ReminderSender reminderSender;

    public RemindParticipantsUseCase(InterviewRepository repository, ReminderSender reminderSender) {
        this.repository = repository;
        this.reminderSender = reminderSender;
    }


    @Override
    public void checkReminders() {
        repository.findInterviewsToRemind().forEach(interview -> {
            sendReminders(interview);
            flagInterviewReminded(interview);
        });
    }

    private String getApplicantMessage(Interview interview) {
        return String.format("applicant message");
    }

    private String getInterviewerMessage(Interview interview) {
        return String.format("interviewer message");
    }

    private void sendReminders(Interview interview) {
        reminderSender.remindApplicant(interview.getApplicant(), getApplicantMessage(interview));
        interview.getInterviewers().forEach(i -> reminderSender.remindInterviewer(i, getInterviewerMessage(interview)));
    }

    private void flagInterviewReminded(Interview interview) {
        interview.setPreInterviewReminderSent(true);
        repository.updateInterviewer(interview);
    }
}

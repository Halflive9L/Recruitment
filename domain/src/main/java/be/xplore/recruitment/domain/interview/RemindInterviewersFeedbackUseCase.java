package be.xplore.recruitment.domain.interview;

import javax.inject.Named;
import java.util.List;

@Named
public class RemindInterviewersFeedbackUseCase implements RemindInterviewersFeedback {
    private final InterviewRepository repository;
    private final ReminderSender reminderSender;

    public RemindInterviewersFeedbackUseCase(InterviewRepository repository, ReminderSender reminderSender) {
        this.repository = repository;
        this.reminderSender = reminderSender;
    }

    @Override
    public void remind() {
        List<Interview> interviews = repository.findInterviewsNeedingFeedback();
        interviews.forEach(this::remindInterviewers);
    }

    private void remindInterviewers(Interview interview) {
        interview.getInterviewers().forEach(interviewer -> {
            reminderSender.remindInterviewer(interviewer, "Interview feedback herinnering",
                    getMessage(interview));
        });
        interview.setFeedbackReminderSent(true);
        repository.updateInterview(interview);
    }

    private String getMessage(Interview interview) {
        return String.format("Herinnering om feedback te geven op interview met %s %s",
                interview.getApplicant().getFirstName(), interview.getApplicant().getLastName());
    }

}

package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interviewer.Interviewer;

public interface ReminderSender {
    void remindApplicant(Applicant applicant, String message);
    void remindInterviewer(Interviewer interviewer, String message);
}

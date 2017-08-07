package be.xplore.recruitment.domain.interview;

public interface RemindParticipants {
    void checkInterviews();

    // 3. markeren interview als reminder verzonden
    //      -> ++veld in interview (preInterviewReminderSent)



    // 1. fetch alle interviews die geremind moete worde uit db
    //      SELECT .. WHERE reminderSent = false AND scheduledTime < (NOW-24h)
    //       -> InterviewRepository
    // 2. send emails (interviewers, applicant)
    //      -> CLEAN arch boundary -> SMTP implementatie
    // 4. Spring @Scheduled voor periodiek check
    //      -> Deze UC...
}

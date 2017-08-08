package be.xplore.recruitment;

import be.xplore.recruitment.domain.interview.RemindParticipants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentScheduler {
    @Autowired
    private RemindParticipants remindParticipants;

    @Scheduled(fixedDelay = 60000)
    public void scheduleInterviewReminders() {
        remindParticipants.checkReminders();
    }
}

package be.xplore.recruitment.domain.interview;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MockInterviewRepo implements InterviewRepository {
    private List<Interview> data;
    private long currentId = 0;

    public MockInterviewRepo() {
        this.data = new ArrayList<>();
    }

    public MockInterviewRepo(List<Interview> data) {
        this.data = data;
        this.currentId = data.stream().mapToLong(Interview::getInterviewId).max().getAsLong();
    }

    @Override
    public Interview createInterview(Interview interview) {
        interview.setInterviewId(++currentId);
        data.add(interview);
        return interview;
    }

    @Override
    public List<Interview> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Interview> findById(long id) {
        return data.stream().filter(i -> i.getInterviewId() == id).findFirst();
    }

    @Override
    public Optional<Interview> deleteInterviewer(long id) {
        return null;
    }

    @Override
    public Optional<Interview> updateInterviewer(Interview interview) {
        return data.stream()
                .filter(i -> i.getInterviewId() == interview.getInterviewId())
                .findFirst()
                .map(i -> {
                    i.setCancelled(interview.isCancelled());
                    i.setInterviewId(interview.getInterviewId());
                    i.setApplicant(interview.getApplicant());
                    i.setInterviewers(interview.getInterviewers());
                    i.setCreatedTime(interview.getCreatedTime());
                    i.setScheduledTime(interview.getScheduledTime());
                    return i;
                });
    }

    @Override
    public List<Interview> findInterviewsToRemind() {
        final LocalDateTime cutoff = LocalDateTime.now().plusDays(1L);
        return data.stream()
                .filter(i -> !i.isPreInterviewReminderSent())
                .filter(i -> cutoff.isAfter(i.getScheduledTime()))
                .collect(Collectors.toList());
    }
}

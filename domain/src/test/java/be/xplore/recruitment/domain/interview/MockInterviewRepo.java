package be.xplore.recruitment.domain.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return null;
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
        return null;
    }
}

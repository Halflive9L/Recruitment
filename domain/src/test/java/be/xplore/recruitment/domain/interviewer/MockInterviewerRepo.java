package be.xplore.recruitment.domain.interviewer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MockInterviewerRepo implements InterviewerRepository {
    private List<Interviewer> data = new ArrayList<>();
    private int lastId = 0;

    public MockInterviewerRepo() {
    }

    public MockInterviewerRepo(List<Interviewer> seed) {
        data = new ArrayList<>(seed);
        lastId = data.stream().map(i -> i.getInterviewerId()).max(Comparator.comparingLong(a -> a)).get().intValue();
    }

    @Override
    public Interviewer createInterviewer(Interviewer interviewer) {
        interviewer.setInterviewerId(++lastId);
        data.add(interviewer);
        return interviewer;
    }

    @Override
    public List<Interviewer> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Interviewer> findById(long id) {
        return data.stream()
                .filter(i -> i.getInterviewerId() == id)
                .findFirst();
    }

    @Override
    public Optional<Interviewer> deleteInterviewer(long id) {
        return data.stream()
                .filter(i -> i.getInterviewerId() == id)
                .findFirst()
                .map(e -> {
                    data.remove(e);
                    return e;
                });
    }

    @Override
    public Optional<Interviewer> updateInterviewer(Interviewer interviewer) {
        return data.stream()
                .filter(i -> i.getInterviewerId() == interviewer.getInterviewerId())
                .findFirst()
                .map(e -> {
                    e.setFirstName(interviewer.getFirstName());
                    e.setLastName(interviewer.getLastName());
                    return e;
                });
    }

    public int count() {
        return data.size();
    }
}

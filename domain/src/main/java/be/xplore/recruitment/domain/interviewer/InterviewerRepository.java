package be.xplore.recruitment.domain.interviewer;

import java.util.List;
import java.util.Optional;

public interface InterviewerRepository {
    Interviewer createInterviewer(Interviewer interviewer);

    List<Interviewer> findAll();

    Optional<Interviewer> findById(long id);

    Optional<Interviewer> deleteInterviewer(long id);

    Optional<Interviewer> updateInterviewer(Interviewer interviewer);
}

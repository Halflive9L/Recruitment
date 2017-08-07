package be.xplore.recruitment.domain.interview;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository {
    Interview createInterview(Interview interview);

    List<Interview> findAll();

    Optional<Interview> findById(long id);

    Optional<Interview> deleteInterviewer(long id);

    Optional<Interview> updateInterviewer(Interview interview);

    List<Interview> findInterviewsToRemind();
}

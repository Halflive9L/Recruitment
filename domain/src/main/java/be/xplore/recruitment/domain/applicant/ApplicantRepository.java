package be.xplore.recruitment.domain.applicant;

import java.util.List;
import java.util.Optional;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public interface ApplicantRepository {
    Applicant createApplicant(Applicant applicant);

    List<Applicant> findAll();

    Optional<Applicant> findApplicantById(long id);

    List<Applicant> findByParameters(Applicant applicant);

    Optional<Applicant> updateApplicant(Applicant applicant);

    Optional<Applicant> deleteApplicant(long id);
}

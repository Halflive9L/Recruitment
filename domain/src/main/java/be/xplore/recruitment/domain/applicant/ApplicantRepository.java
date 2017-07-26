package be.xplore.recruitment.domain.applicant;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public interface ApplicantRepository {

    List<Applicant> findAll();

    void createApplicant(Applicant a);
}

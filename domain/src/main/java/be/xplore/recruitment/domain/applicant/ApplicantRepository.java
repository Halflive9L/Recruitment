package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public interface ApplicantRepository {

    List<Applicant> findAll();

    void createApplicant(Applicant a);

    Applicant findApplicantById(long id) throws NotFoundException;

    void deleteApplicant(long id) throws NotFoundException;
}

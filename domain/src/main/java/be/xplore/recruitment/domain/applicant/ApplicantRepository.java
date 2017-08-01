package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public interface ApplicantRepository {
    void createApplicant(Applicant applicant);

    List<Applicant> findAll();

    Applicant findApplicantById(long id) throws NotFoundException;

    List<Applicant> findByParameters(Applicant applicant) throws NotFoundException;

    void updateApplicant(Applicant applicant) throws NotFoundException;

    Applicant deleteApplicant(long id) throws NotFoundException;
}

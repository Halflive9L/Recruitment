package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public interface ApplicantRepository {
    void createApplicant(Applicant applicant);

    List<Applicant> findAll();

    Optional<Applicant> findApplicantById(long id);

    List<Applicant> findByParameters(Applicant applicant);

    Optional<Applicant> updateApplicant(Applicant applicant);

    Optional<Applicant> deleteApplicant(long id);

    Optional<String> addAttachment(long applicantId, String fileName, InputStream in)
            throws NotFoundException;

    List<String> findAllAttachmentsForApplicant(long applicantId);
}

package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.tag.Tag;

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

    Optional<Attachment> addAttachment(long applicantId, Attachment attachment)
            throws NotFoundException;

    List<Attachment> findAllAttachmentsForApplicant(long applicantId);

    Tag addTagToApplicant(long applicantId, Tag tag) throws EntityAlreadyHasTagException;
}

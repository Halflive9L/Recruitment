package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.tag.Tag;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
@SuppressWarnings("checkstyle:filelength")
public class MockApplicantRepo implements ApplicantRepository {
    List<Applicant> mockApplicants = new ArrayList<>();
    List<Attachment> mockAttachments = new ArrayList<>(1);

    public MockApplicantRepo() {
        mockApplicants.add(Applicant.builder()
                .withFirstName("John").withLastName("Smith").withApplicantId(1)
                .withDateOfBirth(LocalDate.of(1996, 10, 3)).withAddress("Antwerp")
                .withEducation("College").withEmail("john.smith@example.com")
                .withPhone("+32424963258").build());
        mockApplicants.add(Applicant.builder()
                .withFirstName("leeroy").withLastName("Jenkins").withApplicantId(2)
                .withDateOfBirth(LocalDate.of(1996, 10, 3)).withAddress("Kontich")
                .withEducation("University").withEmail("leeroy@jenkins.com")
                .withPhone("+32 420 00 1337").build());
        mockAttachments.add(new Attachment(1, "test.pdf"));
        mockAttachments.add(new Attachment(2, "test.doc"));
    }

    @Override
    public Applicant createApplicant(Applicant a) {
        mockApplicants.add(a);
        return a;
    }

    @Override
    public List<Applicant> findAll() {
        return mockApplicants;
    }

    @Override
    public Optional<Applicant> findApplicantById(long id) {
        for (Applicant mockApplicant : mockApplicants) {
            if (mockApplicant.getApplicantId() == id) {
                return Optional.of(mockApplicant);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Applicant> findByParameters(Applicant applicant) {
        List<Applicant> matches = new ArrayList<>();
        for (Applicant mockApplicant : mockApplicants) {
            if (!isNullOrEmpty(applicant.getFirstName()) &&
                    applicant.getFirstName().equalsIgnoreCase(mockApplicant.getFirstName())) {
                matches.add(mockApplicant);
            }
        }
        return matches;
    }

    @Override
    public Optional<Applicant> updateApplicant(Applicant applicant) {
        for (int i = 0; i < mockApplicants.size(); i++) {
            if (mockApplicants.get(i).getApplicantId() == applicant.getApplicantId()) {
                mockApplicants.set(i, Applicant.builder()
                        .withApplicantId(applicant.getApplicantId())
                        .withFirstName(firstName(applicant, i))
                        .withLastName(lastName(applicant, i))
                        .withEmail(email(applicant, i))
                        .withPhone(phone(applicant, i))
                        .withEducation(education(applicant, i))
                        .withAddress(address(applicant, i))
                        .withDateOfBirth(dateOfBirth(applicant, i)).build());
                return Optional.of(mockApplicants.get(i));
            }
        }
        return Optional.empty();
    }

    private String firstName(Applicant appl, int i) {
        return isNullOrEmpty(appl.getFirstName()) ? mockApplicants.get(i).getFirstName() : appl.getFirstName();
    }

    private String lastName(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getLastName()) ? mockApplicants.get(i).getLastName() : applicant.getLastName();
    }

    private String address(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getAddress()) ? mockApplicants.get(i).getAddress() : applicant.getAddress();
    }

    private String email(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getEmail()) ? mockApplicants.get(i).getEmail() : applicant.getEmail();
    }

    private String phone(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getPhone()) ? mockApplicants.get(i).getPhone() : applicant.getPhone();
    }

    private String education(Applicant appl, int i) {
        return isNullOrEmpty(appl.getEducation()) ? mockApplicants.get(i).getEducation() : appl.getEducation();
    }

    private LocalDate dateOfBirth(Applicant applicant, int i) {
        return applicant.getDateOfBirth() == null ?
                mockApplicants.get(i).getDateOfBirth() : applicant.getDateOfBirth();
    }

    @Override
    public Optional<Applicant> deleteApplicant(long id) {
        Optional<Applicant> deleted = Optional.empty();
        for (int i = 0; i < mockApplicants.size(); i++) {
            if (mockApplicants.get(i).getApplicantId() == id) {
                deleted = deleteApplicant(i);
            }
        }
        return deleted;
    }

    private Optional<Applicant> deleteApplicant(int i) {
        Applicant deletedApplicant = mockApplicants.get(i);
        mockApplicants.remove(i);
        return Optional.ofNullable(deletedApplicant);
    }

    @Override
    public Optional<Attachment> addAttachment(long applicantId, Attachment attachment) {
        if (applicantId == 500) {
            throw new NotFoundException();
        }
        addAttachment(attachment);
        return Optional.of(attachment);
    }

    private void addAttachment(Attachment attachment) {
        setAttachmentId(attachment);
        mockAttachments.add(attachment);
        tryClose(attachment);
    }

    private void tryClose(Attachment attachment) {
        try {
            attachment.getInputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAttachmentId(Attachment attachment) {
        if (mockAttachments.size() > 1) {
            attachment.setAttachmentId(mockAttachments.get(mockAttachments.size() - 1).getAttachmentId() + 1);
        } else {
            attachment.setAttachmentId(1);
        }
    }

    @Override
    public List<Attachment> findAllAttachmentsForApplicant(long applicantId) {
        return mockAttachments;
    }

    @Override
    public Tag addTagToApplicant(long applicantId, Tag tag) {
        return null;
    }

    @Override
    public Set<Tag> addAllTagsToApplicant(long applicantId, Set<Tag> tags) {
        return null;
    }

    @Override
    public Optional<Tag> removeTagFromApplicant(long applicantId, Tag tag) {
        return null;
    }
}
package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.CouldNotDownloadAttachmentException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
class MockApplicantRepo implements ApplicantRepository {
    List<Applicant> mockApplicants = new ArrayList<>();
    List<String> mockAttachments = new ArrayList<>();

    MockApplicantRepo() {
        mockApplicants.add(Applicant.builder()
                .withFirstName("John").withLastName("Smith").withId(1)
                .withDateOfBirth(LocalDate.of(1996, 10, 3))
                .withAddress("Antwerp")
                .withEducation("College")
                .withEmail("john.smith@example.com")
                .withPhone("+32424963258").build());
        mockApplicants.add(Applicant.builder()
                .withFirstName("Leeroy").withLastName("Jenkins").withId(2)
                .withDateOfBirth(LocalDate.of(1996, 10, 3))
                .withAddress("Kontich")
                .withEducation("University")
                .withEmail("leeroy@jenkins.com")
                .withPhone("+32 420 00 1337").build());
    }

    @Override
    public void createApplicant(Applicant a) {
        mockApplicants.add(a);
    }

    @Override
    public List<Applicant> findAll() {
        return mockApplicants;
    }

    @Override
    public Optional<Applicant> findApplicantById(long id) throws NotFoundException {
        for (Applicant mockApplicant : mockApplicants) {
            if (mockApplicant.getApplicantId() == id) {
                return Optional.ofNullable(mockApplicant);
            }
        }
        throw new NotFoundException();
    }

    @Override
    public List<Applicant> findByParameters(Applicant applicant) throws NotFoundException {
        List<Applicant> matches = new ArrayList<>();
        for (Applicant mockApplicant : mockApplicants) {
            boolean isMatch = true;
            if (!isNullOrEmpty(applicant.getFirstName()) &&
                    applicant.getFirstName().equalsIgnoreCase(mockApplicant.getFirstName())) {
                isMatch = false;
                continue;
            }
        }
        return matches;
    }

    @Override
    public Optional<Applicant> updateApplicant(Applicant applicant) {
        for (int i = 0; i < mockApplicants.size(); i++) {
            if (mockApplicants.get(i).getApplicantId() == applicant.getApplicantId()) {
                mockApplicants.set(i, Applicant.builder()
                        .withId(applicant.getApplicantId())
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

    private String firstName(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getFirstName()) ?
                mockApplicants.get(i).getFirstName() : applicant.getFirstName();
    }

    private String lastName(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getLastName()) ?
                mockApplicants.get(i).getLastName() : applicant.getLastName();
    }

    private String address(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getAddress()) ?
                mockApplicants.get(i).getAddress() : applicant.getAddress();
    }

    private String email(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getEmail()) ?
                mockApplicants.get(i).getEmail() : applicant.getEmail();
    }

    private String phone(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getPhone()) ?
                mockApplicants.get(i).getPhone() : applicant.getPhone();
    }

    private String education(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getEducation()) ?
                mockApplicants.get(i).getEducation() : applicant.getEducation();
    }

    private LocalDate dateOfBirth(Applicant applicant, int i) {
        return applicant.getDateOfBirth() == null ?
                mockApplicants.get(i).getDateOfBirth() : applicant.getDateOfBirth();
    }

    @Override
    public Optional<Applicant> deleteApplicant(long id) {
        Applicant deletedApplicant;
        for (int i = 0; i < mockApplicants.size(); i++) {
            if (mockApplicants.get(i).getApplicantId() == id) {
                deletedApplicant = mockApplicants.get(i);
                mockApplicants.remove(i);
                return Optional.ofNullable(deletedApplicant);
            }
        }
        throw new NotFoundException();
    }

    @Override
    public Optional<Attachment> addAttachment(long applicantId, Attachment attachment) throws NotFoundException {
        return null;
    }

    @Override
    public List<Attachment> findAllAttachmentsForApplicant(long applicantId) {
        return null;
    }

    @Override
    public Attachment downloadAttachment(long attachmentId) throws CouldNotDownloadAttachmentException {
        return null;
    }

}

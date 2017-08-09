package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;

public class MockApplicantRepo implements ApplicantRepository {
    List<Applicant> mockApplicants = new ArrayList<>();
    List<Attachment> mockAttachments = new ArrayList<>(1);

    public MockApplicantRepo() {
        mockApplicants.add(Applicant.builder()
                .withFirstName("John").withLastName("Smith").withApplicantId(1)
                .withDateOfBirth(LocalDate.of(1996, 10, 3)) .withAddress("Antwerp").withEducation("College")
                .withEmail("john.smith@example.com").withPhone("+32424963258").build());
        mockApplicants.add(Applicant.builder()
                .withFirstName("Leeroy").withLastName("Jenkins").withApplicantId(2)
                .withDateOfBirth(LocalDate.of(1996, 10, 3)).withAddress("Kontich").withEducation("University")
                .withEmail("leeroy@jenkins.com").withPhone("+32 420 00 1337").build());
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

    private String firstName(Applicant a, int i) {
        return isNullOrEmpty(a.getFirstName()) ? mockApplicants.get(i).getFirstName() : a.getFirstName();
    }

    private String lastName(Applicant a, int i) {
        return isNullOrEmpty(a.getLastName()) ? mockApplicants.get(i).getLastName() : a.getLastName();
    }

    private String address(Applicant a, int i) {
        return isNullOrEmpty(a.getAddress()) ? mockApplicants.get(i).getAddress() : a.getAddress();
    }

    private String email(Applicant a, int i) {
        return isNullOrEmpty(a.getEmail()) ? mockApplicants.get(i).getEmail() : a.getEmail();
    }

    private String phone(Applicant a, int i) {
        return isNullOrEmpty(a.getPhone()) ? mockApplicants.get(i).getPhone() : a.getPhone();
    }

    private String education(Applicant a, int i) {
        return isNullOrEmpty(a.getEducation()) ? mockApplicants.get(i).getEducation() : a.getEducation();
    }

    private LocalDate dateOfBirth(Applicant a, int i) {
        return a.getDateOfBirth() == null ? mockApplicants.get(i).getDateOfBirth() : a.getDateOfBirth();
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
        return Optional.empty();
    }

    @Override
    public Optional<Attachment> addAttachment(long applicantId, Attachment attachment) {
        if (applicantId == 500) {
            throw new NotFoundException();
        }
        if (mockAttachments.size() > 1) {
            attachment.setAttachmentId(mockAttachments.get(mockAttachments.size() - 1).getAttachmentId() + 1);
        } else {
            attachment.setAttachmentId(1);
        }
        mockAttachments.add(attachment);
        try {
            attachment.getInputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(attachment);
    }

    @Override
    public List<Attachment> findAllAttachmentsForApplicant(long applicantId) {
        return mockAttachments;
    }
}

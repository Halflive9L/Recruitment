package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
class MockApplicantRepo implements ApplicantRepository {
    List<Applicant> mockApplicants = new ArrayList<>();

    MockApplicantRepo() {
        mockApplicants.add(Applicant.builder()
                .withFirstName("John").withLastName("Smith").withId(1)
                .withDateOfBirth(new Calendar.Builder().setDate(1996, 10, 3).build()
                        .getTime())
                .withAddress("Antwerp")
                .withEducation("College")
                .withEmail("john.smith@example.com")
                .withPhone("+32424963258").build());
        mockApplicants.add(Applicant.builder()
                .withFirstName("Leeroy").withLastName("Jenkins").withId(2)
                .withDateOfBirth(new Calendar.Builder().setDate(1986, 3, 10).build()
                        .getTime())
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
    public Applicant findApplicantById(long id) throws NotFoundException {
        for (Applicant mockApplicant : mockApplicants) {
            if (mockApplicant.getApplicantId() == id) {
                return mockApplicant;
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
    public void updateApplicant(Applicant applicant) {
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
                return;
            }
        }
        throw new NotFoundException();
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

    private Date dateOfBirth(Applicant applicant, int i) {
        return applicant.getDateOfBirth() == null ?
                mockApplicants.get(i).getDateOfBirth() : applicant.getDateOfBirth();
    }

    @Override
    public Applicant deleteApplicant(long id) {
        Applicant deletedApplicant;
        for (int i = 0; i < mockApplicants.size(); i++) {
            if (mockApplicants.get(i).getApplicantId() == id) {
                deletedApplicant = mockApplicants.get(i);
                mockApplicants.remove(i);
                return deletedApplicant;
            }
        }
        throw new NotFoundException();
    }
}

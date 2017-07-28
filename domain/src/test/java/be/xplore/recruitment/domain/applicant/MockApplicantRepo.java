package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;
import static java.util.Arrays.asList;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
class MockApplicantRepo implements ApplicantRepository {
    Applicant[] mockApplicants = {
            Applicant.builder()
                    .withFirstName("John").withLastName("Smith").withId(1)
                    .withDateOfBirth(new Calendar.Builder().setDate(1996, 10, 3).build()
                            .getTime())
                    .withAddress("Antwerp")
                    .withEducation("College")
                    .withEmail("john.smith@example.com")
                    .withPhone("+32424963258").build(),
            Applicant.builder()
                    .withFirstName("Leeroy").withLastName("Jenkins").withId(2)
                    .withDateOfBirth(new Calendar.Builder().setDate(1986, 3, 10).build()
                            .getTime())
                    .withAddress("Kontich")
                    .withEducation("University")
                    .withEmail("leeroy@jenkins.com")
                    .withPhone("+32 420 00 1337").build()
    };

    @Override
    public void createApplicant(Applicant a) {
    }

    @Override
    public List<Applicant> findAll() {
        return asList(mockApplicants);
    }

    @Override
    public Applicant findApplicantById(long id) {
        for (Applicant p : mockApplicants) {
            if (p.getApplicantId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void updateApplicant(Applicant applicant) {
        for (int i = 0; i < mockApplicants.length; i++) {
            if (mockApplicants[i].getApplicantId() == applicant.getApplicantId()) {
                mockApplicants[i] = Applicant.builder()
                        .withId(applicant.getApplicantId())
                        .withFirstName(firstName(applicant, i))
                        .withLastName(lastName(applicant, i))
                        .withEmail(email(applicant, i))
                        .withPhone(phone(applicant, i))
                        .withEducation(education(applicant, i))
                        .withAddress(address(applicant, i))
                        .withDateOfBirth(dateOfBirth(applicant, i)).build();
                return;
            }
        }
        throw new NotFoundException();
    }

    private String firstName(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getFirstName()) ? mockApplicants[i].getFirstName() : applicant.getFirstName();
    }

    private String lastName(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getLastName()) ? mockApplicants[i].getLastName() : applicant.getLastName();
    }

    private String address(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getAddress()) ? mockApplicants[i].getAddress() : applicant.getAddress();
    }

    private String email(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getEmail()) ? mockApplicants[i].getEmail() : applicant.getEmail();
    }

    private String phone(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getPhone()) ? mockApplicants[i].getPhone() : applicant.getPhone();
    }

    private String education(Applicant applicant, int i) {
        return isNullOrEmpty(applicant.getEducation()) ? mockApplicants[i].getEducation() : applicant.getEducation();
    }

    private Date dateOfBirth(Applicant applicant, int i) {
        return applicant.getDateOfBirth() == null ? mockApplicants[i].getDateOfBirth() : applicant.getDateOfBirth();
    }

    @Override
    public void deleteApplicant(long id) throws NotFoundException {
        for (int i = 0; i < mockApplicants.length; i++) {
            if (mockApplicants[i].getApplicantId() == id) {
                mockApplicants[i] = null;
                return;
            }
        }
        throw new NotFoundException();
    }
}

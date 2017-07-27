package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.Calendar;
import java.util.List;

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

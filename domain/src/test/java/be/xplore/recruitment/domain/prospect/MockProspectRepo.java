package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;

import static be.xplore.recruitment.domain.prospect.Prospect.builder;
import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;
import static be.xplore.recruitment.domain.util.Validator.isValidEmail;
import static be.xplore.recruitment.domain.util.Validator.isValidPhone;
import static java.util.Arrays.asList;

/**
 * @author Lander
 * @since 28/07/2017
 */
class MockProspectRepo implements ProspectRepository {
    Prospect[] mockProspects = {
            Prospect.builder("John", "Smith")
                    .withId(1)
                    .withEmail("john.smith@example.com")
                    .withPhone("+32424963258").build(),
            Prospect.builder("Leroy", "Jenkins")
                    .withId(2)
                    .withEmail("leeroy@jenkins.com")
                    .withPhone("+325435435435").build()
    };

    @Override
    public void createProspect(Prospect a) {
    }

    @Override
    public List<Prospect> findAll() {
        return asList(mockProspects);
    }

    @Override
    public Prospect findProspectById(long id) {
        for (Prospect p : mockProspects) {
            if (p.getProspectId() == id) {
                return p;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public Prospect updateProspect(Prospect prospect) {
        if (findProspectById(prospect.getProspectId()) == null) {
            throw new NotFoundException();
        }
        Prospect zoekProspect = findProspectById(prospect.getProspectId());
        if (prospect.getEmail() != null || isValidEmail(prospect.getEmail())) {
            prospect.setEmail(zoekProspect.getEmail());
        }
        if (prospect.getPhone() != null || isValidPhone(prospect.getPhone())) {
            prospect.setPhone(zoekProspect.getPhone());
        }
        zoekProspect = builder(prospect.getFirstName(), prospect.getLastName())
                .withEmail(prospect.getEmail())
                .withPhone(prospect.getPhone())
                .withId(prospect.getProspectId()).build();
        mockProspects[(int) prospect.getProspectId() - 1] = zoekProspect;
        return zoekProspect;
    }

    private String firstName(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getFirstName()) ? mockProspects[i].getFirstName() : prospect.getFirstName();
    }

    private String lastName(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getLastName()) ? mockProspects[i].getLastName() : prospect.getLastName();
    }

    private String email(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getEmail()) ? mockProspects[i].getEmail() : prospect.getEmail();
    }

    private String phone(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getPhone()) ? mockProspects[i].getPhone() : prospect.getPhone();
    }

    @Override
    public void deleteProspect(long id) throws NotFoundException {
        if (findProspectById(id) == null) {
            throw new NotFoundException();
        }
        mockProspects[(int) id] = null;
    }
}

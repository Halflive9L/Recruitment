package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;

/**
 * @author Lander
 * @since 28/07/2017
 */
class MockProspectRepo implements ProspectRepository {

    List<Prospect> mockProspects = new ArrayList<>(2);

    MockProspectRepo() {
        mockProspects.add(Prospect.builder()
                                  .withFirstName("John")
                                  .withLastName("Smith")
                                  .withProspectId(1)
                                  .withEmail("john.smith@example.com")
                                  .withPhone("+32424963258").build());
        mockProspects.add(Prospect.builder()
                                  .withFirstName("Leeroy")
                                  .withLastName("Jenkins")
                                  .withProspectId(2)
                                  .withEmail("leeroy@jenkins.com")
                                  .withPhone("+325435435435").build());
    }

    @Override
    public Prospect createProspect(Prospect a) {
        mockProspects.add(a);
        return a;
    }

    @Override
    public List<Prospect> findAll() {
        return mockProspects;
    }

    @Override
    public Optional<Prospect> findProspectById(long id) {
        for (Prospect p : mockProspects) {
            if (p.getProspectId() == id) {
                return Optional.ofNullable(p);
            }
        }
        throw new NotFoundException();
    }

    @Override
    public List<Prospect> findByParameters(Prospect prospect) {
        List<Prospect> matches = new ArrayList<>();
        for (Prospect mockProspect : mockProspects) {
            if (!isNullOrEmpty(prospect.getFirstName()) &&
                    prospect.getFirstName().equalsIgnoreCase(mockProspect.getFirstName())) {
                matches.add(mockProspect);
            }
        }
        return matches;
    }

    @Override
    public Optional<Prospect> updateProspect(Prospect prospect) {
        for (int i = 0; i < mockProspects.size(); i++) {
            if (mockProspects.get(i).getProspectId() == prospect.getProspectId()) {
                mockProspects.set(i, Prospect.builder()
                        .withProspectId(prospect.getProspectId())
                        .withFirstName(firstName(prospect, i))
                        .withLastName(lastName(prospect, i))
                        .withEmail(email(prospect, i))
                        .withPhone(phone(prospect, i))
                        .build());
                return Optional.of(mockProspects.get(i));
            }
        }
        return Optional.empty();
    }

    private String firstName(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getFirstName()) ? mockProspects.get(i).getFirstName() : prospect.getFirstName();
    }

    private String lastName(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getLastName()) ? mockProspects.get(i).getLastName() : prospect.getLastName();
    }

    private String email(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getEmail()) ? mockProspects.get(i).getEmail() : prospect.getEmail();
    }

    private String phone(Prospect prospect, int i) {
        return isNullOrEmpty(prospect.getPhone()) ? mockProspects.get(i).getPhone() : prospect.getPhone();
    }

    @Override
    public Optional<Prospect> deleteProspect(long id) throws NotFoundException {
        Optional<Prospect> optional = findProspectById(id);
        mockProspects.remove((int) id);
        return optional;
    }
}

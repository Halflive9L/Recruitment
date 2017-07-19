package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lander on 18/07/2017.
 */
public class ProspectRepository implements IProspectRepository {

    private List<Prospect> mockData;

    public ProspectRepository() {
        mockData = mockData();
    }

    @Override
    public List<Prospect> mockData() {
        List<Prospect> mockData = new ArrayList<>();
        mockData.add(new Prospect(1, "Stijn", "Schack", "stijn.schack@gmail.com", "03 123 45 67"));
        mockData.add(new Prospect(2, "Lander", "Tuyteleers", "lander.tuyteleers@gmail.com", "03 765 43 21"));
        mockData.add(new Prospect(3, "Jan", "Janssens", "jan.janssens@gmail.com", "03 891 01 11"));
        mockData.add(new Prospect(4, "Bart", "Peeters", "bart.peeters@gmail.com", "03 213 14 15"));
        mockData.add(new Prospect(5, "kim", "Clijsters", "kim.clijsters@gmail.com", "03 161 71 81"));
        return mockData;
    }

    @Override
    public List<Prospect> findAll() {
        return null;
    }

    @Override
    public Prospect findProspectById(long id) {
        return null;
    }

    @Override
    public List<Prospect> findAllByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Prospect> findAllByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Prospect> findAllByPhone(String phone) {
        return null;
    }

    @Override
    public List<Prospect> findAllByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Prospect> findAllByFirstNameAndEmail(String firstName, String email) {
        return null;
    }

    public List<Prospect> getMockData() {
        return mockData;
    }
}

package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lander on 18/07/2017.
 */
public class ProspectRepository {

    private List<Prospect> mockData;

    public ProspectRepository() {
        mockData = mockData();
    }

    public List<Prospect> mockData() {
        List<Prospect> mockData = new ArrayList<>();
        mockData.add(new Prospect("Stijn", "Schack", "stijn.schack@gmail.com", "03 123 45 67"));
        mockData.add(new Prospect("Lander", "Tuyteleers", "lander.tuyteleers@gmail.com", "03 765 43 21"));
        mockData.add(new Prospect("Jan", "Janssens", "jan.janssens@gmail.com", "03 891 01 11"));
        mockData.add(new Prospect("Bart", "Peeters", "bart.peeters@gmail.com", "03 213 14 15"));
        mockData.add(new Prospect("kim", "Clijsters", "kim.clijsters@gmail.com", "03 161 71 81"));
        return mockData;
    }

    public List<Prospect> getMockData() {
        return mockData;
    }
}

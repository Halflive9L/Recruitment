package be.xplore.recruitment.model;

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
        mockData.add(new Prospect(1, "Stijn", "stijn.schack@gmail.com", "03 123 45 67"));
        mockData.add(new Prospect(2, "Lander", "lander.tuyteleers@gmail.com", "03 765 43 21"));
        mockData.add(new Prospect(3, "Jan", "jan.janssens@gmail.com", "03 891 01 11"));
        mockData.add(new Prospect(4, "Bart", "bart.peeters@gmail.com", "03 213 14 15"));
        mockData.add(new Prospect(5, "kim", "kim.clijsters@gmail.com", "03 161 71 81"));
        return mockData;
    }

    public List<Prospect> getMockData() {
        return mockData;
    }
}

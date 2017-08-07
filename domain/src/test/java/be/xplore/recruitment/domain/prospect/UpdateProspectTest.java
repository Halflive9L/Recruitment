package be.xplore.recruitment.domain.prospect;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static be.xplore.recruitment.domain.prospect.Prospect.builder;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateProspectTest {
    private final MockProspectRepo repository = new MockProspectRepo();


    @Test
    public void testUpdateProspect() {
        Prospect prospect = builder()
                .withFirstName("leeroy")
                .withLastName("jenkins")
                .withEmail("leeroy@jenkins.com")
                .withPhone("+325435435435")
                .withId(2).build();
        repository.updateProspect(prospect);
        Assert.assertEquals(repository.mockProspects.get(1), getExpectedProspect());
    }

    @Test
    public void testUpdateProspectWithInvalidEmail() {
        Prospect prospect = builder()
                .withFirstName("leeroy")
                .withLastName("jenkins")
                .withEmail("a")
                .withPhone("+325435435435")
                .withId(2).build();
        repository.updateProspect(prospect);
        Assert.assertEquals(repository.mockProspects.get(1), getExpectedProspect());
    }

    @Test
    public void testUpdateProspectWithInvalidPhone() {
        Prospect prospect = builder()
                .withFirstName("leeroy")
                .withLastName("jenkins")
                .withEmail("leeroy@jenkins.com")
                .withPhone("a")
                .withId(2).build();
        repository.updateProspect(prospect);
        Assert.assertEquals(repository.mockProspects.get(1), getExpectedProspect());
    }


    private Prospect getExpectedProspect() {
        return builder()
                .withFirstName("leeroy")
                .withLastName("jenkins")
                .withEmail("leeroy@jenkins.com")
                .withPhone("+325435435435")
                .withId(2).build();
    }
}

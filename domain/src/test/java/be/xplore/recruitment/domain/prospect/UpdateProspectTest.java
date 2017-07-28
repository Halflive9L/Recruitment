package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static be.xplore.recruitment.domain.prospect.Prospect.builder;
import static java.util.Arrays.asList;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateProspectTest {
    private final MockProspectRepo repository = new MockProspectRepo();


    @Test
    public void testUpdateProspect() {
        Prospect prospect = builder("leeroy", "jenkins")
                .withEmail("leeroy@jenkins.com")
                .withPhone("+325435435435")
                .withId(2).build();
        repository.updateProspect(prospect);
        Assert.assertEquals(repository.mockProspects[1], getExpectedProspect());
    }

    @Test
    public void testUpdateProspectWithInvalidEmail() {
        Prospect prospect = builder("leeroy", "jenkins")
                .withEmail("a")
                .withPhone("+325435435435")
                .withId(2).build();
        repository.updateProspect(prospect);
        Assert.assertEquals(repository.mockProspects[1], getExpectedProspect());
    }

    @Test
    public void testUpdateProspectWithInvalidPhone() {
        Prospect prospect = builder("leeroy", "jenkins")
                .withEmail("leeroy@jenkins.com")
                .withPhone("a")
                .withId(2).build();
        repository.updateProspect(prospect);
        Assert.assertEquals(repository.mockProspects[1], getExpectedProspect());
    }


    private Prospect getExpectedProspect() {
        return builder("leeroy", "jenkins")
                .withEmail("leeroy@jenkins.com")
                .withPhone("+325435435435")
                .withId(2).build();
    }
}

package be.xplore.recruitment.api;

import be.xplore.recruitment.model.NotFoundException;
import be.xplore.recruitment.model.Prospect;
import be.xplore.recruitment.model.ProspectRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */
@RestController
public class ProspectController {

    private ProspectRepository prospectRepository = new ProspectRepository();
    private final AtomicInteger counter = new AtomicInteger(prospectRepository.getMockData().size());


    /**
    * Voegt een prospect via een POST toe aan de mockData lijst van prospectRepository
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addprospect")
    public Prospect addProspect(@RequestParam(value = "name") @NotEmpty String name,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "phone") String phone) {
        Prospect prospect = new Prospect(counter.incrementAndGet(), name, email, phone);
        prospectRepository.getMockData().add(prospect);
        return prospect;
    }

    /**
    * Vraagt een prospect op aan de hand van een id.
     * Indien de id niet gevonden is wordt een 404 error gestuurd.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @RequestMapping(method = RequestMethod.GET, value = "/prospect/id/{id}")
    public Prospect prospect(@PathVariable int id) {
        Optional<Prospect> optional = prospectRepository.getMockData().stream().filter(p -> p.getId() == id).findFirst();
        Prospect prospect;
        try {
            prospect = optional.get();
        }catch (NoSuchElementException e){
            throw new NotFoundException(id);
        }
        return prospect;
    }

    /**
    * Vraagt een prospect op aan de hand van een naam.
    * Indien de naam niet gevonden is wordt een 404 error gestuurd.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/prospect/name/{name}")
    public List<Prospect> prospect(@PathVariable String name) {
        List<Prospect> prospects = prospectRepository.getMockData().stream().filter(p -> p.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        if (prospects.isEmpty())
            throw new NotFoundException(name);
        return prospects;
    }


    /**
    * Vraagt de volledige lijst van prospects op.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public List<Prospect> getProspects() {
        return new ArrayList<>(prospectRepository.getMockData());
    }

}

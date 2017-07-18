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
    @RequestMapping(method = RequestMethod.POST, value = "/prospect")
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
    @RequestMapping(method = RequestMethod.GET, value = "/prospect/{id}")
    public Prospect prospect(@PathVariable int id) {
        Optional<Prospect> optional = prospectRepository.getMockData().stream().filter(p -> p.getId() == id).findFirst();
        Prospect prospect;
        try {
            prospect = optional.get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(id);
        }
        return prospect;
    }

    /**
     * Vraagt een prospect op aan de hand van een naam.
     * Indien de naam niet gevonden is wordt een 404 error gestuurd.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public List<Prospect> prospect(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "phone", required = false) String phone) {

        List<Prospect> prospects = prospectRepository.getMockData().stream().collect(Collectors.toList());

        System.out.println("Email: " + email);
        if (name != null && !name.isEmpty()) {
            Optional<Prospect> optional = prospects.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
            if(!optional.isPresent())
                throw new NotFoundException(name);
            prospects = prospects.stream().filter(p -> p.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        }
        if (email != null && !email.isEmpty()) {
            Optional<Prospect> optional = prospects.stream().filter(p -> p.getEmail().equalsIgnoreCase(email)).findFirst();
            if(!optional.isPresent())
                throw new NotFoundException(email);
            prospects = prospects.stream().filter(p -> p.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
        }

        if (phone != null && !phone.isEmpty()) {
            Optional<Prospect> optional = prospects.stream().filter(p -> p.getPhone().equalsIgnoreCase(phone)).findFirst();
            if(!optional.isPresent())
                throw new NotFoundException(phone);
            prospects = prospects.stream().filter(p -> p.getPhone().equalsIgnoreCase(phone)).collect(Collectors.toList());
        }
        return prospects;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/prospect/{id}")
    public Prospect deleteProspect(@PathVariable int id) {
        Optional<Prospect> optional = prospectRepository.getMockData().stream().filter(p -> p.getId() == id).findFirst();
        if (!optional.isPresent())
            throw new NotFoundException(id);
        prospectRepository.getMockData().remove(optional.get());
        return optional.get();
    }
}

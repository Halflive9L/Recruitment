package be.xplore.recruitment.api;

import be.xplore.recruitment.model.NotFoundException;
import be.xplore.recruitment.model.Prospect;
import be.xplore.recruitment.repository.IProspectRepository;
import be.xplore.recruitment.repository.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private ProspectRepository repository = new ProspectRepository();

    private final IProspectRepository prospectRepository;

    @Autowired
    public ProspectController(IProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    /**
    * Voegt een prospect via een POST toe aan de mockData lijst van prospectRepository
     */
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Prospect> addProspect(@RequestBody Prospect input) {
        Prospect prospect = new Prospect(input);
        prospectRepository.save(prospect);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Vraagt een prospect op aan de hand van een id.
     * Indien de id niet gevonden is wordt een 404 error gestuurd.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @RequestMapping(method = RequestMethod.GET, value = "/prospect/{prospectId}")
    public Prospect prospect(@PathVariable long prospectId) {
        Prospect prospect;
        try {
            prospect = prospectRepository.findProspectByProspectId(prospectId);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(prospectId);
        }
        return prospect;
    }

    /**
     * Vraagt een prospect op aan de hand van een naam, een email of een telefoonnumber of een
     * combinatie van deze 3 parameters.
     * @param firstName de voornaam van de prospect
     * @param lastName de achternaam van de prospect
     * @param email het email adres van de prospect
     * @param phone het telefoonnummer van de prospect
     */
    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public List<Prospect> prospect(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "phone", required = false) String phone) {

        //List<Prospect> prospects = new ArrayList<>(getMockData.getMockData());
        List<Prospect> prospects = prospectRepository.findAll();

        if (firstName != null && !firstName.isEmpty()) {
            Optional<Prospect> optional = prospects.stream().filter(p -> p.getFirstName().equalsIgnoreCase(firstName)).findFirst();
            if(!optional.isPresent())
                throw new NotFoundException(firstName);
            prospects = prospects.stream().filter(p -> p.getFirstName().equalsIgnoreCase(firstName)).collect(Collectors.toList());
        }
        if (lastName != null && !lastName.isEmpty()) {
            Optional<Prospect> optional = prospects.stream().filter(p -> p.getLastName().equalsIgnoreCase(lastName)).findFirst();
            if(!optional.isPresent())
                throw new NotFoundException(lastName);
            prospects = prospects.stream().filter(p -> p.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toList());
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/prospect/{prospectId}")
    public Prospect deleteProspect(@PathVariable int prospectId) {
        Optional<Prospect> optional = repository.getMockData().stream().filter(p -> p.getProspectId() == prospectId).findFirst();
        if (!optional.isPresent())
            throw new NotFoundException(prospectId);
        repository.getMockData().remove(optional.get());
        return optional.get();
    }
}

package be.xplore.recruitment.api;

import be.xplore.recruitment.model.NotFoundException;
import be.xplore.recruitment.model.Prospect;
import be.xplore.recruitment.repository.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Stijn Schack
 * @since 7/18/2017
 */

@RestController
public class ProspectController {
    private final ProspectRepository prospectRepository;

    @Autowired
    public ProspectController(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    /**
     * Voegt een prospect via een POST toe aan de mockData lijst van prospectRepository
     */
    @RequestMapping(method = RequestMethod.POST, value = "/prospect")
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
        Prospect prospect = prospectRepository.findOne(prospectId);
        if (prospect == null)
            throw new NotFoundException(prospectId);
        return prospect;
    }


    /**
     * Vraagt een prospect op aan de hand van een naam, een email of een telefoonnumber of een
     * combinatie van deze 3 parameters.
     */

    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public List<Prospect> prospect(@ModelAttribute ProspectQuery query) {
        System.out.println("query = " + query);
        return prospectRepository.findAll(query);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/prospect/{prospectId}")
    public Prospect deleteProspect(@PathVariable long prospectId) {
        Prospect prospect = prospectRepository.findOne(prospectId);
        if (prospect == null) throw new NotFoundException(prospectId);
        prospectRepository.delete(prospect);
        return prospect;
    }


}

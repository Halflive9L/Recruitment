package be.xplore.recruitment.web.api;

import be.xplore.recruitment.domain.model.Prospect;
import be.xplore.recruitment.domain.model.exceptions.NotFoundException;
import be.xplore.recruitment.repository.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST, value = "/prospect")
    public ResponseEntity<Prospect> addProspect(@RequestBody Prospect input) {
        Prospect prospect = new Prospect(input);
        prospectRepository.save(prospect);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @RequestMapping(method = RequestMethod.GET, value = "/prospect/{prospectId}")
    public Prospect getProspectById(@PathVariable long prospectId) {
        Prospect prospect = prospectRepository.findOne(prospectId);
        if (prospect == null) {
            throw new NotFoundException(prospectId);
        }
        return prospect;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public List<Prospect> getProspectByParam(@ModelAttribute ProspectQuery query) {
        System.out.println("query = " + query);
        return prospectRepository.findAll(query);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/prospect/{prospectId}")
    public Prospect deleteProspect(@PathVariable long prospectId) {
        Prospect prospect = prospectRepository.findOne(prospectId);
        if (prospect == null) {
            throw new NotFoundException(prospectId);
        }
        prospectRepository.delete(prospect);
        return prospect;
    }


}

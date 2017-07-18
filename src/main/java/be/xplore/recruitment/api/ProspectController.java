package be.xplore.recruitment.api;

import be.xplore.recruitment.model.Prospect;
import be.xplore.recruitment.model.ProspectRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */
@RestController
public class ProspectController {

    private ProspectRepository prospectRepository = new ProspectRepository();
    private final AtomicInteger counter = new AtomicInteger(prospectRepository.getMockData().size());


    @RequestMapping(method = RequestMethod.POST, value = "/addprospect")
    public Prospect addProspect(@RequestParam(value = "name") @NotEmpty String name,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "phone") String phone) {
        Prospect prospect = new Prospect(counter.incrementAndGet(), name, email, phone);
        prospectRepository.getMockData().add(prospect);
        return prospect;
    }


    @RequestMapping(method=RequestMethod.GET, value = "/prospect/{id}")
    public Prospect prospect(@PathVariable int id) {
        return prospectRepository.getMockData().stream().filter(p -> p.getId() == id).findFirst().get();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public List<Prospect> getProspects(){
        return new ArrayList<>(prospectRepository.getMockData());
    }

}

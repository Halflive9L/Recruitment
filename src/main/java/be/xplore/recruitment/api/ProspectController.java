package be.xplore.recruitment.api;

import be.xplore.recruitment.model.Prospect;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */
@RestController
public class ProspectController {

    private final AtomicInteger counter = new AtomicInteger(0);

    @RequestMapping(method = RequestMethod.POST, value = "/addprospect")
    public Prospect addProspect(@RequestParam(value = "name") @NotEmpty String name,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "phone") String phone) {
        return new Prospect(counter.incrementAndGet(), name, email, phone);
    }

    @RequestMapping(method=RequestMethod.GET, value = "/prospect/{id}")
    public String prospect() {
        return "";
    }
}

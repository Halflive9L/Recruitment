package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.CreateProspect;
import be.xplore.recruitment.domain.prospect.CreateProspectRequest;
import be.xplore.recruitment.domain.prospect.CreateProspectUseCase;
import be.xplore.recruitment.domain.prospect.Prospect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Stijn Schack
 * @since 7/18/2017
 */

@RestController
public class ProspectController {
    @RequestMapping(method = RequestMethod.POST, value = "/prospect")
    public ResponseEntity<JsonProspect> addProspect(@RequestBody JsonProspect input) {
        CreateProspectRequest request = new CreateProspectRequest();
        request.prospect = new Prospect.ProspectBuilder(input.getFirstName(), input.getLastName())
                .setEmail(input.getEmail())
                .setPhone(input.getPhone()).createProspect();
        CreateProspect createProspect = new CreateProspectUseCase();
        createProspect.createProspect(request, prospectId -> {});
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/prospect/{prospectId}")
    public ResponseEntity<JsonProspect> getProspectById(@PathVariable long prospectId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public ResponseEntity<JsonProspect> getProspectByParam(@ModelAttribute JsonProspect query) {
        System.out.println("query = " + query);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/prospect/{prospectId}")
    public ResponseEntity<JsonProspect> deleteProspect(@PathVariable long prospectId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.prospect.CreateProspect;
import be.xplore.recruitment.domain.prospect.CreateProspectRequest;
import be.xplore.recruitment.domain.prospect.DeleteProspect;
import be.xplore.recruitment.domain.prospect.DeleteProspectRequest;
import be.xplore.recruitment.domain.prospect.ReadProspect;
import be.xplore.recruitment.domain.prospect.ReadProspectRequest;
import be.xplore.recruitment.domain.prospect.UpdateProspect;
import be.xplore.recruitment.domain.prospect.UpdateProspectRequest;
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

    @Autowired
    private CreateProspect createProspect;

    @Autowired
    private ReadProspect readProspect;

    @Autowired
    private UpdateProspect updateProspect;

    @Autowired
    private DeleteProspect deleteProspect;


    @RequestMapping(method = RequestMethod.POST, value = "/prospect")
    public ResponseEntity<JsonProspect> addProspect(@RequestBody JsonProspect input) {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = input.getFirstName();
        request.lastName = input.getLastName();
        request.email = input.getEmail();
        request.phone = input.getPhone();
        try {
            createProspect.createProspect(request, prospectId -> {
            });
        } catch (InvalidEmailException | InvalidPhoneException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/prospect/{prospectId}")
    public ResponseEntity<List<JsonProspect>> getProspectById(@PathVariable long prospectId) {
        ReadProspectRequest request = new ReadProspectRequest();
        request.prospectId = prospectId;
        JsonProspectPresenter presenter = new JsonProspectPresenter();
        readProspect.readProspectById(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/prospect")
    public ResponseEntity<List<JsonProspect>> getProspectByParam(@ModelAttribute JsonProspect query) {
        System.out.println("query = " + query);
        ReadProspectRequest request = new ReadProspectRequest();
        //JsonProspectPresenter prospectPresenter = new JsonProspectPresenter();
        JsonProspectToReadProspectRequest(query, request);
        /*readProspect.readAllProspects(prospectPresenter);
        return prospectPresenter.getResponseEntity();*/
        JsonProspectPresenter presenter = new JsonProspectPresenter();
        if (query.isEmpty()) {
            readProspect.readAllProspects(presenter);
        } else {
            readProspect.readProspectByParam(request, presenter);
        }
        return presenter.getResponseEntity();

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/prospect/{prospectId}")
    public ResponseEntity<List<JsonProspect>> deleteProspect(@PathVariable long prospectId) {
        DeleteProspectRequest request = new DeleteProspectRequest();
        JsonProspectPresenter presenter = new JsonProspectPresenter();
        request.prospectId = prospectId;
        try {
            deleteProspect.deleteProspect(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/prospect/{prospectId}")
    public ResponseEntity<List<JsonProspect>> updateProspect(@PathVariable long prospectId) {
        UpdateProspectRequest request = new UpdateProspectRequest();
        JsonProspectPresenter presenter = new JsonProspectPresenter();
        request.prospectId = prospectId;
        try {
            updateProspect.updateProspect(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  catch (InvalidEmailException | InvalidPhoneException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return presenter.getResponseEntity();
    }

    private void JsonProspectToReadProspectRequest(@ModelAttribute JsonProspect query, ReadProspectRequest request) {
        request.firstName = query.getFirstName();
        request.lastName = query.getLastName();
        request.email = query.getEmail();
        request.phone = query.getPhone();
    }
}

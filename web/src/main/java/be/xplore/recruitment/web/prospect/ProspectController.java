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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @RequestMapping(method = RequestMethod.POST, value = "/api/prospect")
    public ResponseEntity<JsonProspect> addProspect(@RequestBody JsonProspect input) {
        CreateProspectRequest request = jsonProspectToCreateProspectRequest(input);
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        try {
            createProspect.createProspect(request, presenter);
        } catch (InvalidEmailException | InvalidPhoneException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return presenter.getResponseEntity();
    }

    private CreateProspectRequest jsonProspectToCreateProspectRequest(JsonProspect prospect) {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = prospect.getFirstName();
        request.lastName = prospect.getLastName();
        request.email = prospect.getEmail();
        request.phone = prospect.getPhone();
        return request;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/api/prospect/{prospectId}")
    public ResponseEntity<JsonProspect> getProspectById(@PathVariable long prospectId) {
        ReadProspectRequest request = new ReadProspectRequest();
        request.prospectId = prospectId;
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        try {
            readProspect.readProspectById(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/prospect")
    public ResponseEntity<List<JsonProspect>> getProspectByParam(@ModelAttribute JsonProspect jsonProspect) {
        System.out.println(jsonProspect);
        try {
            return presentProspectsByParam(jsonProspect);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<List<JsonProspect>> presentProspectsByParam(JsonProspect prospect)
            throws NotFoundException {
        JsonProspectResponseModelListPresenter presenter = new JsonProspectResponseModelListPresenter();
        if (prospect.isEmpty()) {
            readProspect.readAllProspects(presenter);
        } else {
            readProspect.readProspectsByParam(getReadProspectRequestFromJsonProspect(prospect), presenter);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/prospect/{prospectId}")
    public ResponseEntity<JsonProspect> deleteProspect(@PathVariable long prospectId) {
        DeleteProspectRequest request = new DeleteProspectRequest(prospectId);
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        try {
            deleteProspect.deleteProspect(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/prospect/{prospectId}")
    public ResponseEntity<JsonProspect> updateProspect(@PathVariable long prospectId,
                                                             @RequestBody JsonProspect query) {
        UpdateProspectRequest request = new UpdateProspectRequest();
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        JsonProspectToUpdateProspectRequest(query, request);
        request.prospectId = prospectId;
        try {
            updateProspect.updateProspect(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidEmailException | InvalidPhoneException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/importprospects")
    public ResponseEntity<?> importProspects(@RequestParam("file") MultipartFile uploaded) {
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    private ReadProspectRequest getReadProspectRequestFromJsonProspect(JsonProspect prospect) {
        ReadProspectRequest request = new ReadProspectRequest();
        request.firstName = prospect.getFirstName();
        request.lastName = prospect.getLastName();
        request.email = prospect.getEmail();
        request.phone = prospect.getPhone();
        return request;
    }

    private void JsonProspectToReadProspectRequest(@ModelAttribute JsonProspect query, ReadProspectRequest request) {
        request.firstName = query.getFirstName();
        request.lastName = query.getLastName();
        request.email = query.getEmail();
        request.phone = query.getPhone();
    }

    private void JsonProspectToUpdateProspectRequest(JsonProspect query,
                                                     UpdateProspectRequest request) {
        request.firstName = query.getFirstName();
        request.lastName = query.getLastName();
        request.email = query.getEmail();
        request.phone = query.getPhone();
    }
}

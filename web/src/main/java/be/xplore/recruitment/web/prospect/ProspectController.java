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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/prospect")
public class ProspectController {
    @Autowired
    private CreateProspect createProspect;
    @Autowired
    private ReadProspect readProspect;
    @Autowired
    private UpdateProspect updateProspect;
    @Autowired
    private DeleteProspect deleteProspect;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<JsonProspect> addProspect(@RequestBody JsonProspect input) {
        CreateProspectRequest request = jsonProspectToCreateProspectRequest(input);
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        return tryAddProspect(request, presenter);
    }

    private ResponseEntity<JsonProspect> tryAddProspect(CreateProspectRequest request,
                                                        JsonProspectResponseModelPresenter presenter) {
        try {
            createProspect.createProspect(request, presenter);
        } catch (InvalidEmailException | InvalidPhoneException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return presenter.getResponseEntity();
    }

    private CreateProspectRequest jsonProspectToCreateProspectRequest(JsonProspect prospect) {
        return prospect.toCreateRequest();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{prospectId}")
    public ResponseEntity<JsonProspect> getProspectById(@PathVariable long prospectId) {
        ReadProspectRequest request = new ReadProspectRequest();
        request.prospectId = prospectId;
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        return tryReadProspectById(request, presenter);
    }

    private ResponseEntity<JsonProspect> tryReadProspectById(ReadProspectRequest request,
                                                             JsonProspectResponseModelPresenter presenter) {
        try {
            readProspect.readProspectById(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JsonProspect>> getProspectByParam(@ModelAttribute JsonProspect jsonProspect) {
        try {
            return presentProspectsByParam(jsonProspect);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<List<JsonProspect>> presentProspectsByParam(JsonProspect prospect) throws NotFoundException {
        JsonProspectResponseModelListPresenter presenter = new JsonProspectResponseModelListPresenter();
        readProspects(prospect, presenter);
        return presenter.getResponseEntity();
    }

    private void readProspects(JsonProspect prospect, JsonProspectResponseModelListPresenter presenter) {
        if (prospect.isEmpty()) {
            readProspect.readAllProspects(presenter);
        } else {
            readProspect.readProspectsByParam(prospect.toReadRequest(), presenter);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{prospectId}")
    public ResponseEntity<JsonProspect> deleteProspect(@PathVariable long prospectId) {
        DeleteProspectRequest request = new DeleteProspectRequest(prospectId);
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        return tryDeleteProspect(request, presenter);
    }

    private ResponseEntity<JsonProspect> tryDeleteProspect(DeleteProspectRequest request,
                                                           JsonProspectResponseModelPresenter presenter) {
        try {
            deleteProspect.deleteProspect(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{prospectId}")
    public ResponseEntity<JsonProspect> updateProspect(@PathVariable long prospectId, @RequestBody JsonProspect query) {
        UpdateProspectRequest request = query.toUpdateRequest(prospectId);
        JsonProspectResponseModelPresenter presenter = new JsonProspectResponseModelPresenter();
        return tryUpdateProspect(request, presenter);
    }

    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    private ResponseEntity<JsonProspect> tryUpdateProspect(UpdateProspectRequest request,
                                                           JsonProspectResponseModelPresenter presenter) {
        ResponseEntity<JsonProspect> responseEntity;
        try {
            updateProspect.updateProspect(request, presenter);
            responseEntity = presenter.getResponseEntity();
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidEmailException | InvalidPhoneException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return responseEntity;
    }
}

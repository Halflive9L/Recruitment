package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.prospect.CreateProspect;
import be.xplore.recruitment.domain.prospect.CreateProspectRequest;
import be.xplore.recruitment.domain.prospect.DeleteProspect;
import be.xplore.recruitment.domain.prospect.DeleteProspectRequest;
import be.xplore.recruitment.domain.prospect.ImportProspects;
import be.xplore.recruitment.domain.prospect.ImportProspectsRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */
@CrossOrigin
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

    @Autowired
    private ImportProspects importProspects;
    private final String prospectUrl = "/api/v1/prospect";

    @RequestMapping(method = RequestMethod.POST, value = prospectUrl)
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

    @RequestMapping(method = RequestMethod.GET, value = prospectUrl + "/{prospectId}")
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

    @RequestMapping(method = RequestMethod.GET, value = prospectUrl)
    public ResponseEntity<List<JsonProspect>> getProspectByParam(@ModelAttribute JsonProspect jsonProspect) {
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

    @RequestMapping(method = RequestMethod.DELETE, value = prospectUrl + "/{prospectId}")
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

    @RequestMapping(method = RequestMethod.PUT, value = prospectUrl+"/{prospectId}")
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

    @RequestMapping(method = RequestMethod.POST, value = "/api/importprospects")
    public ResponseEntity<JsonImportResult> importProspects(@RequestParam("file") MultipartFile uploaded) {
        JsonProspectImportResponseModelPresenter presenter = new JsonProspectImportResponseModelPresenter();
        try {
            ImportProspectsRequest request = new ImportProspectsRequest(uploaded.getInputStream());
            importProspects.importProspects(request, presenter);
            return presenter.getResponseEntity();
        } catch (IOException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private ReadProspectRequest getReadProspectRequestFromJsonProspect(JsonProspect prospect) {
        ReadProspectRequest request = new ReadProspectRequest();
        request.firstName = prospect.getFirstName();
        request.lastName = prospect.getLastName();
        request.email = prospect.getEmail();
        request.phone = prospect.getPhone();
        return request;
    }

    private void JsonProspectToUpdateProspectRequest(JsonProspect query,
                                                     UpdateProspectRequest request) {
        request.firstName = query.getFirstName();
        request.lastName = query.getLastName();
        request.email = query.getEmail();
        request.phone = query.getPhone();
    }
}

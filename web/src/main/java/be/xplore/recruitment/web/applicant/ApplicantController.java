package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.CreateApplicant;
import be.xplore.recruitment.domain.applicant.CreateApplicantRequest;
import be.xplore.recruitment.domain.applicant.DeleteApplicant;
import be.xplore.recruitment.domain.applicant.DeleteApplicantRequest;
import be.xplore.recruitment.domain.applicant.ReadApplicant;
import be.xplore.recruitment.domain.applicant.ReadApplicantRequest;
import be.xplore.recruitment.domain.applicant.UpdateApplicant;
import be.xplore.recruitment.domain.applicant.UpdateApplicantRequest;
import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
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
@RequestMapping("/api/v1/applicant")
public class ApplicantController {
    @Autowired
    private CreateApplicant createApplicant;
    @Autowired
    private ReadApplicant readApplicant;
    @Autowired
    private UpdateApplicant updateApplicant;
    @Autowired
    private DeleteApplicant deleteApplicant;
    private JsonApplicantResponseModelPresenter presenter = new JsonApplicantResponseModelPresenter();
    private JsonApplicantResponseModelListPresenter listPresenter = new JsonApplicantResponseModelListPresenter();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<JsonApplicant> addApplicant(@RequestBody JsonApplicant input) {
        try {
            createApplicant.createApplicant(ApplicantRequestBuilder.buildCreateRequest(input), presenter);
        } catch (InvalidEmailException | InvalidPhoneException | InvalidDateException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{applicantId}")
    public ResponseEntity<JsonApplicant> getApplicantById(@PathVariable long applicantId) {
        ReadApplicantRequest request = createReadRequest(applicantId);
        try {
            readApplicant.readApplicantById(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    private ReadApplicantRequest createReadRequest(@PathVariable long applicantId) {
        ReadApplicantRequest request = new ReadApplicantRequest();
        request.applicantId = applicantId;
        return request;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JsonApplicant>> getApplicantByParam(@ModelAttribute JsonApplicant jsonApplicant) {
        try {
            return presentApplicantsByParam(jsonApplicant);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<List<JsonApplicant>> presentApplicantsByParam(JsonApplicant applicant)
            throws NotFoundException {
        if (applicant.isEmpty()) {
            readApplicant.readAllApplicants(listPresenter);
        } else {
            readApplicant.readApplicantsByParam(ApplicantRequestBuilder.buildReadRequest(applicant), listPresenter);
        }
        return listPresenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{applicantId}")
    public ResponseEntity<JsonApplicant> deleteApplicant(@PathVariable long applicantId) {
        DeleteApplicantRequest request = new DeleteApplicantRequest(applicantId);
        return getJsonApplicantResponseEntity(request, presenter);
    }

    private ResponseEntity<JsonApplicant> getJsonApplicantResponseEntity(
            DeleteApplicantRequest request, JsonApplicantResponseModelPresenter presenter) {
        try {
            deleteApplicant.deleteApplicant(request, presenter);
            return presenter.getResponseEntity();
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{applicantId}")
    public ResponseEntity<JsonApplicant> updateApplicant(@PathVariable long applicantId,
                                                         @RequestBody JsonApplicant applicant) {
        UpdateApplicantRequest request = ApplicantRequestBuilder.buildUpdateRequest(applicantId, applicant);
        ResponseEntity<JsonApplicant> responseEntity = getJsonApplicantResponseEntity(request, presenter);
        return responseEntity;
    }

    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    private ResponseEntity<JsonApplicant> getJsonApplicantResponseEntity(
            UpdateApplicantRequest request, JsonApplicantResponseModelPresenter presenter) {
        ResponseEntity<JsonApplicant> result;
        try {
            result = updateApplicant(request, presenter);
        } catch (NotFoundException e) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidDateException | InvalidEmailException | InvalidPhoneException e) {
            result = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return result;
    }

    private ResponseEntity<JsonApplicant> updateApplicant(UpdateApplicantRequest request,
                                                          JsonApplicantResponseModelPresenter presenter) {
        updateApplicant.updateApplicant(request, presenter);
        return presenter.getResponseEntity();
    }
}


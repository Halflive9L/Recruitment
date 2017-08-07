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

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */

@CrossOrigin
@RestController
public class ApplicantController {
    @Autowired
    private CreateApplicant createApplicant;

    @Autowired
    private ReadApplicant readApplicant;

    @Autowired
    private UpdateApplicant updateApplicant;

    @Autowired
    private DeleteApplicant deleteApplicant;

    private final String applicantUrl = "/api/v1/applicant";

    @RequestMapping(method = RequestMethod.POST, value = applicantUrl)
    public ResponseEntity<List<JsonApplicant>> addApplicant(@RequestBody JsonApplicant input) {
        System.out.println("Input:" + input);
        CreateApplicantRequest request = getCreateRequestFromJsonApplicant(input);
        JsonApplicantResponseModelPresenter presenter = new JsonApplicantResponseModelPresenter();
        try {
            createApplicant.createApplicant(request, presenter);
        } catch (InvalidEmailException | InvalidPhoneException | InvalidDateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = applicantUrl + "/{applicantId}")
    public ResponseEntity<List<JsonApplicant>> getApplicantById(@PathVariable long applicantId) {
        ReadApplicantRequest request = new ReadApplicantRequest();
        request.applicantId = applicantId;
        JsonApplicantResponseModelPresenter presenter = new JsonApplicantResponseModelPresenter();
        try {
            readApplicant.readApplicantById(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = applicantUrl)
    public ResponseEntity<List<JsonApplicant>> getApplicantByParam(@ModelAttribute JsonApplicant jsonApplicant) {
        System.out.println(jsonApplicant);
        try {
            return presentApplicantsByParam(jsonApplicant);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<List<JsonApplicant>> presentApplicantsByParam(JsonApplicant applicant)
            throws NotFoundException {
        JsonApplicantResponseModelPresenter presenter = new JsonApplicantResponseModelPresenter();
        if (applicant.isEmpty()) {
            readApplicant.readAllApplicants(presenter);
        } else {
            readApplicant.readApplicantsByParam(getReadApplicantRequestFromJsonApplicant(applicant), presenter);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = applicantUrl+"{applicantId}")
    public ResponseEntity<List<JsonApplicant>> deleteApplicant(@PathVariable long applicantId) {
        DeleteApplicantRequest request = new DeleteApplicantRequest(applicantId);
        JsonApplicantResponseModelPresenter presenter = new JsonApplicantResponseModelPresenter();
        try {
            deleteApplicant.deleteApplicant(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.PUT, value = applicantUrl+"{applicantId}")
    public ResponseEntity<List<JsonApplicant>> updateApplicant(@PathVariable long applicantId,
                                                               @RequestBody JsonApplicant applicant) {
        UpdateApplicantRequest request = getUpdateApplicantRequestFromJsonApplicant(applicantId, applicant);
        JsonApplicantResponseModelPresenter presenter = new JsonApplicantResponseModelPresenter();
        try {
            updateApplicant.updateApplicant(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidDateException | InvalidEmailException | InvalidPhoneException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return presenter.getResponseEntity();
    }

    private CreateApplicantRequest getCreateRequestFromJsonApplicant(JsonApplicant applicant) {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        return request;
    }

    private ReadApplicantRequest getReadApplicantRequestFromJsonApplicant(JsonApplicant applicant) {
        ReadApplicantRequest request = new ReadApplicantRequest();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        return request;
    }

    private UpdateApplicantRequest getUpdateApplicantRequestFromJsonApplicant(long id, JsonApplicant applicant) {
        UpdateApplicantRequest request = new UpdateApplicantRequest(id);
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        return request;
    }
}

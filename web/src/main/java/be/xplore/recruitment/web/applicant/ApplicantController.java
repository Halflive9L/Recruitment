package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.CreateApplicant;
import be.xplore.recruitment.domain.applicant.CreateApplicantRequest;
import be.xplore.recruitment.domain.applicant.DeleteApplicant;
import be.xplore.recruitment.domain.applicant.ReadApplicant;
import be.xplore.recruitment.domain.applicant.ReadApplicantRequest;
import be.xplore.recruitment.domain.applicant.UpdateApplicant;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
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
 * @since 7/20/2017
 */
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

    @RequestMapping(method = RequestMethod.POST, value = "/applicant")
    public ResponseEntity<JsonApplicant> addApplicant(@RequestBody JsonApplicant input) {
        CreateApplicantRequest request = getCreateRequestFromJsonApplicant(input);
        try {
            createApplicant.createApplicant(request, applicantId -> {
            });
        } catch (InvalidEmailException | InvalidPhoneException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicant/{applicantId}")
    public ResponseEntity<JsonApplicant> getApplicantById(@PathVariable long applicantId) {
        ReadApplicantRequest request = new ReadApplicantRequest();
        request.applicantId = applicantId;
        JsonApplicantResponseModelPresenter presenter = new JsonApplicantResponseModelPresenter();
        readApplicant.readApplicantById(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicant")
    public ResponseEntity<List<JsonApplicant>> getApplicantByParam(@ModelAttribute JsonApplicant applicant) {
        try {
            presentApplicantsByParam(applicant);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void presentApplicantsByParam(JsonApplicant applicant) throws NotFoundException {
        if (applicant.isEmpty()) {
            readApplicant.readAllApplicants(applicants -> {
            });
        } else {
            readApplicant.readApplicantsByParam(getReadApplicantRequestFromJsonApplicant(applicant), applicants -> {
            });
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/applicant/{applicantId}")
    public ResponseEntity<JsonApplicant> deleteApplicant(@PathVariable long applicantId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/applicant/{applicantId}")
    public ResponseEntity<JsonApplicant> updateApplicant(@PathVariable long applicantId,
                                                         @RequestBody JsonApplicant applicant) {
        return new ResponseEntity<>(HttpStatus.OK);
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
}

package be.xplore.recruitment.web.applicant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
@RestController
public class ApplicantController {
    @RequestMapping(method = RequestMethod.POST, value = "/applicant")
    public ResponseEntity<JsonApplicant> addApplicant(@RequestBody JsonApplicant input) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicant/{applicantId}")
    public ResponseEntity<JsonApplicant> getApplicantById(@PathVariable long applicantId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicant")
    public ResponseEntity<JsonApplicant> getApplicantByParam(@ModelAttribute JsonApplicant applicant) {
        return new ResponseEntity<>(HttpStatus.OK);
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
}

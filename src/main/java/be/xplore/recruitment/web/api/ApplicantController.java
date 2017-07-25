package be.xplore.recruitment.web.api;

import be.xplore.recruitment.domain.model.Applicant;
import be.xplore.recruitment.domain.model.exceptions.NotFoundException;
import be.xplore.recruitment.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
@RestController
public class ApplicantController {
    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/applicant")
    public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant input) {
        Applicant applicant = new Applicant(input);
        applicantRepository.save(applicant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicant/{applicantId}")
    public Applicant getApplicantById(@PathVariable long applicantId) {
        Applicant applicant = applicantRepository.findOne(applicantId);
        if (applicant == null)
            throw new NotFoundException(applicantId);
        return applicant;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicant")
    public List<Applicant> getApplicantByParam(@ModelAttribute ApplicantQuery query) {
        return applicantRepository.findAll(query);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/applicant/{applicantId}")
    public Applicant deleteApplicant(@PathVariable long applicantId) {
        Applicant applicant = applicantRepository.findOne(applicantId);
        if (applicant == null) throw new NotFoundException(applicantId);
        applicantRepository.delete(applicant);
        return applicant;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/applicant/{applicantId}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable long applicantId, @RequestBody Applicant applicant) {
        Applicant foundApplicant = applicantRepository.findOne(applicantId);
        applicant.setApplicantId(foundApplicant.getApplicantId());
        applicantRepository.save(applicant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

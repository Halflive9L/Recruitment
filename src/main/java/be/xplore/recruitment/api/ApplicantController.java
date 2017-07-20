package be.xplore.recruitment.api;

import be.xplore.recruitment.model.Applicant;
import be.xplore.recruitment.model.exceptions.NotFoundException;
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

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @RequestMapping(method = RequestMethod.GET, value = "/applicant/{applicantId}")
    public Applicant applicant(@PathVariable long applicantId) {
        Applicant applicant = applicantRepository.findOne(applicantId);
        if (applicant == null)
            throw new NotFoundException(applicantId);
        return applicant;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicant")
    public List<Applicant> applicant(@ModelAttribute ApplicantQuery query) {
        System.out.println("query = " + query);
        return applicantRepository.findAll(query);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/applicant/{applicantId}")
    public Applicant deleteApplicant(@PathVariable long applicantId) {
        Applicant applicant = applicantRepository.findOne(applicantId);
        if (applicant == null) throw new NotFoundException(applicantId);
        applicantRepository.delete(applicant);
        return applicant;
    }
}

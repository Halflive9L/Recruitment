package be.xplore.recruitment.web.applicant.tag;

import be.xplore.recruitment.domain.applicant.tag.AddApplicantTag;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
@RestController
@RequestMapping("/api/v1/applicant/tag")
public class ApplicantTagController {

    private final AddApplicantTag addApplicantTag;

    @Autowired
    public ApplicantTagController(AddApplicantTag addApplicantTag) {
        this.addApplicantTag = addApplicantTag;
    }

    @RequestMapping(value = "/{applicantId}", method = RequestMethod.POST)
    public ResponseEntity<String> addTagToApplicant(@PathVariable("applicantId") long applicantId,
                                                    @RequestBody String tag) {
        AddTagToEntityRequest request = new AddTagToEntityRequest(applicantId, tag);
        AddApplicantTagPresenter presenter = new AddApplicantTagPresenter();
        addApplicantTag.addApplicantTag(request, presenter);
        return presenter.getResponseEntity();
    }
}

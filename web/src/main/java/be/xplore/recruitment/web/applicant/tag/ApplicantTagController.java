package be.xplore.recruitment.web.applicant.tag;

import be.xplore.recruitment.domain.applicant.tag.AddApplicantTag;
import be.xplore.recruitment.domain.applicant.tag.RemoveApplicantTag;
import be.xplore.recruitment.domain.tag.AddAllTagsToEntityRequest;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;
import be.xplore.recruitment.domain.tag.RemoveTagFromEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
@RestController
@RequestMapping("/api/v1/applicant/{applicantId}/tag")
public class ApplicantTagController {

    private final AddApplicantTag addApplicantTag;
    private final RemoveApplicantTag removeApplicantTag;

    @Autowired
    public ApplicantTagController(AddApplicantTag addApplicantTag,
                                  RemoveApplicantTag removeApplicantTag) {
        this.addApplicantTag = addApplicantTag;
        this.removeApplicantTag = removeApplicantTag;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addTagToApplicant(@PathVariable("applicantId") long applicantId,
                                                    @RequestBody String tag) {
        AddTagToEntityRequest request = new AddTagToEntityRequest(applicantId, tag);
        AddApplicantTagPresenter presenter = new AddApplicantTagPresenter();
        addApplicantTag.addApplicantTag(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Set<String>> addAllTagsToApplicant(@PathVariable("applicantId") long applicantId,
                                                             @RequestBody String[] tags) {
        AddAllTagsToEntityRequest request = new AddAllTagsToEntityRequest(applicantId, asSet(tags));
        AddAllApplicantTagsPresenter presenter = new AddAllApplicantTagsPresenter();
        addApplicantTag.addAllTags(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> removeTagFromApplicant(@PathVariable("applicantId") long applicantId,
                                                         @RequestBody String tag) {
        RemoveTagFromEntityRequest request = new RemoveTagFromEntityRequest(applicantId, tag);
        RemoveTagFromApplicantPresenter presenter = new RemoveTagFromApplicantPresenter();
        removeApplicantTag.removeApplicantTag(request, presenter);
        return presenter.getResponseEntity();
    }
}

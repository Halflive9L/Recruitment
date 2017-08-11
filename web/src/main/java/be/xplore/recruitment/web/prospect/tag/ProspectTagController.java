package be.xplore.recruitment.web.prospect.tag;

import be.xplore.recruitment.domain.prospect.tag.AddProspectTag;
import be.xplore.recruitment.domain.tag.AddAllTagsToEntityRequest;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;
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
@RequestMapping("/api/v1/prospect/{prospectId}/tag")
public class ProspectTagController {

    private final AddProspectTag addProspectTag;

    @Autowired
    public ProspectTagController(AddProspectTag addProspectTag) {
        this.addProspectTag = addProspectTag;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addTagToProspect(@PathVariable("prospectId") long prospectId,
                                                   @RequestBody String tag) {
        AddTagToEntityRequest request = new AddTagToEntityRequest(prospectId, tag);
        AddProspectTagPresenter presenter = new AddProspectTagPresenter();
        addProspectTag.addProspectTag(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Set<String>> addAllTagsToProspect(@PathVariable("prospectId") long prospectId,
                                                            @RequestBody String[] tags) {
        AddAllTagsToEntityRequest request = new AddAllTagsToEntityRequest(prospectId, asSet(tags));
        AddAllProspectTagsPresenter presenter = new AddAllProspectTagsPresenter();
        addProspectTag.addAllTags(request, presenter);
        return presenter.getResponseEntity();
    }
}

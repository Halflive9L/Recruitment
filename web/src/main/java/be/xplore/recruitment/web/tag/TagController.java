package be.xplore.recruitment.web.tag;

import be.xplore.recruitment.domain.tag.ReadAllTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
@RequestMapping("/api/v1/tags")
@RestController
public class TagController {
    private final ReadAllTags readAllTags;

    @Autowired
    public TagController(ReadAllTags readAllTags) {
        this.readAllTags = readAllTags;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getAllTags() {
        ReadAllTagsPresenter presenter = new ReadAllTagsPresenter();
        readAllTags.readAllTags(presenter);
        return presenter.getResponseEntity();
    }

}

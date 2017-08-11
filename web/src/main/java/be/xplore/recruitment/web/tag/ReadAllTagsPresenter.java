package be.xplore.recruitment.web.tag;

import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.tag.TagSetResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public class ReadAllTagsPresenter implements Consumer<TagSetResponseModel> {
    private ResponseEntity<Set<String>> responseEntity;

    @Override
    public void accept(TagSetResponseModel tagSetResponseModel) {
        Set<String> body = tagSetResponseModel.getTags().stream().map(Tag::getTagName).collect(Collectors.toSet());
        responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<Set<String>> getResponseEntity() {
        return responseEntity;
    }
}

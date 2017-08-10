package be.xplore.recruitment.domain.tag;

import be.xplore.recruitment.domain.exception.TagAlreadyExistsException;

import javax.inject.Named;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
@Named
public class CreateTagUseCase implements CreateTag {
    private final TagRepository repository;

    public CreateTagUseCase(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTag(CreateTagRequest request, Consumer<TagResponseModel> response) {
        Tag tag = tryCreateTag(request);
        response.accept(getTagResponseModelFromTag(tag));
    }

    private Tag tryCreateTag(CreateTagRequest request) {
        try {
            return (repository.createTag(new Tag(request.getName())));
        } catch (TagAlreadyExistsException e) {
            return null;
        }
    }

    private TagResponseModel getTagResponseModelFromTag(Tag tag) {
        if (tag == null) {
            return new TagResponseModel();
        }
        return new TagResponseModel(tag.getTagId(), tag.getTagName());
    }
}

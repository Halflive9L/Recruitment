package be.xplore.recruitment.domain.tag;

import javax.inject.Named;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
@Named
public class ReadAllTagsUseCase implements ReadAllTags {
    private final TagRepository repository;

    public ReadAllTagsUseCase(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public void readAllTags(Consumer<TagSetResponseModel> response) {
        Set<Tag> tags = repository.findAllTags();
        TagSetResponseModel responseModel = new TagSetResponseModel(tags);
        response.accept(responseModel);
    }
}

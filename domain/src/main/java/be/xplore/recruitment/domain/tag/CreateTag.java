package be.xplore.recruitment.domain.tag;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public interface CreateTag {
    void createTag(CreateTagRequest request, Consumer<CreateTagResponseModel> response);
}

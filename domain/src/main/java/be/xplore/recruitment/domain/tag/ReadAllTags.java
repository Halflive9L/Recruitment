package be.xplore.recruitment.domain.tag;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public interface ReadAllTags {
    void readAllTags(Consumer<TagSetResponseModel> response);
}

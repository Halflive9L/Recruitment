package be.xplore.recruitment.domain.prospect.tag;

import be.xplore.recruitment.domain.tag.AddAllTagsToEntityRequest;
import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public interface AddProspectTag {
    void addProspectTag(AddTagToEntityRequest request, Consumer<AddTagResponseModel> response);

    void addAllTags(AddAllTagsToEntityRequest request, Consumer<List<AddTagResponseModel>> presenter);
}

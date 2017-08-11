package be.xplore.recruitment.domain.prospect.tag;

import be.xplore.recruitment.domain.tag.RemoveTagFromEntityRequest;
import be.xplore.recruitment.domain.tag.RemoveTagResponseModel;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public interface RemoveProspectTag {
    void removeProspectTag(RemoveTagFromEntityRequest request, Consumer<RemoveTagResponseModel> response);
}

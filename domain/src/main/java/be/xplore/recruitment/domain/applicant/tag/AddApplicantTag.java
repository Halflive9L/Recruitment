package be.xplore.recruitment.domain.applicant.tag;

import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public interface AddApplicantTag {
    void addApplicantTag(AddTagToEntityRequest request, Consumer<AddTagResponseModel> response);
}

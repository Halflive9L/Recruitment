package be.xplore.recruitment.domain.applicant.tag;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;
import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;
import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.tag.TagRepository;

import javax.inject.Named;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
@Named
public class AddApplicantTagUseCase implements AddApplicantTag {
    private final ApplicantRepository applicantRepository;
    private final TagRepository tagRepository;

    public AddApplicantTagUseCase(ApplicantRepository applicantRepository,
                                  TagRepository tagRepository) {
        this.applicantRepository = applicantRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void addApplicantTag(AddTagToEntityRequest request, Consumer<AddTagResponseModel> response) {
        if (!applicantRepository.findApplicantById(request.getEntityId()).isPresent()) {
            response.accept(new AddTagResponseModel(null, false));
            return;
        }
        acceptValidResponse(request, response);
    }

    private void acceptValidResponse(AddTagToEntityRequest request, Consumer<AddTagResponseModel> response) {
        Tag tag = getTagFromRepo(request.getTagName());
        response.accept(getResponseModel(request.getEntityId(), tag));
    }

    private Tag getTagFromRepo(String tagName) {
        Optional<Tag> optional = tagRepository.findTagByName(tagName);
        return optional.orElseGet(() -> tagRepository.createTag(tagName));
    }

    private AddTagResponseModel getResponseModel(long applicantId, Tag tag) {
        try {
            applicantRepository.addTagToApplicant(applicantId, tag);
            return new AddTagResponseModel(tag.getTagName(), false);
        } catch (EntityAlreadyHasTagException e) {
            return new AddTagResponseModel(tag.getTagName(), true);
        }
    }
}

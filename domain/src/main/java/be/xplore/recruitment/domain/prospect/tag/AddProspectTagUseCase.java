package be.xplore.recruitment.domain.prospect.tag;

import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
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
public class AddProspectTagUseCase implements AddProspectTag {
    private final ProspectRepository prospectRepository;
    private final TagRepository tagRepository;

    public AddProspectTagUseCase(ProspectRepository prospectRepository,
                                 TagRepository tagRepository) {
        this.prospectRepository = prospectRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void addProspectTag(AddTagToEntityRequest request, Consumer<AddTagResponseModel> response) {
        if (!prospectRepository.findProspectById(request.getEntityId()).isPresent()) {
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

    private AddTagResponseModel getResponseModel(long prospectId, Tag tag) {
        try {
            prospectRepository.addTagToProspect(prospectId, tag);
            return new AddTagResponseModel(tag.getTagName(), false);
        } catch (EntityAlreadyHasTagException e) {
            return new AddTagResponseModel(e.getMessage(), true);
        }
    }
}

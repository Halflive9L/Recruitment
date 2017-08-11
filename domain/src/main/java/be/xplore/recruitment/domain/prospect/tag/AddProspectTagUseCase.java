package be.xplore.recruitment.domain.prospect.tag;

import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
import be.xplore.recruitment.domain.tag.AddAllTagsToEntityRequest;
import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;
import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.tag.TagRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        if (!doesProspectExist(request.getEntityId())) {
            response.accept(null);
            return;
        }
        acceptValidResponse(request, response);
    }

    private void acceptValidResponse(AddTagToEntityRequest request, Consumer<AddTagResponseModel> response) {
        Tag tag = getTagFromRepo(request.getTagName());
        response.accept(getResponseModel(request.getEntityId(), tag));
    }

    private AddTagResponseModel getResponseModel(long prospectId, Tag tag) {
        try {
            prospectRepository.addTagToProspect(prospectId, tag);
            return new AddTagResponseModel(tag.getTagName(), false);
        } catch (EntityAlreadyHasTagException e) {
            return new AddTagResponseModel(e.getMessage(), true);
        }
    }

    @Override
    public void addAllTags(AddAllTagsToEntityRequest request, Consumer<List<AddTagResponseModel>> response) {
        if (!doesProspectExist(request.getEntityId())) {
            response.accept(null);
            return;
        }
        acceptValidResponseList(request, response);
    }

    private void acceptValidResponseList(AddAllTagsToEntityRequest request,
                                         Consumer<List<AddTagResponseModel>> response) {
        Set<Tag> tags = request.getTagNames().stream().map(this::getTagFromRepo).collect(Collectors.toSet());
        tags = prospectRepository.addAllTagsToProspect(request.getEntityId(), tags);
        response.accept(tags.stream().map(tag -> new AddTagResponseModel(tag.getTagName(), false))
                                .collect(Collectors.toList()));
    }

    private boolean doesProspectExist(long prospectId) {
        return prospectRepository.findProspectById(prospectId).isPresent();
    }

    private Tag getTagFromRepo(String tagName) {
        Optional<Tag> optional = tagRepository.findTagByName(tagName);
        return optional.orElseGet(() -> tagRepository.createTag(tagName));
    }
}

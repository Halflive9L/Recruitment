package be.xplore.recruitment.domain.applicant.tag;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;
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
        if (!doesApplicantExist(request.getEntityId())) {
            response.accept(null);
            return;
        }
        acceptValidResponse(request, response);
    }

    private void acceptValidResponse(AddTagToEntityRequest request, Consumer<AddTagResponseModel> response) {
        Tag tag = getTagFromRepo(request.getTagName());
        response.accept(getResponseModel(request.getEntityId(), tag));
    }

    private AddTagResponseModel getResponseModel(long applicantId, Tag tag) {
        try {
            applicantRepository.addTagToApplicant(applicantId, tag);
            return new AddTagResponseModel(tag.getTagName(), false);
        } catch (EntityAlreadyHasTagException e) {
            return new AddTagResponseModel(e.getMessage(), true);
        }
    }

    @Override
    public void addAllTags(AddAllTagsToEntityRequest request, Consumer<List<AddTagResponseModel>> response) {
        if (!doesApplicantExist(request.getEntityId())) {
            response.accept(null);
            return;
        }
        acceptValidResponseList(request, response);
    }

    private void acceptValidResponseList(AddAllTagsToEntityRequest request,
                                         Consumer<List<AddTagResponseModel>> response) {
        Set<Tag> tags = request.getTagNames().stream().map(this::getTagFromRepo).collect(Collectors.toSet());
        tags = applicantRepository.addAllTagsToApplicant(request.getEntityId(), tags);
        response.accept(tags.stream().map(tag -> new AddTagResponseModel(tag.getTagName(), false))
                                .collect(Collectors.toList()));
    }

    private boolean doesApplicantExist(long applicantId) {
        return applicantRepository.findApplicantById(applicantId).isPresent();
    }

    private Tag getTagFromRepo(String tagName) {
        Optional<Tag> optional = tagRepository.findTagByName(tagName);
        return optional.orElseGet(() -> tagRepository.createTag(tagName));
    }
}

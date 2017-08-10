package be.xplore.recruitment.domain.applicant.tag;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import be.xplore.recruitment.domain.tag.AddTagToEntityRequest;
import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.tag.TagRepository;

import javax.inject.Named;
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
            response.accept(new AddTagResponseModel());
            return;
        }
        Tag tag = getTagFromRepo(request.getTagName());
        tag = applicantRepository.addTagToApplicant(request.getEntityId(), tag);
        response.accept(new AddTagResponseModel(tag.getTagName()));
    }

    private Tag getTagFromRepo(String tagName) {
        return tagRepository.findTagByName(tagName)
                .orElse(tagRepository.createTag(tagName));
    }
}

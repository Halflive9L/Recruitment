package be.xplore.recruitment.domain.applicant.tag;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.tag.RemoveTagFromEntityRequest;
import be.xplore.recruitment.domain.tag.RemoveTagResponseModel;
import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.tag.TagRepository;

import javax.inject.Named;
import java.util.function.Consumer;


/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
@Named
public class RemoveApplicantTagUseCase implements RemoveApplicantTag {
    private final ApplicantRepository applicantRepository;
    private final TagRepository tagRepository;

    public RemoveApplicantTagUseCase(ApplicantRepository applicantRepository,
                                     TagRepository tagRepository) {
        this.applicantRepository = applicantRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void removeApplicantTag(RemoveTagFromEntityRequest request, Consumer<RemoveTagResponseModel> response) {
        Tag tagToRemove = tagRepository.findTagByName(request.getTag()).orElseThrow(NotFoundException::new);
        Tag tag = applicantRepository.removeTagFromApplicant(request.getEntityId(), tagToRemove)
                .orElseThrow(NotFoundException::new);
        response.accept(new RemoveTagResponseModel(tag.getTagName()));
    }
}

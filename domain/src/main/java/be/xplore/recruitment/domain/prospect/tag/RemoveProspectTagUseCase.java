package be.xplore.recruitment.domain.prospect.tag;

import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
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
public class RemoveProspectTagUseCase implements RemoveProspectTag {
    private final ProspectRepository prospectRepository;
    private final TagRepository tagRepository;

    public RemoveProspectTagUseCase(ProspectRepository prospectRepository,
                                    TagRepository tagRepository) {
        this.prospectRepository = prospectRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void removeProspectTag(RemoveTagFromEntityRequest request, Consumer<RemoveTagResponseModel> response) {
        Tag tagToRemove = tagRepository.findTagByName(request.getTag()).orElseThrow(NotFoundException::new);
        Tag tag = prospectRepository.removeTagFromProspect(request.getEntityId(), tagToRemove)
                .orElseThrow(NotFoundException::new);
        response.accept(new RemoveTagResponseModel(tag.getTagName()));
    }
}

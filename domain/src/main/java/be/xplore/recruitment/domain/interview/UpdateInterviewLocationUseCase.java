package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
@Named
public class UpdateInterviewLocationUseCase implements UpdateInterviewLocation {
    private final InterviewRepository repository;

    public UpdateInterviewLocationUseCase(InterviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateInterviewLocation(UpdateInterviewLocationRequest request,
                                        Consumer<InterviewResponseModel> response)
            throws NotFoundException {
        Interview interview = repository.updateInterviewLocation(request.getInterviewId(), request.getLocation())
                .orElseThrow(NotFoundException::new);
        InterviewResponseModel responseModel = InterviewResponseModel.fromInterview(interview);
        response.accept(responseModel);
    }
}

package be.xplore.recruitment.domain.interview;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public interface UpdateInterviewLocation {
    void updateInterviewLocation(UpdateInterviewLocationRequest request,
                                 Consumer<InterviewResponseModel> location);
}

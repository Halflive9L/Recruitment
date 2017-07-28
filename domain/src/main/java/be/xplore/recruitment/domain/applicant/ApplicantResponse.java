package be.xplore.recruitment.domain.applicant;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public interface ApplicantResponse<T> {
    void onResponse(Consumer<T> consumer);
}

package be.xplore.recruitment.domain.applicant;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public interface ReadApplicantsByParamResponse {
    void onResponse(List<Applicant> applicants);
}

package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.CreateApplicantResponse;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
@ResponseBody
public class CreateApplicantPresenter implements CreateApplicantResponse {
    @Override
    public void onResponse(long ApplicantId) {
        
    }
}

package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.ApplicantResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class JsonApplicantResponseModelPresenter implements Consumer<ApplicantResponseModel> {
    private ResponseEntity<JsonApplicant> responseEntity;

    @Override
    public void accept(ApplicantResponseModel applicantResponseModel) {
        JsonApplicant responseBody = JsonApplicant.asJsonApplicant(applicantResponseModel);
        this.responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<JsonApplicant> getResponseEntity() {
        return responseEntity;
    }
}

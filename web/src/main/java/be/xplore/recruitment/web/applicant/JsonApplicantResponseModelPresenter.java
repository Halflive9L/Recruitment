package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.ApplicantResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public class JsonApplicantResponseModelPresenter implements Consumer<ApplicantResponseModel> {
    private ResponseEntity<JsonApplicant> responseEntity;

    @Override
    public void accept(ApplicantResponseModel applicantResponseModel) {
        if (applicantResponseModel.isEmpty()) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(JsonApplicant.asJsonApplicant(applicantResponseModel), HttpStatus.OK);
        }
    }

    public ResponseEntity<JsonApplicant> getResponseEntity(){
        return this.responseEntity;
    }
}

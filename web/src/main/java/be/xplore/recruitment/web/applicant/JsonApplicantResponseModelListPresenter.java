package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.ApplicantResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public class JsonApplicantResponseModelListPresenter implements Consumer<List<ApplicantResponseModel>> {
    private ResponseEntity<List<JsonApplicant>> responseEntity;

    @Override
    public void accept(List<ApplicantResponseModel> applicantResponseModel) {
        List<JsonApplicant> responseBody = new ArrayList<>();
        applicantResponseModel.forEach(responseModel -> responseBody.add(JsonApplicant.asJsonApplicant(responseModel)));
        responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<List<JsonApplicant>> getResponseEntity() {
        return this.responseEntity;
    }
}

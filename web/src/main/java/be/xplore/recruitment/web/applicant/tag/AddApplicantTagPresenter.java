package be.xplore.recruitment.web.applicant.tag;

import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class AddApplicantTagPresenter implements Consumer<AddTagResponseModel> {
    private ResponseEntity<String> responseEntity;

    @Override
    public void accept(AddTagResponseModel responseModel) {
        if (responseModel.getTagName() == null) {
            responseEntity = new ResponseEntity<>("Applicant does not exist", HttpStatus.NOT_FOUND);
        } else {
            responseEntity = responseEntityCheckRedundancy(responseModel);
        }
    }

    private ResponseEntity<String> responseEntityCheckRedundancy(AddTagResponseModel responseModel) {
        if (responseModel.isRedundantTag()) {
            return new ResponseEntity<>(responseModel.getTagName(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(responseModel.getTagName(), HttpStatus.OK);
    }

    public ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }
}

package be.xplore.recruitment.web.prospect.tag;

import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class AddProspectTagPresenter implements Consumer<AddTagResponseModel> {
    private ResponseEntity<String> responseEntity;

    @Override
    public void accept(AddTagResponseModel responseModel) {
        String body = responseModel.getTagName();
        responseEntity = body != null ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>("Prospect does not exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }
}

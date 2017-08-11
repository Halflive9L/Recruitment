package be.xplore.recruitment.web.applicant.tag;

import be.xplore.recruitment.domain.tag.AddTagResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public class AddAllApplicantTagsPresenter implements Consumer<List<AddTagResponseModel>> {
    private ResponseEntity<Set<String>> responseEntity;

    @Override

    public void accept(List<AddTagResponseModel> responseModels) {
        if (responseModels == null) {
            responseEntity = new ResponseEntity<>(Collections.singleton("Applicant does not exist"),
                                                  HttpStatus.NOT_FOUND);
        } else {
            Set<String> body = responseModels.stream().map(AddTagResponseModel::getTagName).collect(Collectors.toSet());
            responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    public ResponseEntity<Set<String>> getResponseEntity() {
        return responseEntity;
    }
}

package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ProspectResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Lander
 * @since 28/07/2017
 */
public class JsonProspectPresenter implements Consumer<List<ProspectResponseModel>> {
    private ResponseEntity<List<JsonProspect>> responseEntity;

    @Override
    public void accept(List<ProspectResponseModel> responseModel) {
        List<JsonProspect> responseBody = new ArrayList<>();
        responseModel.forEach(responseModel1 -> responseBody.add(JsonProspect
                .asJsonProspect(responseModel1)));
        this.responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<List<JsonProspect>> getResponseEntity() {
        return this.responseEntity;
    }

}

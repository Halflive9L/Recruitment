package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ProspectResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.stream;

/**
 * @author Lander
 * @since 28/07/2017
 */
public class JsonProspectPresenter implements Consumer<ProspectResponseModel> {
    private ResponseEntity<List<JsonProspect>> responseEntity;
    private List<JsonProspect> prospects = new ArrayList<>();
    private HttpStatus statuscode;

    @Override
    public void accept(ProspectResponseModel responseModel) {
        if(responseModel.isEmpty()) {
            statuscode = HttpStatus.NOT_FOUND;
        } else {
            prospects.add(JsonProspect.asJsonProspect(responseModel));
            statuscode = HttpStatus.OK;
        }
    }

    public ResponseEntity<List<JsonProspect>> getResponseEntity() {
        responseEntity = new ResponseEntity<List<JsonProspect>>(prospects, statuscode);
        return this.responseEntity;
    }

}

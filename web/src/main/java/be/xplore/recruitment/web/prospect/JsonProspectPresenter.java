package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ProspectResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Lander
 * @since 28/07/2017
 */
public class JsonProspectPresenter implements Consumer<ProspectResponseModel> {
    private ResponseEntity<JsonProspect> responseEntity;

    @Override
    public void accept(ProspectResponseModel responseModel) {
        if(responseModel.isEmpty()) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(JsonProspect.asJsonProspect(responseModel), HttpStatus.OK);
        }
    }

    public ResponseEntity<JsonProspect> getResponseEntity() {
        return this.responseEntity;
    }

}

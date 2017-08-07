package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ProspectResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class JsonProspectResponseModelPresenter implements Consumer<ProspectResponseModel> {
    private ResponseEntity<JsonProspect> responseEntity;

    @Override
    public void accept(ProspectResponseModel prospectResponseModel) {
        JsonProspect responseBody = JsonProspect.asJsonProspect(prospectResponseModel);
        responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    ResponseEntity<JsonProspect> getResponseEntity() {
        return responseEntity;
    }
}

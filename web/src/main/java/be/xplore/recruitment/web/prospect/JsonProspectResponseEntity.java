package be.xplore.recruitment.web.prospect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class JsonProspectResponseEntity extends ResponseEntity<JsonProspect> {
    public JsonProspectResponseEntity(HttpStatus status) {
        super(status);
    }
}

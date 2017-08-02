package be.xplore.recruitment.web.applicant;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static org.springframework.util.StreamUtils.copyToByteArray;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
class DownloadFilePresenter implements Consumer<InputStream> {
    private ResponseEntity<ByteArrayResource> responseEntity;

    @Override
    public void accept(InputStream input) {
        ByteArrayResource body;
        try {
            body = new ByteArrayResource(copyToByteArray(input));
            input.close();
        } catch (IOException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }
        responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
    }


    public ResponseEntity<ByteArrayResource> getResponseEntity() {
        return responseEntity;
    }
}

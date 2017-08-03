package be.xplore.recruitment.web.applicant.file;

import be.xplore.recruitment.domain.applicant.file.GetFileResponseModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static org.springframework.util.StreamUtils.copyToByteArray;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
class DownloadFilePresenter implements Consumer<GetFileResponseModel> {
    private ResponseEntity<ByteArrayResource> responseEntity;

    @Override
    public void accept(GetFileResponseModel responseModel) {
        ByteArrayResource body;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.CONTENT_TYPE, getContentTypeFromResponseModel(responseModel.getContentType()));

        try (InputStream inputStream = new FileInputStream(responseModel.getFile())) {
            body = new ByteArrayResource(copyToByteArray(inputStream));
        } catch (IOException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }
        responseEntity = new ResponseEntity<>(body, headers, HttpStatus.OK);
    }


    public ResponseEntity<ByteArrayResource> getResponseEntity() {
        return responseEntity;
    }

    private String getContentTypeFromResponseModel(String responseContentType) {
        if (responseContentType != null) {
            return responseContentType;
        }
        return MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }
}

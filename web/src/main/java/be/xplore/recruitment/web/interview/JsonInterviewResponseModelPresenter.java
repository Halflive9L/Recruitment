package be.xplore.recruitment.web.interview;

import be.xplore.recruitment.domain.interview.InterviewResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

public class JsonInterviewResponseModelPresenter implements Consumer<InterviewResponseModel> {
    private ResponseEntity<JsonInterview> responseEntity;

    @Override
    public void accept(InterviewResponseModel responseModel) {
        JsonInterview body = JsonInterview.asJsonInterview(responseModel);
        this.responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<JsonInterview> getResponseEntity() {
        return responseEntity;
    }
}

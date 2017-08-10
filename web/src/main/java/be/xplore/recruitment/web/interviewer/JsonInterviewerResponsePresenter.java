package be.xplore.recruitment.web.interviewer;

import be.xplore.recruitment.domain.interviewer.InterviewerResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

public class JsonInterviewerResponsePresenter implements Consumer<InterviewerResponseModel> {
    private ResponseEntity<JsonInterviewer> responseEntity;

    @Override
    public void accept(InterviewerResponseModel responseModel) {
        JsonInterviewer body = JsonInterviewer.asJsonInterviewer(responseModel);
        this.responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<JsonInterviewer> getResponseEntity() {
        return responseEntity;
    }
}

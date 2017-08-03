package be.xplore.recruitment.web.interviewer;

import be.xplore.recruitment.domain.interviewer.InterviewerResponseModel;
import be.xplore.recruitment.web.applicant.JsonApplicant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JsonInterviewerResponseModelListPresenter implements Consumer<List<InterviewerResponseModel>> {
    private ResponseEntity<List<JsonInterviewer>> responseEntity;

    @Override
    public void accept(List<InterviewerResponseModel> models) {
        List<JsonInterviewer> responseBody = models.stream()
                .map(JsonInterviewer::asJsonInterviewer)
                .collect(Collectors.toList());
        responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<List<JsonInterviewer>> getResponseEntity() {
        return responseEntity;
    }
}

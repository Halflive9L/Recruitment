package be.xplore.recruitment.web.interview;

import be.xplore.recruitment.domain.interview.InterviewResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JsonInterviewResponseModelListPresenter implements Consumer<List<InterviewResponseModel>> {
    private ResponseEntity<List<JsonInterview>> responseEntity;

    @Override
    public void accept(List<InterviewResponseModel> interviewResponseModels) {
        List<JsonInterview> interviews = interviewResponseModels.stream()
                .map(JsonInterview::asJsonInterview)
                .collect(Collectors.toList());
        this.responseEntity = new ResponseEntity<List<JsonInterview>>(interviews, HttpStatus.OK);
    }

    public ResponseEntity<List<JsonInterview>> getResponseEntity() {
        return responseEntity;
    }
}

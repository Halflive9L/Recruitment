package be.xplore.recruitment.web.interview;

import be.xplore.recruitment.domain.interview.ReadInterview;
import be.xplore.recruitment.domain.interview.ReadInterviewRequest;
import be.xplore.recruitment.domain.interview.ScheduleInterview;
import be.xplore.recruitment.domain.interview.ScheduleInterviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {
    @Autowired
    private ScheduleInterview scheduleInterview;

    @Autowired
    private ReadInterview readInterview;

    public InterviewController() {
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<JsonInterview> scheduleInterview(@RequestBody JsonInterview body) {
        JsonInterviewResponseModelPresenter presenter = new JsonInterviewResponseModelPresenter();
        ScheduleInterviewRequest request = ScheduleInterviewRequest.builder()
                .withApplicantId(body.getApplicantId())
                .withCreatedTime(body.getCreatedTime())
                .withScheduledTime(body.getScheduledTime())
                .withInterviewerIds(body.getInterviewerIds())
                .build();
        scheduleInterview.scheduleInterview(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{interviewId}")
    public ResponseEntity<JsonInterview> readInterviewById(@PathVariable long interviewId) {
        JsonInterviewResponseModelPresenter presenter = new JsonInterviewResponseModelPresenter();
        readInterview.readInterview(new ReadInterviewRequest(interviewId), presenter);
        return presenter.getResponseEntity();
    }
}

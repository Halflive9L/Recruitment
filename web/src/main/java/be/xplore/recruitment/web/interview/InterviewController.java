package be.xplore.recruitment.web.interview;

import be.xplore.recruitment.domain.interview.CancelInterview;
import be.xplore.recruitment.domain.interview.CancelInterviewRequest;
import be.xplore.recruitment.domain.interview.ReadInterview;
import be.xplore.recruitment.domain.interview.ReadInterviewRequest;
import be.xplore.recruitment.domain.interview.ScheduleInterview;
import be.xplore.recruitment.domain.interview.ScheduleInterviewRequest;
import be.xplore.recruitment.domain.interview.UpdateInterviewLocation;
import be.xplore.recruitment.domain.interview.UpdateInterviewLocationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interview")
public class InterviewController {
    private Logger LOGGER = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private ScheduleInterview scheduleInterview;

    @Autowired
    private ReadInterview readInterview;

    @Autowired
    private CancelInterview cancelInterview;

    @Autowired
    private UpdateInterviewLocation updateInterviewLocation;

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
                .withLocation(body.getLocation())
                .build();
        scheduleInterview.scheduleInterview(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JsonInterview>> readAll() {
        JsonInterviewResponseModelListPresenter presenter = new JsonInterviewResponseModelListPresenter();
        readInterview.readAll(presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{interviewId}")
    public ResponseEntity<JsonInterview> readInterviewById(@PathVariable long interviewId) {
        JsonInterviewResponseModelPresenter presenter = new JsonInterviewResponseModelPresenter();
        readInterview.readInterview(new ReadInterviewRequest(interviewId), presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cancel/{interviewId}")
    public ResponseEntity<JsonInterview> cancelInterviewById(@PathVariable long interviewId) {
        JsonInterviewResponseModelPresenter presenter = new JsonInterviewResponseModelPresenter();
        cancelInterview.cancelInterview(new CancelInterviewRequest(interviewId), presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{interviewId}/updatelocation",
            consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<JsonInterview> updateInterviewLocation(@PathVariable long interviewId,
                                                                 @RequestBody String location) {
        LOGGER.debug(location);
        JsonInterviewResponseModelPresenter presenter = new JsonInterviewResponseModelPresenter();
        UpdateInterviewLocationRequest request = new UpdateInterviewLocationRequest(interviewId, location);
        updateInterviewLocation.updateInterviewLocation(request, presenter);
        return presenter.getResponseEntity();
    }
}

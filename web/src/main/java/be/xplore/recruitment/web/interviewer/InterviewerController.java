package be.xplore.recruitment.web.interviewer;

import be.xplore.recruitment.domain.interviewer.CreateInterviewer;
import be.xplore.recruitment.domain.interviewer.CreateInterviewerRequest;
import be.xplore.recruitment.domain.interviewer.DeleteInterviewer;
import be.xplore.recruitment.domain.interviewer.DeleteInterviewerRequest;
import be.xplore.recruitment.domain.interviewer.ReadInterviewer;
import be.xplore.recruitment.domain.interviewer.ReadInterviewerRequest;
import be.xplore.recruitment.domain.interviewer.UpdateInterviewer;
import be.xplore.recruitment.domain.interviewer.UpdateInterviewerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interviewer")
public class InterviewerController {
    @Autowired
    private CreateInterviewer createInterviewer;

    @Autowired
    private ReadInterviewer readInterviewer;

    @Autowired
    private UpdateInterviewer updateInterviewer;

    @Autowired
    private DeleteInterviewer deleteInterviewer;

    public InterviewerController() {
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<JsonInterviewer>> getAllInterviewers() {
        JsonInterviewerResponseModelListPresenter presenter = new JsonInterviewerResponseModelListPresenter();
        readInterviewer.readAll(presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{interviewerId}")
    public ResponseEntity<JsonInterviewer> getInterviewerById(@PathVariable long interviewerId) {
        ReadInterviewerRequest request = new ReadInterviewerRequest(interviewerId);
        JsonInterviewerResponsePresenter presenter = new JsonInterviewerResponsePresenter();
        readInterviewer.readInterviewerById(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<JsonInterviewer> createInterviewer(@RequestBody JsonInterviewer body) {
        CreateInterviewerRequest request = new CreateInterviewerRequest(body.getFirstName(), body.getLastName());
        JsonInterviewerResponsePresenter presenter = new JsonInterviewerResponsePresenter();
        createInterviewer.createInterviewer(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{interviewerId}")
    public ResponseEntity<JsonInterviewer> deleteInterviewerById(@PathVariable long interviewerId) {
        DeleteInterviewerRequest request = new DeleteInterviewerRequest(interviewerId);
        JsonInterviewerResponsePresenter presenter = new JsonInterviewerResponsePresenter();
        deleteInterviewer.deleteInterviewer(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseEntity<JsonInterviewer> updateInterviewer(@RequestBody JsonInterviewer body) {
        UpdateInterviewerRequest request = new UpdateInterviewerRequest();
        request.setInterviewerId(body.getInterviewerId());
        request.setFirstName(body.getFirstName());
        request.setLastName(body.getLastName());
        JsonInterviewerResponsePresenter presenter = new JsonInterviewerResponsePresenter();
        updateInterviewer.updateInterviewer(request, presenter);
        return presenter.getResponseEntity();
    }
}

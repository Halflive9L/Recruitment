package be.xplore.recruitment;

import be.xplore.recruitment.domain.interview.Interview;
import be.xplore.recruitment.domain.interview.InterviewRepository;
import be.xplore.recruitment.domain.interview.RemindParticipants;
import be.xplore.recruitment.domain.interview.RemindParticipantsUseCase;
import be.xplore.recruitment.domain.interview.ReminderSender;
import be.xplore.recruitment.smtpreminder.SMTPConfig;
import be.xplore.recruitment.smtpreminder.SMTPReminderSender;
import be.xplore.recruitment.web.interview.JsonInterview;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import de.saly.javamail.mock2.MockMailbox;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.mail.Message;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InterviewTest extends TestBase {
    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private RemindParticipants remindParticipants;

    @Override
    protected JSONObject getJsonTestObject() {
        return null;
    }

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    @Ignore
    @DatabaseSetup(value = "/interview/InterviewTest.testData.xml")
    //@DatabaseTearDown(type = DatabaseOperation.TRUNCATE_TABLE)
    public void schedulesInterview() {
        JSONObject object = createInterviewObject(0, 1, Arrays.asList(1, 2));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(object.toJSONString(), headers);
        JsonInterview result = restTemplate
                .postForEntity("/api/interview/create", httpEntity, JsonInterview.class)
                .getBody();
        assertThat(result.getInterviewId()).isEqualTo(1);
        assertThat(result.getApplicantId()).isEqualTo(1);
        assertThat(result.getInterviewerIds()).contains(1L, 2L);
    }

    @Test
    @Ignore
    @DatabaseSetup(value = "/interview/InterviewTest.testDataWithInterviews.xml")
    public void testReadAllInterviews() {
        ParameterizedTypeReference<List<JsonInterview>> typeRef =
                new ParameterizedTypeReference<List<JsonInterview>>() {
                };
        List<JsonInterview> result = restTemplate
                .exchange("/api/interview/all", HttpMethod.GET, null, typeRef)
                .getBody();
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getApplicantId()).isEqualTo(1);
        assertThat(result.get(0).getInterviewerIds()).contains(1L, 3L);
        assertThat(result.get(1).getApplicantId()).isEqualTo(2);
        assertThat(result.get(1).getInterviewerIds()).contains(2L);
        assertThat(result.get(2).getApplicantId()).isEqualTo(1);
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testDataWithInterviews.xml")
    @Ignore
    //@DatabaseTearDown(type = DatabaseOperation.TRUNCATE_TABLE)
    public void testReadInterviewById() {
        JsonInterview interview = restTemplate.getForEntity("/api/interview/1", JsonInterview.class)
                .getBody();
        assertThat(interview.getInterviewId()).isEqualTo(1);
        assertThat(interview.getApplicantId()).isEqualTo(1);
        assertThat(interview.getInterviewerIds()).contains(1L, 3L);
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testDataWithInterviews.xml")
    @Ignore
    public void testCancelInterview() {
        JSONObject object = createInterviewObject(0, 1, Arrays.asList(1, 2));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(object.toJSONString(), headers);
        JsonInterview result = restTemplate
                .postForEntity("/api/interview/cancel/1", httpEntity, JsonInterview.class)
                .getBody();
        assertThat(result.isCancelled()).isTrue();
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testRemind.xml")
    @Ignore
    public void testRemindApplicantBeforeInterview() throws Exception {
        Interview interview = interviewRepository.findById(1).get();
        interview.setScheduledTime(LocalDateTime.now().plusHours(12));
        interviewRepository.updateInterviewer(interview);
        RemindParticipants useCase = remindParticipants;
        useCase.checkReminders();
        interview = interviewRepository.findById(1).get();
        assertThat(interview.isPreInterviewReminderSent()).isTrue();

        MockMailbox mb = MockMailbox.get("jos.vermeulen@example.com");
        Message[] messages = mb.getInbox().getMessages();
        assertThat(messages.length).isEqualTo(1);
    }

    private JSONObject createInterviewObject(long interviewId, long applicantId, List<Integer> interviewerIds) {
        JSONObject obj = new JSONObject();
        obj.put("interviewId", Long.toString(interviewId));
        obj.put("applicantId", Long.toString(applicantId));
        JSONArray jsonInterviewers = new JSONArray();
        jsonInterviewers.addAll(interviewerIds);
        obj.put("interviewerIds", jsonInterviewers);
        obj.put("createdTime", formatTime(LocalDateTime.now()));
        obj.put("scheduledTime", formatTime(LocalDateTime.now().plus(5, ChronoUnit.DAYS)));
        return obj;
    }

    private String formatTime(LocalDateTime time) {
        return time.toString();
    }
}

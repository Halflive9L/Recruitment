package be.xplore.recruitment;

import be.xplore.recruitment.domain.interview.Interview;
import be.xplore.recruitment.domain.interview.InterviewRepository;
import be.xplore.recruitment.domain.interview.RemindParticipants;
import be.xplore.recruitment.web.interview.JsonInterview;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import de.saly.javamail.mock2.MockMailbox;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Before
    public void setup() {
        MockMailbox.resetAll();
    }

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testData.xml")
    public void schedulesInterview() {
        JSONObject object = createInterviewObject(0, 1, Arrays.asList(1, 2));
        JsonInterview result = postRequest("/api/v1/interview/create", object);
        assertThat(result.getApplicantId()).isEqualTo(1);
        assertThat(result.getInterviewerIds()).contains(1L, 2L);
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testDataWithInterviews.xml")
    public void testReadAllInterviews() {
        List<JsonInterview> result = requestAll();
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getInterviewerIds()).contains(1L, 3L);
        assertThat(result.get(1).getInterviewerIds()).contains(2L);
    }

    private List<JsonInterview> requestAll() {
        ParameterizedTypeReference<List<JsonInterview>> typeRef =
                new ParameterizedTypeReference<List<JsonInterview>>() {
                };
        List<JsonInterview> result = restTemplate
                .exchange("/api/v1/interview/", HttpMethod.GET, null, typeRef)
                .getBody();
        return result;
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testDataWithInterviews.xml")
    public void testReadInterviewById() {
        JsonInterview interview = restTemplate.getForEntity("/api/v1/interview/1", JsonInterview.class)
                .getBody();
        assertThat(interview.getInterviewId()).isEqualTo(1);
        assertThat(interview.getApplicantId()).isEqualTo(1);
        assertThat(interview.getInterviewerIds()).contains(1L, 3L);
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testDataWithInterviews.xml")
    public void testCancelInterview() {
        JSONObject object = createInterviewObject(0, 1, Arrays.asList(1, 2));
        JsonInterview result = postRequest("/api/v1/interview/cancel/1", object);
        assertThat(result.isCancelled()).isTrue();
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testDataWithInterviews.xml")
    public void testRescheduleInterview() {
        LocalDateTime time = LocalDateTime.now().plusDays(3);
        JSONObject obj = JSONObjectBuilder.aJsonObject()
                .with("interviewId", 2)
                .with("newScheduledTime", formatTime(time))
                .with("newLocation", "Veldkant 35A")
                .build();
        JsonInterview result = postRequest("/api/v1/interview/reschedule", obj);
        verifyResult(time, result);
    }

    private void verifyResult(LocalDateTime time, JsonInterview result) {
        assertThat(result.getInterviewId()).isEqualTo(2L);
        assertThat(result.getScheduledTime()).isEqualTo(time);
        assertThat(result.getLocation()).isEqualTo("Veldkant 35A");
    }

    private JsonInterview postRequest(String url, JSONObject obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(obj.toJSONString(), headers);
        return restTemplate
                .postForEntity(url, httpEntity, JsonInterview.class)
                .getBody();
    }

    @Test
    @DatabaseSetup(value = "/interview/InterviewTest.testRemind.xml")
    public void testRemindParticipantsBeforeInterview() throws Exception {
        Interview interview = interviewRepository.findById(1).get();
        interview.setScheduledTime(LocalDateTime.now().plusHours(12));
        executeRemindersAndVerify(interview);
    }

    private void executeRemindersAndVerify(Interview interview) throws Exception {
        interviewRepository.updateInterview(interview);
        remindParticipants.checkReminders();
        verifyReminderResult();
    }

    private void verifyReminderResult() throws Exception {
        Interview interview;
        interview = interviewRepository.findById(1).get();
        assertThat(interview.isPreInterviewReminderSent()).isTrue();
        verifyMailboxesHaveMessages("jos.vermeulen@example.com",
                "casandra.kleinveld@email.com",
                "jitte.slotboom@email.com");
    }

    private void verifyMailboxesHaveMessages(String... mailboxes) throws Exception {
        for (String mailbox : mailboxes) {
            verifyMailboxHasMessages(mailbox);
        }
    }

    private void verifyMailboxHasMessages(String mailbox) throws Exception {
        MockMailbox mb = MockMailbox.get(mailbox);
        Message[] messages = mb.getInbox().getMessages();
        assertThat(messages.length).isGreaterThanOrEqualTo(1);
    }

    private JSONObject createInterviewObject(long interviewId, long applicantId, List<Integer> interviewerIds) {
        return JSONObjectBuilder.aJsonObject()
                .with("interviewId", Long.toString(interviewId))
                .with("applicantId", Long.toString(applicantId))
                .withList("interviewerIds", interviewerIds)
                .with("createdTime", formatTime(LocalDateTime.now()))
                .with("scheduledTime", formatTime(LocalDateTime.now().plus(5, ChronoUnit.DAYS)))
                .build();
    }

    private String formatTime(LocalDateTime time) {
        return time.toString();
    }
}

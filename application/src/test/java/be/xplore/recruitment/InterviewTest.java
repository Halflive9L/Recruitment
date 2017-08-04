package be.xplore.recruitment;

import be.xplore.recruitment.web.interview.JsonInterview;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InterviewTest extends TestBase {
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
    //@DatabaseTearDown(type = DatabaseOperation.TRUNCATE_TABLE)
    public void testReadInterviewById() {
        JsonInterview interview = restTemplate.getForEntity("/api/interview/1", JsonInterview.class)
                .getBody();
        assertThat(interview.getInterviewId()).isEqualTo(1);
        assertThat(interview.getApplicantId()).isEqualTo(1);
        assertThat(interview.getInterviewerIds()).contains(1L, 3L);
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
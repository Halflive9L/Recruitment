package be.xplore.recruitment;

import be.xplore.recruitment.domain.interviewer.Interviewer;
import be.xplore.recruitment.web.interviewer.JsonInterviewer;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InterviewerTest extends TestBase {
    protected JSONObject getJsonTestObject() {
        return null;
    }

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    public void testGetById() {
        JsonInterviewer interviewer = restTemplate.getForEntity("/api/v1/interviewer/1", JsonInterviewer.class)
                .getBody();
        assertThat(interviewer.getFirstName()).isEqualTo("Casandra");
        assertThat(interviewer.getLastName()).isEqualTo("Kleinveld");
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    public void testGetAll() {
        ParameterizedTypeReference<List<JsonInterviewer>> typeRef =
                new ParameterizedTypeReference<List<JsonInterviewer>>() {
                };
        List<JsonInterviewer> result = restTemplate
                .exchange("/api/v1/interviewer/", HttpMethod.GET, null, typeRef)
                .getBody();
        assertThat(result).hasSize(3);
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    @ExpectedDatabase(value = "/interviewer/InterviewerTest.afterCreate.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreate() {
        JSONObject body = createInterviewerJsonObject(Interviewer.builder()
                .withInterviewerId(0)
                .withFirstName("Maarten")
                .withLastName("Billiet")
                .withEmail("mb@email.com")
                .build());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body.toJSONString(), headers);
        JsonInterviewer result = restTemplate
                .postForEntity("/api/v1/interviewer/create", httpEntity, JsonInterviewer.class)
                .getBody();
        assertThat(result.getFirstName()).isEqualTo("Maarten");
        assertThat(result.getLastName()).isEqualTo("Billiet");
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    @ExpectedDatabase(value = "/interviewer/InterviewerTest.afterUpdate.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdate() {
        JSONObject body = createInterviewerJsonObject(Interviewer.builder()
                .withInterviewerId(1)
                .withFirstName("Maarten")
                .withLastName("Billiet")
                .withEmail("mb@email.com")
                .build());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body.toJSONString(), headers);
        JsonInterviewer result = restTemplate
                .postForEntity("/api/v1/interviewer/update", httpEntity, JsonInterviewer.class)
                .getBody();
        assertThat(result.getFirstName()).isEqualTo("Maarten");
        assertThat(result.getLastName()).isEqualTo("Billiet");
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    @ExpectedDatabase(value = "/interviewer/InterviewerTest.afterDelete.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDelete() {
        restTemplate
                .exchange("/api/v1/interviewer/1", HttpMethod.DELETE, null, Object.class)
                .getBody();
    }

    private JSONObject createInterviewerJsonObject(Interviewer interviewer) {
        JSONObject obj = new JSONObject();
        obj.put("interviewerId", Long.toString(interviewer.getInterviewerId()));
        obj.put("firstName", interviewer.getFirstName());
        obj.put("lastName", interviewer.getLastName());
        obj.put("email", interviewer.getEmail());
        return obj;
    }
}

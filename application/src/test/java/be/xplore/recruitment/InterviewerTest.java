package be.xplore.recruitment;

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
    @Override
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
        JsonInterviewer interviewer = restTemplate.getForEntity("/api/interviewer/1", JsonInterviewer.class)
                .getBody();
        assertThat(interviewer.getFirstName()).isEqualTo("Casandra");
        assertThat(interviewer.getLastName()).isEqualTo("Kleinveld");
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    public void testGetAll() {
        ParameterizedTypeReference<List<JsonInterviewer>> typeRef =
                new ParameterizedTypeReference<List<JsonInterviewer>>() {};
        List<JsonInterviewer> result = restTemplate
                .exchange("/api/interviewer/all", HttpMethod.GET, null,  typeRef)
                .getBody();
        assertThat(result).hasSize(3);
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    @ExpectedDatabase(value = "/interviewer/InterviewerTest.afterCreate.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreate() {
        JSONObject body = createInterviewerJsonObject(0, "Maarten", "Billiet");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body.toJSONString(), headers);
        JsonInterviewer result = restTemplate
                .postForEntity("/api/interviewer/create", httpEntity, JsonInterviewer.class)
                .getBody();
        assertThat(result.getFirstName()).isEqualTo("Maarten");
        assertThat(result.getLastName()).isEqualTo("Billiet");
    }

    @Test
    @DatabaseSetup("/interviewer/InterviewerTest.testData.xml")
    @ExpectedDatabase(value = "/interviewer/InterviewerTest.afterUpdate.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdate() {
    JSONObject body = createInterviewerJsonObject(1, "Maarten", "Billiet");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(body.toJSONString(), headers);
        JsonInterviewer result = restTemplate
                .postForEntity("/api/interviewer/update", httpEntity, JsonInterviewer.class)
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
                .exchange("/api/interviewer/1", HttpMethod.DELETE, null, Object.class)
                .getBody();
    }

    private JSONObject createInterviewerJsonObject(long id, String firstName, String lastName) {
        JSONObject obj = new JSONObject();
        obj.put("interviewerId", Long.toString(id));
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        return obj;
    }
}

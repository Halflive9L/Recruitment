package be.xplore.recruitment;

import be.xplore.recruitment.web.applicant.JsonApplicant;
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
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */

public class ApplicantTest extends TestBase {
    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Override
    public JSONObject getJsonTestObject() {
        JSONObject jsonTestObject = new JSONObject();
        jsonTestObject.put("firstName", "jos");
        jsonTestObject.put("lastName", "vermeulen");
        jsonTestObject.put("dateOfBirth", "1990-01-01");
        jsonTestObject.put("address", "antwerpen");
        jsonTestObject.put("education", "none");
        jsonTestObject.put("email", "jos.vermeulen@example.com");
        jsonTestObject.put("phone", "+32356854598");
        return jsonTestObject;
    }

    @Test
    @ExpectedDatabase(value = "/applicant/ApplicantTest.testPOST.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testPOST() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);
        restTemplate.postForEntity("/api/v1/applicant", httpEntity, JsonApplicant.class);
    }

    @Test
    @DatabaseSetup("/applicant/ApplicantTest.testGetById.xml")
    public void testGetById() {
        JsonApplicant applicant = restTemplate.getForEntity("/api/v1/applicant/1", JsonApplicant.class).getBody();
        assertThat(applicant.getFirstName()).isEqualTo("jos");
        assertThat(applicant.getLastName()).isEqualTo("vermeulen");
        assertThat(applicant.getAddress()).isEqualTo("antwerpen");
        assertThat(applicant.getEducation()).isEqualTo("none");
        assertThat(applicant.getEmail()).isEqualTo("jos.vermeulen@example.com");
        assertThat(applicant.getPhone()).isEqualTo("+32356854598");
    }

    @Test
    @DatabaseSetup(value = "/applicant/ApplicantTest.testGetAll.xml")
    public void testGetAll() {
        ParameterizedTypeReference<List<JsonApplicant>> typeReference =
                new ParameterizedTypeReference<List<JsonApplicant>>() {
                };
        List<JsonApplicant> applicants =
                restTemplate.exchange("/api/v1/applicant", HttpMethod.GET, null, typeReference).getBody();
        assertThat(applicants).hasSize(3);
    }

    @Test
    @DatabaseSetup(value = "/applicant/ApplicantTest.testGetByParam.xml")
    public void testGetByParam() {
        ParameterizedTypeReference<List<JsonApplicant>> typeReference =
                new ParameterizedTypeReference<List<JsonApplicant>>() {
                };
        List<JsonApplicant> applicants =
                restTemplate.exchange("/api/v1/applicant?firstName=stijn", HttpMethod.GET,
                        null, typeReference).getBody();
        assertThat(applicants).hasSize(2);
        assertThat(applicants.get(0).getFirstName()).isEqualTo(applicants.get(1).getFirstName())
                .isEqualToIgnoringCase("stijn");
    }

    @Test
    @DatabaseSetup(value = "/applicant/ApplicantTest.testUpdateApplicant.setup.xml")
    @ExpectedDatabase(value = "/applicant/ApplicantTest.testUpdateApplicant.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdateApplicant() {
        String jsonBody = "{" +
                "\"firstName\":\"lander\"," +
                "\"lastName\":\"tuyteleers\"," +
                "\"dateOfBirth\":\"1996-01-01\"," +
                "\"email\":\"lander.tuyteleers@example.com\"," +
                "\"phone\":\"+32458621475\"," +
                "\"address\":\"Kontich\", " +
                "\"education\":\"college\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, headers);
        restTemplate.put("/api/v1/applicant/3", httpEntity, ResponseEntity.class);

    }

}

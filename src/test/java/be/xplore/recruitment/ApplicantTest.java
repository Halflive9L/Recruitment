package be.xplore.recruitment;

import be.xplore.recruitment.api.ApplicantController;
import be.xplore.recruitment.model.Applicant;
import be.xplore.recruitment.repository.ApplicantRepository;
import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicantTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ApplicantController applicantController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    public void contextLoads() {
        assertThat(applicantController).isNotNull();
        assertThat(restTemplate).isNotNull();
        assertThat(applicantRepository).isNotNull();
    }

    @Ignore
    public JSONObject getJsonTestObject() {
        JSONObject jsonTestObject = new JSONObject();
        jsonTestObject.put("firstName", "jos");
        jsonTestObject.put("lastName", "vermeulen");
        jsonTestObject.put("dateOfBirth", "1990-1-1");
        jsonTestObject.put("address", "antwerpen");
        jsonTestObject.put("education","toegepaste informatica");
        jsonTestObject.put("email", "jos.vermeulen@example.com");
        jsonTestObject.put("phone", "0493587565");
        return jsonTestObject;
    }

    @Test
    public void testPOST() {
        URI applicantUri = URI.create("http://localhost:" + port + "/applicant");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);

        assertThat(restTemplate.postForEntity(applicantUri, httpEntity, ResponseEntity.class).getStatusCodeValue())
                .isEqualTo(new ResponseEntity<>(HttpStatus.OK).getStatusCodeValue());
    }

    @Test
    public void testGetById(){
        URI prospectUri = URI.create("http://localhost:" + port + "/applicant");
        JSONObject jsonObject = getJsonTestObject();
        Applicant applicant = restTemplate.getForEntity(URI.create(prospectUri.toString() + "/1"), Applicant.class).getBody();
        assertThat(applicant.getFirstName()).isEqualTo(jsonObject.getAsString("firstName"));
        assertThat(applicant.getLastName()).isEqualTo(jsonObject.getAsString("lastName"));
        assertThat(applicant.getDateOfBirth()).isInSameDayAs(jsonObject.getAsString("dateOfBirth"));
        assertThat(applicant.getAddress()).isEqualTo(jsonObject.getAsString("address"));
        assertThat(applicant.getEducation()).isEqualTo(jsonObject.getAsString("education"));
        assertThat(applicant.getEmail()).isEqualTo(jsonObject.getAsString("email"));
        assertThat(applicant.getPhone()).isEqualTo(jsonObject.getAsString("phone"));
    }
}

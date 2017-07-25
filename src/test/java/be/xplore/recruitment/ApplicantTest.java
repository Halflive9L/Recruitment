package be.xplore.recruitment;

import be.xplore.recruitment.domain.model.Applicant;
import be.xplore.recruitment.repository.ApplicantRepository;
import be.xplore.recruitment.web.api.ApplicantController;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */

@RunWith(SpringRunner.class)
public class ApplicantTest extends TestBase{
    @Autowired
    private ApplicantController applicantController;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    public void contextLoads() {
        assertThat(applicantController).isNotNull();
        assertThat(restTemplate).isNotNull();
        assertThat(applicantRepository).isNotNull();
    }

    @Override
    public JSONObject getJsonTestObject() {
        JSONObject jsonTestObject = new JSONObject();
        jsonTestObject.put("firstName", "jos");
        jsonTestObject.put("lastName", "vermeulen");
        jsonTestObject.put("dateOfBirth", "1990-01-01");
        jsonTestObject.put("address", "antwerpen");
        jsonTestObject.put("education","none");
        jsonTestObject.put("email", "jos.vermeulen@example.com");
        jsonTestObject.put("phone", "0356854598");
        return jsonTestObject;
    }

    @Test
    @ExpectedDatabase(value = "/ApplicantTest.testPOST.xml")
    public void testPOST() {
        URI applicantUri = URI.create("http://localhost:" + port + "/applicant");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);

        assertThat(restTemplate.postForEntity(applicantUri, httpEntity, ResponseEntity.class).getStatusCodeValue())
                .isEqualTo(new ResponseEntity<>(HttpStatus.OK).getStatusCodeValue());
    }

    @Test
    @DatabaseSetup("/ApplicantTest.testGetById.xml")
    public void testGetById(){
        URI prospectUri = URI.create("http://localhost:" + port + "/applicant");
        Applicant applicant = restTemplate.getForEntity(URI.create(prospectUri.toString() + "/1"), Applicant.class).getBody();
        assertThat(applicant.getFirstName()).isEqualTo("jos");
        assertThat(applicant.getLastName()).isEqualTo("vermeulen");
        assertThat(applicant.getDateOfBirth()).isInSameDayAs("1990-01-01");
        assertThat(applicant.getAddress()).isEqualTo("antwerpen");
        assertThat(applicant.getEducation()).isEqualTo("none");
        assertThat(applicant.getEmail()).isEqualTo("jos.vermeulen@example.com");
        assertThat(applicant.getPhone()).isEqualTo("0356854598");
    }

    @Test
    public void testGetByParam() {

    }
}

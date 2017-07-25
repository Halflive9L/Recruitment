package be.xplore.recruitment;

import be.xplore.recruitment.domain.model.Applicant;
import be.xplore.recruitment.web.api.ApplicantController;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */

public class ApplicantTest extends TestBase{

    @Autowired
    private ApplicantController applicantController;

    @Test
    public void contextLoads() {
        assertThat(applicantController).isNotNull();
        assertThat(restTemplate).isNotNull();
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
    @ExpectedDatabase(value = "/applicant/ApplicantTest.testPOST.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testPOST() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);

        assertThat(restTemplate.postForEntity("/applicant", httpEntity, ResponseEntity.class).getStatusCodeValue())
                .isEqualTo(new ResponseEntity<>(HttpStatus.OK).getStatusCodeValue());
    }

    @Test
    @DatabaseSetup("/applicant/ApplicantTest.testGetById.xml")
    public void testGetById(){
        Applicant applicant = restTemplate.getForEntity(URI.create("/applicant/1"), Applicant.class).getBody();
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

package be.xplore.recruitment;

import be.xplore.recruitment.api.ProspectController;
import be.xplore.recruitment.model.Prospect;
import be.xplore.recruitment.repository.ProspectRepository;
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
public class ProspectTest extends TestBase {
    @Autowired
    private ProspectController prospectController;

    @Autowired
    private ProspectRepository prospectRepository;

    @Test
    public void contextLoads() {
        assertThat(prospectController).isNotNull();
        assertThat(restTemplate).isNotNull();
        assertThat(prospectRepository).isNotNull();
    }

    @Override
    protected JSONObject getJsonTestObject() {
        JSONObject jsonTestObject = new JSONObject();
        jsonTestObject.put("firstName", "jos");
        jsonTestObject.put("lastName", "vermeulen");
        jsonTestObject.put("email", "jos.vermeulen@example.com");
        jsonTestObject.put("phone", "0356854598");
        return jsonTestObject;
    }

    @Override
    @ExpectedDatabase(value = "/ProspectTest.testPOST.xml")
    public void testPOST() {
        URI prospectUri = URI.create("http://localhost:" + port + "/prospect");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);

        assertThat(restTemplate.postForEntity(prospectUri, httpEntity, ResponseEntity.class).getStatusCodeValue())
                .isEqualTo(new ResponseEntity<>(HttpStatus.OK).getStatusCodeValue());
    }

    @Override
    @DatabaseSetup("/ProspectTest.testGetById.xml")
    public void testGetById() {
        URI prospectUri = URI.create("http://localhost:" + port + "/prospect");
        Prospect prospect = restTemplate.getForEntity(URI.create(prospectUri.toString() + "/1"), Prospect.class).getBody();
        assertThat(prospect.getFirstName()).isEqualTo("jos");
        assertThat(prospect.getLastName()).isEqualTo("vermeulen");
        assertThat(prospect.getEmail()).isEqualTo("jos.vermeulen@example.com");
        assertThat(prospect.getPhone()).isEqualTo("0356854598");
    }
}

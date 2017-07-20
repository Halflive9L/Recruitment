package be.xplore.recruitment;

import be.xplore.recruitment.api.ProspectController;
import be.xplore.recruitment.model.Prospect;
import be.xplore.recruitment.repository.ProspectRepository;
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
public class ProspectTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ProspectController prospectController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProspectRepository prospectRepository;

    @Test
    public void contextLoads() {
        assertThat(prospectController).isNotNull();
        assertThat(restTemplate).isNotNull();
        assertThat(prospectRepository).isNotNull();
    }

    @Ignore
    public JSONObject getJsonTestObject() {
        JSONObject jsonTestObject = new JSONObject();
        jsonTestObject.put("firstName", "jos");
        jsonTestObject.put("lastName", "vermeulen");
        jsonTestObject.put("email", "jos.vermeulen@example.com");
        jsonTestObject.put("phone", "0493587565");
        return jsonTestObject;
    }

    @Test
    public void testPOST() {
        URI prospectUri = URI.create("http://localhost:" + port + "/prospect");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);

        assertThat(restTemplate.postForEntity(prospectUri, httpEntity, ResponseEntity.class).getStatusCodeValue())
                .isEqualTo(new ResponseEntity<>(HttpStatus.OK).getStatusCodeValue());
    }

    @Test
    public void testGetById(){
        URI prospectUri = URI.create("http://localhost:" + port + "/prospect");
        JSONObject jsonObject = getJsonTestObject();
        Prospect prospect = restTemplate.getForEntity(URI.create(prospectUri.toString() + "/1"), Prospect.class).getBody();
        assertThat(prospect.getFirstName()).isEqualTo(jsonObject.getAsString("firstName"));
        assertThat(prospect.getLastName()).isEqualTo(jsonObject.getAsString("lastName"));
        assertThat(prospect.getEmail()).isEqualTo(jsonObject.getAsString("email"));
        assertThat(prospect.getPhone()).isEqualTo(jsonObject.getAsString("phone"));
    }
}

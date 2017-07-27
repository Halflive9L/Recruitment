package be.xplore.recruitment.web.prospect;

import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ProspectController.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ProspectControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String baseUrl = "/prospect";

    @Ignore
    private JSONObject getJsonTestObject() {
        JSONObject jsonTestObject = new JSONObject();
        jsonTestObject.put("firstName", "jos");
        jsonTestObject.put("lastName", "vermeulen");
        jsonTestObject.put("email", "jos.vermeulen@example.com");
        jsonTestObject.put("phone", "03568545981");
        return jsonTestObject;
    }

    @Test
    public void addProspectTest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);
        assertThat(testRestTemplate.postForEntity(baseUrl, httpEntity, ResponseEntity.class).getStatusCodeValue())
                .isEqualTo(new ResponseEntity<>(HttpStatus.OK).getStatusCodeValue());
    }

    @Test
    public void getProspectByIdTest(){

    }
}

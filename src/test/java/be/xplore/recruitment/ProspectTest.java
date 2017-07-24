package be.xplore.recruitment;

import be.xplore.recruitment.domain.model.Prospect;
import be.xplore.recruitment.repository.ProspectRepository;
import be.xplore.recruitment.web.api.ProspectController;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.List;

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
    @ExpectedDatabase(value = "/ProspectTest.testPOST.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
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

    @Override
    @DatabaseSetup(value = "/ProspectTest.testGetByParam.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void testGetByParam() {
        URI uri = URI.create("http://localhost:" + port + "/prospect");
        ParameterizedTypeReference<List<Prospect>> typeReference = new ParameterizedTypeReference<List<Prospect>>() {};
        List<Prospect> prospects = restTemplate.exchange(uri, HttpMethod.GET, null, typeReference).getBody();
        prospects.forEach(System.out::println);
        assertThat(prospects).isNotEmpty();
    }
}

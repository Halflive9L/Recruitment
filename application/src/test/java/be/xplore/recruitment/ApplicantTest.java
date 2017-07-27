package be.xplore.recruitment;

import net.minidev.json.JSONObject;
import org.junit.Test;

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
        jsonTestObject.put("phone", "0356854598");
        return jsonTestObject;
    }
/*
    @Test
    @ExpectedDatabase(value = "/applicant/ApplicantTest.testPOST.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testPOST() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);
        restTemplate.postForEntity("/applicant", httpEntity, ResponseEntity.class);
    }

    @Test
    @DatabaseSetup("/applicant/ApplicantTest.testGetById.xml")
    public void testGetById() {
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
    @DatabaseSetup(value = "/applicant/ApplicantTest.testGetAll.xml")
    public void testGetAll() {
        ParameterizedTypeReference<List<Applicant>> typeReference = new ParameterizedTypeReference<List<Applicant>>() {
        };
        List<Applicant> prospects =
                restTemplate.exchange("/applicant", HttpMethod.GET, null, typeReference).getBody();
        prospects.forEach(System.out::println);
        assertThat(prospects).hasSize(3);
    }

    @Test
    @DatabaseSetup(value = "/applicant/ApplicantTest.testGetByParam.xml")
    public void testGetByParam() {
        ParameterizedTypeReference<List<Applicant>> typeReference = new ParameterizedTypeReference<List<Applicant>>() {
        };
        List<Applicant> prospects =
                restTemplate.exchange("/applicant?firstName=stijn", HttpMethod.GET, null, typeReference).getBody();
        assertThat(prospects).hasSize(2);
        assertThat(prospects.get(0).getFirstName()).isEqualTo(prospects.get(1).getFirstName())
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
                "\"phone\":\"0458621475\"," +
                "\"address\":\"Kontich\", " +
                "\"education\":\"college\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, headers);
        restTemplate.put("/applicant/3", httpEntity, ResponseEntity.class);

    }
*/

}

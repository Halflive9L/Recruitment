package be.xplore.recruitment;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */


public class ProspectTest /*extends TestBase*/ {
/*
    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected JSONObject getJsonTestObject() {
        JSONObject jsonTestObject = new JSONObject();
        jsonTestObject.put("firstName", "jos");
        jsonTestObject.put("lastName", "vermeulen");
        jsonTestObject.put("email", "jos.vermeulen@example.com");
        jsonTestObject.put("phone", "+323568545981");
        return jsonTestObject;
    }

    @Test
    @ExpectedDatabase(value = "/prospect/ProspectTest.testPOST.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testPOST() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(getJsonTestObject().toJSONString(), headers);
        restTemplate.postForEntity("/prospect", httpEntity, ResponseEntity.class);
    }

    @Test
    @DatabaseSetup("/prospect/ProspectTest.testGetById.xml")
    public void testGetById() {
        Prospect prospect = restTemplate.getForEntity("/prospect/1", Prospect.class).getBody();
        assertThat(prospect.getFirstName()).isEqualTo("jos");
        assertThat(prospect.getLastName()).isEqualTo("vermeulen");
        assertThat(prospect.getEmail()).isEqualTo("jos.vermeulen@example.com");
        assertThat(prospect.getPhone()).isEqualTo("0356854598");
    }

    @Test
    @DatabaseSetup(value = "/prospect/ProspectTest.testGetAll.xml")
    public void testGetAll() {
        ParameterizedTypeReference<List<Prospect>> typeReference = new ParameterizedTypeReference<List<Prospect>>() {
        };
        List<Prospect> prospects =
                restTemplate.exchange("/prospect", HttpMethod.GET, null, typeReference).getBody();
        prospects.forEach(System.out::println);
        assertThat(prospects).hasSize(2);
    }

    @Test
    @DatabaseSetup(value = "/prospect/ProspectTest.testGetByParam.xml")//, type = DatabaseOperation.CLEAN_INSERT)
    public void testGetByParam() {
        ParameterizedTypeReference<List<Prospect>> typeReference = new ParameterizedTypeReference<List<Prospect>>() {
        };
        List<Prospect> prospects =
                restTemplate.exchange("/prospect?firstName=stijn", HttpMethod.GET, null, typeReference).getBody();
        assertThat(prospects).hasSize(2);
        assertThat(prospects.get(0).getFirstName()).isEqualTo(prospects.get(1).getFirstName())
                .isEqualToIgnoringCase("stijn");
    }
*/
}

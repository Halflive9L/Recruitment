package be.xplore.recruitment.attachment;

import be.xplore.recruitment.TestBase;
import be.xplore.recruitment.web.attachment.JsonAttachment;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
@Rollback
public class ApplicantAttachmentTest extends TestBase {
    @Test
    @DatabaseSetup(value="ApplicantAttachmentTest.testUpload.setup.xml")
    @ExpectedDatabase(value = "ApplicantAttachmentTest.testUpload.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpload() {
        LinkedMultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<>();
        multipartMap.add("attachment", new FileSystemResource("testPdf.pdf"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity<>(multipartMap, headers);
        ResponseEntity<JsonAttachment> responseEntity = restTemplate.
                exchange("/api/v1/applicant/1/attachment", HttpMethod.POST, request, JsonAttachment.class);
        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(1, responseEntity.getBody().getId());
    }

    @Test
    @DatabaseSetup(value="ApplicantAttachmentTest.testUpload.setup.xml")
    @ExpectedDatabase(value = "ApplicantAttachmentTest.testUpload.setup.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUploadForNonExistingApplicant() {

        ResponseEntity<JsonAttachment> responseEntity = restTemplate.
                exchange("/api/v1/applicant/500/attachment", HttpMethod.POST,
                        getUploadRequestEntity(), JsonAttachment.class);

        assertNull(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("Duplicates")
    @Ignore
    private HttpEntity<LinkedMultiValueMap<String, Object>> getUploadRequestEntity() {
        LinkedMultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<>();
        multipartMap.add("attachment", new FileSystemResource("testPdf.pdf"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HttpEntity<>(multipartMap, headers);
    }
}

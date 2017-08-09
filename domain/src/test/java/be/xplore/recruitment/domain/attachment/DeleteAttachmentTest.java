package be.xplore.recruitment.domain.attachment;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Stijn Schack
 * @since 8/9/2017
 */
public class DeleteAttachmentTest {
    private DeleteAttachment useCase;

    @Before
    public void initUseCase() {
        useCase = new DeleteAttachmentUseCase(new MockAttachmentRepo());
    }

    @Test
    public void testDeleteAttachment() {
        DeleteAttachmentRequest request = new DeleteAttachmentRequest(1);
        useCase.deleteAttachment(request, responseModel -> {
            assertNotNull(responseModel.getAttachment());
            assertEquals(1, responseModel.getAttachment().getAttachmentId());
        });
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNonExistingAttachment() {
        DeleteAttachmentRequest request = new DeleteAttachmentRequest(500);
        useCase.deleteAttachment(request, Assert::assertNull);
    }
}

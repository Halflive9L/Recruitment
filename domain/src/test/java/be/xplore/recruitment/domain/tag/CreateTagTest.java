package be.xplore.recruitment.domain.tag;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class CreateTagTest {
    private CreateTag useCase;

    @Before
    public void initUseCase() {
        useCase = new CreateTagUseCase(new MockTagRepo());
    }

    @Test
    public void testCreateTag() {
        CreateTagRequest request = new CreateTagRequest("testTag3");
        useCase.createTag(request, responseModel -> {
            assertNotNull(responseModel.getName());
            assertEquals(3, responseModel.getId());
            assertTrue(responseModel.isCreated());
        });
    }

    @Test
    public void testCreateExistingTag(){
        CreateTagRequest request = new CreateTagRequest("testTag1");
        useCase.createTag(request, responseModel -> {
            assertFalse(responseModel.isCreated());
            assertNull(responseModel.getName());
            assertEquals(0, responseModel.getId());
        });
    }
}

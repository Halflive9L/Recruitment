package be.xplore.recruitment.domain.applicant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateFileTest {
    private CreateFile useCase;

    @Mock
    private FileRepository repository;

    @Before
    public void initUseCase() {
        useCase = new CreateFileUseCase(repository);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateFile() throws IOException {
        CreateFileRequest request = new CreateFileRequest();
        request.setApplicantId(1);
        request.setExtension(".pdf");
        request.setInput(getClass().getResourceAsStream("testPdf.pdf"));
        assertNotNull(request.getInput());
        useCase.createFile(request, fileResponseModel -> {

        });
    }
}

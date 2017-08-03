package be.xplore.recruitment.domain.applicant.file;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
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
    @Mock
    private ApplicantRepository applicantRepository;

    @Before
    public void initUseCase() {
        useCase = new CreateFileUseCase(repository, applicantRepository);
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

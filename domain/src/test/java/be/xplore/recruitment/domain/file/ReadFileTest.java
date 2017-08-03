package be.xplore.recruitment.domain.file;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Stijn Schack
 * @since 8/3/2017
 */
public class ReadFileTest {
    private ReadFile useCase;

    @Mock
    private FileRepository repository;

    @Before
    public void initUseCase() {
        useCase = new ReadFileUseCase(repository);
    }

    @Test(expected = NullPointerException.class)
    public void testReadAllFiles() {
        ReadAllFilesForApplicantRequest request = new ReadAllFilesForApplicantRequest(1);
        useCase.readAllFilesForApplicant(request, files -> {

        });
    }

    @Test(expected = NullPointerException.class)
    public void testDownloadFile() throws IOException {
        DownloadFileRequest request = new DownloadFileRequest(1, "testPdf.pdf",
                new FileOutputStream("testFile.pdf"));
        useCase.downloadFile(request, responseModel -> {
        });
    }
}

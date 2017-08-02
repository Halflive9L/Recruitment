package be.xplore.recruitment.persistence.applicant;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class CreateFileTest {
    private FileRepo repo;

    @Before
    public void initRepo() {
        repo = new FileRepo();
    }

    @Test
    public void testCreateFile() throws IOException {
        InputStream input = getClass().getResourceAsStream("testPdf.pdf");
        System.out.println(input.available());
        File file = repo.createFile(1, input);
        System.out.println(file.getAbsolutePath());
        assertTrue(file.exists());
        assertTrue(file.length() == 7945);
    }
}

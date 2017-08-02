package be.xplore.recruitment.persistence.applicant;

import be.xplore.recruitment.domain.applicant.FileRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@Component
public class FileRepo implements FileRepository {
    @Override
    public File createFile(long applicantId, InputStream input) throws IOException {
        File file = File.createTempFile("applicant" + applicantId + "-", ".pdf");
        FileOutputStream output = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        input.close();
        output.close();
        return file;
    }
}

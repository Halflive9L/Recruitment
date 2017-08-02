package be.xplore.recruitment.persistence.applicant;

import be.xplore.recruitment.domain.applicant.FileRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.Arrays.asList;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@Component
public class FileRepo implements FileRepository {
    private Map<Long, File> dirs = new TreeMap<>();

    @Override
    public File createFile(long applicantId, InputStream input) throws IOException {
        if (!dirs.containsKey(applicantId)) {
            File dir = Files.createTempDirectory("applicant-" + applicantId + "-").toFile();
            dirs.put(applicantId, dir);
        }
        File file = File.createTempFile("applicant-" + applicantId + "-", ".pdf", dirs.get(applicantId));
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

    @Override
    public Optional<List<File>> readAllFiles(long applicantId) {
        if (!dirs.containsKey(applicantId)) {
            return Optional.empty();
        }
        File dir = dirs.get(applicantId);
        File[] filesArray = dir.listFiles();
        if (filesArray == null) {
            return Optional.empty();
        }
        return Optional.of(asList(filesArray));
    }
}

package be.xplore.recruitment.persistence.applicant.file;

import be.xplore.recruitment.domain.applicant.file.FileRepository;
import be.xplore.recruitment.domain.applicant.file.StreamWithInfo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public File createFile(long applicantId, InputStream input, String extension) throws IOException {
        if (!dirs.containsKey(applicantId)) {
            File dir = Files.createTempDirectory("applicant-" + applicantId + "-").toFile();
            dirs.put(applicantId, dir);
        }
        File file = File.createTempFile("applicant-" + applicantId + "-", extension, dirs.get(applicantId));
        try (FileOutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return file;
    }

    @Override
    public Optional<List<String>> readAllFiles(long applicantId) {
        if (!doesDirExist(applicantId)) {
            return Optional.empty();
        }
        File dir = dirs.get(applicantId);
        File[] filesArray = dir.listFiles();
        if (filesArray == null) {
            return Optional.empty();
        }
        String[] fileNames = new String[filesArray.length];
        for (int i = 0; i < filesArray.length; i++) {
            fileNames[i] = filesArray[i].getName();
        }
        return Optional.of(asList(fileNames));
    }

    @Override
    public Optional<StreamWithInfo> downloadFile(long applicantId, String fileName) throws IOException {
        if (!doesDirExist(applicantId)) {
            return Optional.empty();
        }
        File file = getFileFromApplicantIdAndFileName(applicantId, fileName);

        return Optional.of(new StreamWithInfo(new FileInputStream(file), file.getName()));
    }

    private boolean doesDirExist(long applicantId) {
        return dirs.containsKey(applicantId);
    }

    private File getFileFromApplicantIdAndFileName(long applicantId, String fileName) {
        return Paths.get(dirs.get(applicantId).getPath() + File.separator + fileName).toFile();
    }
}

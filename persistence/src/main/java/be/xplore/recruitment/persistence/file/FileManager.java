package be.xplore.recruitment.persistence.file;

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
public class FileManager {

    public String createFile(InputStream input, String ownerType, String extension) throws IOException {
        File file = createFileInFileSystem(ownerType, extension);
        JpaAttachment jpaAttachment = new JpaAttachment();
        jpaAttachment.setFileName(file.getName());
        writeFileToDisk(input, file);
        return file.getName();
    }

    private File createFileInFileSystem(String fileName, String extension) throws IOException {
        return File.createTempFile(fileName, extension);
    }

    private void writeFileToDisk(InputStream input, File file) throws IOException {
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
    }
    /*
    public Optional<List<String>> readAllFiles(long applicantId) {

        File dir = new File("");
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

    public Optional<StreamWithInfo> downloadFile(long applicantId, String fileName) throws IOException {
        File file = getFileFromApplicantIdAndFileName(applicantId, fileName);

        return Optional.of(new StreamWithInfo(new FileInputStream(file), file.getName()));
    }

    private File getFileFromApplicantIdAndFileName(long applicantId, String fileName) {
        return Paths.get(new File("").getPath() + File.separator + fileName).toFile();
    }
    */
}

package be.xplore.recruitment.persistence.attachment;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

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
        File file = File.createTempFile(fileName, extension);
        file.deleteOnExit();
        return file;
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

    InputStream downloadAttachment(String attachmentName) throws IOException {
        return new FileInputStream(getFileFromAttachmentName(attachmentName));
    }

    void deleteAttachment(String attachmentName) throws IOException {
        Files.deleteIfExists(getFileFromAttachmentName(attachmentName).toPath());
    }

    private File getFileFromAttachmentName(String fileName) {
        String tempPath = System.getProperty("java.io.tmpdir");
        return new File(tempPath + File.separator + fileName);
    }
}

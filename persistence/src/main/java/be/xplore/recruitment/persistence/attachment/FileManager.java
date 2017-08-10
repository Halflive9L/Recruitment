package be.xplore.recruitment.persistence.attachment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
@Component
public class FileManager {
    private static final Logger LOG = LoggerFactory.getLogger(FileManager.class);

    private final Path directory;

    public FileManager() throws IOException {
        directory = Files.createTempDirectory("recruitment");
        directory.toFile().deleteOnExit();

        LOG.info("Temporary directory created for file manager: {}", directory);
    }

    @PreDestroy
    public void deleteTemporaryDirectory() throws IOException {
        Files.walk(directory).sorted(Comparator.reverseOrder()).forEach(this::deleteTemporaryFile);
    }

    public String createFile(InputStream input, String ownerType, String extension) throws IOException {
        Path file = createFileInFileSystem(ownerType, extension);
        JpaAttachment jpaAttachment = new JpaAttachment();
        jpaAttachment.setFileName(file.toFile().getName());
        writeFileToDisk(input, file);
        return file.toFile().getName();
    }

    private Path createFileInFileSystem(String fileName, String extension) throws IOException {
        Path file = Files.createTempFile(directory, fileName, extension);
        file.toFile().deleteOnExit();

        LOG.debug("Temporary file created: {}", file);

        return file;
    }

    private void writeFileToDisk(InputStream input, Path file) throws IOException {
        try {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            closeQuietly(input);
        }
    }

    public InputStream downloadAttachment(String attachmentName) throws IOException {
        return Files.newInputStream(getFileFromAttachmentName(attachmentName));
    }

    private Path getFileFromAttachmentName(String fileName) {
        return directory.resolve(fileName);
    }

    void deleteAttachment(String attachmentName) throws IOException {
        Files.deleteIfExists(getFileFromAttachmentName(attachmentName).toFile().toPath());
    }

    private void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException ignore) {
        }
    }

    private void deleteTemporaryFile(Path path) {
        try {
            LOG.info("Delete temporary file: {}", path);

            Files.delete(path);
        } catch (IOException ignore) {
            LOG.warn("Unable to delete temporary file: {}", path);
        }
    }
}

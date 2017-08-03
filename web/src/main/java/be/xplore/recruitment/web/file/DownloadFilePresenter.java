package be.xplore.recruitment.web.file;

import be.xplore.recruitment.domain.file.DownloadFileResponseModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;

import static org.springframework.util.StreamUtils.copy;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
class DownloadFilePresenter implements Consumer<DownloadFileResponseModel> {
    private String fileName;

    @Override
    public void accept(DownloadFileResponseModel responseModel) {
        this.fileName = responseModel.getFileName();
        try (InputStream in = responseModel.getInputStream(); OutputStream out = responseModel.getOutputStream()) {
            copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getFileName() {
        return fileName;
    }
}

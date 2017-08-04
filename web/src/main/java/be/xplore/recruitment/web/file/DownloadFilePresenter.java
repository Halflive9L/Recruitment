package be.xplore.recruitment.web.file;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
class DownloadFilePresenter {
    private String fileName;
    /*
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
    */
}

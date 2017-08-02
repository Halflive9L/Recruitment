package be.xplore.recruitment.domain.prospect;

import java.io.InputStream;

public class ImportProspectsRequest {
    private InputStream stream;

    public ImportProspectsRequest(InputStream stream) {
        this.stream = stream;
    }

    public InputStream getStream() {
        return stream;
    }
}

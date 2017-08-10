package be.xplore.recruitment.domain.prospect;

import java.util.ArrayList;
import java.util.List;

public class ImportProspectsResponseModel {
    private List<ImportProspectsFailure> failed;
    private List<ProspectResponseModel> prospects;

    public ImportProspectsResponseModel() {
        this.failed = new ArrayList<>();
        this.prospects = new ArrayList<>();
    }

    public List<ImportProspectsFailure> getFailed() {
        return failed;
    }

    public List<ProspectResponseModel> getProspects() {
        return prospects;
    }
}

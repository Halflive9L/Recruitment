package be.xplore.recruitment.domain.prospect;

import java.util.List;

public class ImportProspectsResponseModel {
    private List<ImportProspectsFailure> failed;
    private List<ProspectResponseModel> prospects;

    public ImportProspectsResponseModel(List<ImportProspectsFailure> failed, List<ProspectResponseModel> prospects) {
        this.failed = failed;
        this.prospects = prospects;
    }

    public List<ImportProspectsFailure> getFailed() {
        return failed;
    }

    public List<ProspectResponseModel> getProspects() {
        return prospects;
    }
}

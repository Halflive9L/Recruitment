package be.xplore.recruitment.domain.prospect;

import java.util.List;

public class ImportProspectsResponseModel {
    private List<String> failed;
    private List<ProspectResponseModel>  prospects;

    public ImportProspectsResponseModel(List<String> failed, List<ProspectResponseModel> prospects) {
        this.failed = failed;
        this.prospects = prospects;
    }

    public List<String> getFailed() {
        return failed;
    }

    public List<ProspectResponseModel> getProspects() {
        return prospects;
    }
}

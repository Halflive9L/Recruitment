package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ImportProspectsResponseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@JsonComponent
public class JsonImportResult implements Serializable {
    private static final long serialVersionUID = 7912309631885354667L;
    private List<JsonProspect> prospects;
    private List<JsonImportFailure> failed;

    @JsonCreator
    public JsonImportResult() {
    }

    public static JsonImportResult asJsonImportResult(ImportProspectsResponseModel response) {
        JsonImportResult json = new JsonImportResult();
        json.setProspects(response.getProspects()
                .stream()
                .map(JsonProspect::asJsonProspect)
                .collect(Collectors.toList()));
        json.setFailed(response.getFailed()
                .stream()
                .map(JsonImportFailure::asJsonImportFailure)
                .collect(Collectors.toList()));
        return json;
    }

    @JsonProperty
    public List<JsonProspect> getProspects() {
        return prospects;
    }

    @JsonProperty
    public void setProspects(List<JsonProspect> prospects) {
        this.prospects = prospects;
    }

    @JsonProperty
    public List<JsonImportFailure> getFailed() {
        return failed;
    }

    @JsonProperty
    public void setFailed(List<JsonImportFailure> failed) {
        this.failed = failed;
    }
}

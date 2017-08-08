package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ImportProspectsFailure;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

@JsonComponent
public class JsonImportFailure implements Serializable {
    private static final long serialVersionUID = -3977225873451707535L;
    private String input;
    private String reason;

    @JsonCreator
    public JsonImportFailure() {
    }

    public static JsonImportFailure asJsonImportFailure(ImportProspectsFailure failure) {
        JsonImportFailure json = new JsonImportFailure();
        json.setInput(failure.getInput());
        json.setReason(failure.getReason());
        return json;
    }

    @JsonProperty
    public String getInput() {
        return input;
    }

    @JsonProperty
    public void setInput(String input) {
        this.input = input;
    }

    @JsonProperty
    public String getReason() {
        return reason;
    }

    @JsonProperty
    public void setReason(String reason) {
        this.reason = reason;
    }
}


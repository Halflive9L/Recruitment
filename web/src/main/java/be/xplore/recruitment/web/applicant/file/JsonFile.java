package be.xplore.recruitment.web.applicant.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */

@JsonComponent
public class JsonFile {
    private String fileName;

    @JsonCreator
    public JsonFile() {
    }

    @JsonProperty
    public String getFileName() {
        return fileName;
    }

    @JsonProperty
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

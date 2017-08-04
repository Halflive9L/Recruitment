package be.xplore.recruitment.web.applicant.attachment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
@JsonComponent
public class JsonAttachment {
    private String attachmentName;

    @JsonCreator
    public JsonAttachment() {
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    @JsonProperty
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}

package be.xplore.recruitment.web.applicant.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
@JsonComponent
public class JsonAttachment {
    private long id;
    private String attachmentName;

    @JsonCreator
    public JsonAttachment() {
    }

    private JsonAttachment(long id, String attachmentName) {
        this.id = id;
        this.attachmentName = attachmentName;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getAttachmentName() {
        return attachmentName;
    }

    @JsonProperty
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    static JsonAttachment asJsonAttachment(Attachment attachment) {
        return new JsonAttachment(attachment.getAttachmentId(), attachment.getAttachmentName());
    }
}
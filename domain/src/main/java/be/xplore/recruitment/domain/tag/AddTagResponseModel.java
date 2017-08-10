package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class AddTagResponseModel {
    private String tagName;

    public AddTagResponseModel() {
    }

    public AddTagResponseModel(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}

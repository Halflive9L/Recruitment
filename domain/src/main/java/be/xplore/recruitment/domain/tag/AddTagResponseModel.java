package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class AddTagResponseModel {
    private String tagName;
    private boolean redundantTag;

    public AddTagResponseModel(String tagName, boolean redundantTag) {
        this.tagName = tagName;
        this.redundantTag = redundantTag;
    }

    public String getTagName() {
        return tagName;
    }

    public boolean isRedundantTag() {
        return redundantTag;
    }
}

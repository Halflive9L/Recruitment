package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public class RemoveTagResponseModel {
    private String tag;

    public RemoveTagResponseModel(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}

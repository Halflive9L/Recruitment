package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class CreateTagRequest {
    private String name;

    public CreateTagRequest(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

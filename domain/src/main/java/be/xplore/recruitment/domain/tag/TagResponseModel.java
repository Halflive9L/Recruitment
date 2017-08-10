package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class TagResponseModel {
    private long id;
    private String name;
    private boolean created;

    public TagResponseModel(long id, String name) {
        this.id = id;
        this.name = name;
        this.created = true;
    }

    public TagResponseModel() {
        this.created = false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

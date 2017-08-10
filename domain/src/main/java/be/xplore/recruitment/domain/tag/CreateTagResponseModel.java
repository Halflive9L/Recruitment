package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class CreateTagResponseModel {
    private long id;
    private String name;
    private boolean created;

    public CreateTagResponseModel(long id, String name) {
        this.id = id;
        this.name = name;
        this.created = true;
    }

    public CreateTagResponseModel() {
        this.created = false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCreated() {
        return created;
    }
}

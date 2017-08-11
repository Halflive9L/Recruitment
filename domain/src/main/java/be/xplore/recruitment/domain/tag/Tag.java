package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class Tag {
    private long tagId;
    private String tagName;

    public Tag(long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tag tag = (Tag) o;

        return tagName.equals(tag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagName='" + tagName + '\'' +
                '}';
    }
}

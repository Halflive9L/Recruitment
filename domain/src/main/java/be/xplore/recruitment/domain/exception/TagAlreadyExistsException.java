package be.xplore.recruitment.domain.exception;

import be.xplore.recruitment.domain.tag.Tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class TagAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 3602612837962159246L;

    public TagAlreadyExistsException(Tag tag, Throwable cause) {
        super(String.format("Tag with name: \"%s\" already exists", tag.getTagName()), cause);
    }

    public TagAlreadyExistsException(Tag tag) {
        this(tag, null);
    }
}

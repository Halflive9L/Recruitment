package be.xplore.recruitment.domain.exception;

import be.xplore.recruitment.domain.tag.Tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class EntityAlreadyHasTagException extends RuntimeException {

    private static final long serialVersionUID = 3602612837962159246L;

    public EntityAlreadyHasTagException(Class<?> entityType, Tag tag) {
        super(String.format("%s already has tag with name: %s", entityType.getSimpleName(), tag.getTagName()));
    }
}

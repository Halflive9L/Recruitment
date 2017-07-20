package be.xplore.recruitment.model.exceptions;

import org.omg.SendingContext.RunTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Lander on 18/07/2017.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super(String.format("Prospect with id: %d is not found.", id));
    }

    public NotFoundException(String name) {
        super(String.format("Prospect with name: %s is not found.", name));
    }

}

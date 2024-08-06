package de.predic8.config;

import de.predic8.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.net.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TooMuchException.class)
    public ProblemDetail handleCustomException(TooMuchException e, WebRequest r) throws URISyntaxException {
        ProblemDetail pd = forStatusAndDetail(BAD_REQUEST, "Too much!");
        pd.setTitle("Too much items!");
        pd.setType(new URI("http://predic8.de/vertical-slice"));
        return pd;
    }

    @ExceptionHandler(WrongStateException.class)
    public ProblemDetail handleCustomException(WrongStateException e, WebRequest r) throws URISyntaxException {
        ProblemDetail pd = forStatusAndDetail(CONFLICT, "Wrong state!");
        pd.setTitle("Wrong state for action!");
        pd.setType(new URI("http://predic8.de/vertical-slice"));
        return pd;
    }
}
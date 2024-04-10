package riccardo.U5W2D3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler (NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound( NotFoundException ex){
        return new ErrorsPayload( ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler (BadRequestException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest (BadRequestException ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler (Exception.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleException (){
        return new ErrorsPayload("Problema relativo al server, risolveremo al più presto possibile", LocalDateTime.now());
    }

}
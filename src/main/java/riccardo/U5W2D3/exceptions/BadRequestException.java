package riccardo.U5W2D3.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class BadRequestException extends RuntimeException{

   private List<ObjectError> errorList = new ArrayList<>();

    public BadRequestException (String message){
        super(message);
    }
    public BadRequestException (List<ObjectError> errorList){
        super("Ci sono stati degli errori nella validazione dei payload");
        this.errorList = errorList;
    }
}

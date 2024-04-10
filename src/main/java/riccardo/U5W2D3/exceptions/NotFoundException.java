package riccardo.U5W2D3.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){
        super("il record con id: " + id + " non trovato" );
    }
}

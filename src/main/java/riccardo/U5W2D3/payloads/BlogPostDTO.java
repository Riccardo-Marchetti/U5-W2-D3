package riccardo.U5W2D3.payloads;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record BlogPostDTO(
                   @NotEmpty (message = "La categoria è obbligatoria")
                   @Size (min = 1, max = 20 , message = "La categoria deve avere la lunghezza dei caratteri compresa tra 1 e 20")
                   String category,
                   @NotEmpty (message = "Il titolo è obbligatorio")
                   @Size (min = 1, max = 30, message = "Il titolo deve avere la lunghezza dei caratteri compresa tra 1 e 30")
                   String title,
                   @NotEmpty (message = "La cover è obbligatoria")
                   @URL (message = "L'URL che hai inserito non è valido")
                   String cover,
                   @NotEmpty (message = "La cover è obbligatoria")
                   @Size (min = 1, max = 100, message = "Il contenuto deve avere la lunghezza dei caratteri compresa tra 1 e 100")
                   String content,
                   @NotNull (message = "Il readingTime è obbligatorio")
                   @Min(1)
                   int readingTime,
                   @NotNull (message = "L'id dell'autore è obbligatorio")
                   @Min(1)
                   long idAuthor) {
}

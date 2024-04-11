package riccardo.U5W2D3.payloads;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class BlogPostPayload {

    private String category;

    private String title;

    private String cover;

    private String content;

    private int readingTime;

    private long idAuthor;
}

package riccardo.U5W2D3.entites;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostPayload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long idAuthor;

    private String category;

    private String title;

    private String cover;

    private String content;

    private int readingTime;
}

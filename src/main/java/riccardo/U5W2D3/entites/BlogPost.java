package riccardo.U5W2D3.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name = "author_id")
    private Author author;

    private String category;

    private String title;

    private String cover;

    private String content;

    private int readingTime;

}

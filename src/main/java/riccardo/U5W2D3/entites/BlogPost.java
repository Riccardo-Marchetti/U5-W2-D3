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
    @Setter (AccessLevel.NONE)
    private long id;

    private String category;

    private String title;

    private String cover;

    private String content;

    private int readingTime;

    @ManyToOne
    @JoinColumn (name = "author_id")
    private Author author;

    public BlogPost( String category, String title, String cover, String content, int readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
    }
}

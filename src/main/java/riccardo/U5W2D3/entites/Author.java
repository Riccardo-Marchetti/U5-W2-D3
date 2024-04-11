package riccardo.U5W2D3.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private long id;

    @OneToMany (mappedBy = "author")
    @JsonIgnore
    private List<BlogPost> blogPosts;

    private String name;

    private String lastName;

    private String email;

    private LocalDate dateOfBirth;

    private String avatar;

    public Author(long id, String name, String lastName, String email, LocalDate dateOfBirth, String avatar) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + lastName;
    }
}

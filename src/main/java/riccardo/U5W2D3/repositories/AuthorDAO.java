package riccardo.U5W2D3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.U5W2D3.entites.Author;

import java.util.Optional;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}

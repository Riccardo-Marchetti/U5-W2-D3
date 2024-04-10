package riccardo.U5W2D3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.U5W2D3.entites.BlogPost;

@Repository
public interface BlogPostDAO extends JpaRepository<BlogPost, Long> {

}

package riccardo.U5W2D3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.U5W2D3.entites.Author;
import riccardo.U5W2D3.exceptions.BadRequestException;
import riccardo.U5W2D3.exceptions.NotFoundException;
import riccardo.U5W2D3.repositories.AuthorDAO;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;
    public Page<Author> getAuthorList(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }

    public Author saveAuthor(Author body){
        this.authorDAO.findByEmail(body.getEmail()).ifPresent(
                author -> {
                    throw new BadRequestException("l'email: " + body.getEmail() + " è già in uso");
                }
        );
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getLastName());
      return authorDAO.save(body);
    }

    public Author findAuthorById(long id){
      return this.authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author findAuthorByIdAndUpdate(long id, Author authorUpdate){
        Author found = this.authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        found.setName(authorUpdate.getName());
        found.setLastName(authorUpdate.getLastName());
        found.setEmail(authorUpdate.getEmail());
        found.setDateOfBirth(authorUpdate.getDateOfBirth());
        found.setAvatar("https://ui-avatars.com/api/?name= " + authorUpdate.getName() + "+" + authorUpdate.getLastName());
        return this.authorDAO.save(found);
    }

    public void deleteAuthor(long id){
        Author found = this.authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.authorDAO.delete(found);
    }
}

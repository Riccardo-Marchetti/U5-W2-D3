package riccardo.U5W2D3.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardo.U5W2D3.entites.BlogPost;
import riccardo.U5W2D3.exceptions.NotFoundException;
import riccardo.U5W2D3.repositories.AuthorDAO;
import riccardo.U5W2D3.repositories.BlogPostDAO;

@Getter
@Service
public class BlogPostService {

    @Autowired
    private BlogPostDAO blogPostDAO;

    @Autowired
    private AuthorDAO authorDAO;
    public Page<BlogPost> getBlogPostsList(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostDAO.findAll(pageable);
    }

    public BlogPost saveBlogPost(BlogPost body, long id){
        this.authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        return blogPostDAO.save(body);
    }

    public BlogPost findBlogById (long id){
        return this.blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost findBlogByIdAndUpdate (long id, BlogPost blogPostUpdate){
        BlogPost found = this.blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        found.setCategory(blogPostUpdate.getCategory());
        found.setTitle(blogPostUpdate.getTitle());
        found.setCover(blogPostUpdate.getCover());
        found.setContent(blogPostUpdate.getContent());
        found.setReadingTime(blogPostUpdate.getReadingTime());
        return this.blogPostDAO.save(found);
    }

    public void deleteBlogPost(long id){
        BlogPost found = this.blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.blogPostDAO.delete(found);
    }
}

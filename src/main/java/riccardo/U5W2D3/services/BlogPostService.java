package riccardo.U5W2D3.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import riccardo.U5W2D3.entites.BlogPost;
import riccardo.U5W2D3.payloads.BlogPostDTO;
import riccardo.U5W2D3.exceptions.NotFoundException;
import riccardo.U5W2D3.repositories.BlogPostDAO;

import java.io.IOException;

@Getter
@Service
public class BlogPostService {

    @Autowired
    private BlogPostDAO blogPostDAO;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private Cloudinary cloudinary;

    public Page<BlogPost> getBlogPostsList(int page, int size, String sortBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostDAO.findAll(pageable);
    }

    public BlogPost saveBlogPost(BlogPostDTO body){
        BlogPost blogPost = new BlogPost( body.category(), body.title(), body.cover(), body.content(), body.readingTime(), authorService.findAuthorById(body.idAuthor()));
        return blogPostDAO.save(blogPost);
    }

    public BlogPost findBlogById (long id){
        return this.blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost findBlogByIdAndUpdate (long id, BlogPostDTO blogPostUpdate){
        BlogPost found = this.blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        found.setCategory(blogPostUpdate.category());
        found.setTitle(blogPostUpdate.title());
        found.setCover(blogPostUpdate.cover());
        found.setContent(blogPostUpdate.content());
        found.setReadingTime(blogPostUpdate.readingTime());
        found.setAuthor(authorService.findAuthorById(blogPostUpdate.idAuthor()));
        return this.blogPostDAO.save(found);
    }

    public void deleteBlogPost(long id){
        BlogPost found = this.blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
        this.blogPostDAO.delete(found);
    }

    public String uploadImage(MultipartFile image) throws IOException {
        return (String) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
    }
}

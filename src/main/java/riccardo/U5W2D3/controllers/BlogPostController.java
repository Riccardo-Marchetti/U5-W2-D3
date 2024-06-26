package riccardo.U5W2D3.controllers;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import riccardo.U5W2D3.entites.BlogPost;
import riccardo.U5W2D3.exceptions.BadRequestException;
import riccardo.U5W2D3.payloads.BlogPostDTO;
import riccardo.U5W2D3.services.BlogPostService;

import java.io.IOException;

@RestController
@RequestMapping ("/blogpost")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    private Page<BlogPost> getAllBlog(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size, @RequestParam (defaultValue = "id") String sortBy){
        return blogPostService.getBlogPostsList(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost postBlog(@RequestBody @Validated BlogPostDTO body, BindingResult validation){

        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return blogPostService.saveBlogPost(body);
    }

    @GetMapping ("/{blogId}")
    private BlogPost getBlogPostById(@PathVariable long blogId){
        return this.blogPostService.findBlogById(blogId);
    }

    @PutMapping ("/{blogId}")
    private BlogPost findBlogAndUpdate (@PathVariable @Validated long blogId, @RequestBody @Validated BlogPostDTO body, BindingResult validation){
        return blogPostService.findBlogByIdAndUpdate(blogId, body);
    }

    @DeleteMapping ("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findBlogAndDelete (@PathVariable long blogId){
        blogPostService.deleteBlogPost(blogId);
    }
    @PostMapping ("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    private String uploadImage(@RequestParam ("avatar")MultipartFile image) throws IOException {
        return blogPostService.uploadImage(image);
    }
}

package riccardo.U5W2D3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W2D3.entites.BlogPost;
import riccardo.U5W2D3.payloads.BlogPostPayload;
import riccardo.U5W2D3.services.BlogPostService;

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
    private BlogPost postBlog(@RequestBody BlogPostPayload body){
        return blogPostService.saveBlogPost(body);
    }

    @GetMapping ("/{blogId}")
    private BlogPost getBlogPostById(@PathVariable long blogId){
        return this.blogPostService.findBlogById(blogId);
    }

    @PutMapping ("/{blogId}")
    private BlogPost findBlogAndUpdate (@PathVariable long blogId, @RequestBody BlogPostPayload body){
        return blogPostService.findBlogByIdAndUpdate(blogId, body);
    }

    @DeleteMapping ("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findBlogAndDelete (@PathVariable long blogId){
        blogPostService.deleteBlogPost(blogId);
    }
}

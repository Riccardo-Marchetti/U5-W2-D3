package riccardo.U5W2D3.controllers;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import riccardo.U5W2D3.entites.Author;
import riccardo.U5W2D3.services.AuthorService;

import java.io.IOException;

@RestController
@RequestMapping ("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    private Page<Author> getAuthorList(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size, @RequestParam (defaultValue = "name") String sortBy){
        return authorService.getAuthorList(page, size, sortBy);
    }
    @GetMapping ("/{authorId}")
    private Author getAuthor (@PathVariable long authorId){
        return authorService.findAuthorById(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Author postAuthor (@RequestBody Author body) {
        return authorService.saveAuthor(body);
    }

    @PutMapping ("/{authorId}")
    private Author findAuthorByIdAndUpdate(@PathVariable long authorId, @RequestBody Author body ){
        return authorService.findAuthorByIdAndUpdate(authorId, body);
    }

    @DeleteMapping ("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteAuthor(@PathVariable long authorId){
        authorService.deleteAuthor(authorId);
    }

    @PostMapping ("/upload")
    @ResponseStatus (HttpStatus.CREATED)
    private String uploadImage(@RequestParam ("avatar")MultipartFile image) throws IOException {
    return authorService.uploadImage(image);
    }
}

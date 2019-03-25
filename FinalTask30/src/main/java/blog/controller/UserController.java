package blog.controller;

import blog.model.Article;
import blog.model.User;
import blog.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    private UserService userService = new UserServiceImpl();//done
    private ArticleService articleService = new ArticleServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    @PostMapping("articles")
    public ResponseEntity<Article> addArticle(
            @RequestParam(value = "id")Integer authorId,
            @RequestParam(value="title") String title,
            @RequestParam(value = "text") String text,
            @RequestParam(value = "status") Article.Status status){
        Article article = new Article();
        article.setAuthotId(authorId);
        article.setText(text);
        article.setTitle(title);
        article.setStatus(status);
        articleService.save(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("articles")
    public ResponseEntity<List<Article>> getPublicArticles(){
        return new ResponseEntity<>(articleService.getPublicArticle(), HttpStatus.OK);
    }

    @GetMapping("articles/my")
    public ResponseEntity<List<Article>> getUsersArticle(
            @RequestParam(value = "id") Integer id){
        return new ResponseEntity<>(articleService.getMyArticle(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "articles/{id}")//don't work
    public ResponseEntity<String> deleteArticle(@PathVariable Integer authorId,
                                        @RequestParam(value = "postId") Integer id){
        return new ResponseEntity<String>(articleService.delete(id, authorId) ? "Success" :
                "Your are not the author of the article",  HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "articles/{id}")//consumes and produces - wtf??
    public ResponseEntity<String> updateArticle(@PathVariable Integer id,
                                                @RequestParam(value = "postId") Integer postId,
                                                @RequestParam(value = "newText") String newText) {
        return new ResponseEntity<>(articleService.update(id, postId, newText) ? "Success" :
                "Your are not author of the post", HttpStatus.OK);
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("user/registrate")
    public ResponseEntity<User> registration(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password){
        User user = new User();
        user.setPassword(password);
        user.setFirstName(name);
        user.setEmail(email);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("user/auth")
    public ResponseEntity<String> authorization(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password){
        return new ResponseEntity<>(userService.checkUser(email, password) ? "Success" :
                "User wasn't registrated", HttpStatus.OK);
    }

    @PostMapping("user/auth/forgotPassword")
    public ResponseEntity<Integer> forgotPass(
            @RequestParam(value = "email") String email){
        return new ResponseEntity<>(userService.generateCode(email), HttpStatus.OK);
    }

    @PostMapping("user/auth/reset")
    public ResponseEntity<String> reset(
            @RequestParam(value = "code") Integer code,
            @RequestParam(value = "newPassword") String newPassword){
        return new ResponseEntity<>(userService.reset(code, newPassword) ? "Success" : "Wrong code", HttpStatus.OK);
    }
}

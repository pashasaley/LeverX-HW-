package blog.controller;

import blog.model.Article;
import blog.service.ArticleService;
import blog.service.ArticleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {
    private ArticleService articleService = new ArticleServiceImpl();

    @PostMapping
    public ResponseEntity<Article> addArticle(
            //@RequestParam(value = "name")String authorName, - think about it
            @RequestParam(value="title") String title,
            @RequestParam(value = "text") String text,
            @RequestParam(value = "status") Article.Status status){
        Article article = new Article();
        article.setText(text);
        article.setTitle(title);
        article.setStatus(status);
        articleService.save(article);
        //set ides
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getPublicArticles(){
        return new ResponseEntity<>(articleService.getPublicArticle(), HttpStatus.OK);
    }

    /*@GetMapping - подумать как передать юзера, пут и делете - тоже
    public ResponseEntity<List<Article>> getUsersArticle(){
        return new ResponseEntity<>(articleService.getMyArticle())
    }*/
}

package blog.controller;

import blog.model.Article;
import blog.model.Comment;
import blog.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleCommentController {
    private ArticleService articleService = new ArticleServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Integer id,
            @RequestParam(value = "text") String text){
        Comment comment = new Comment();
        comment.setText(text);
        comment.setPostId(id);
        comment.setAuthorId(articleService.getAuthorById(id));
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<Comment>> showPostComments(
            @PathVariable Integer postId){
        return new ResponseEntity<>(commentService.getPostComments(postId), HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}/comments/{id}")
    public ResponseEntity<Comment> showComment(
            @PathVariable Integer postId, @PathVariable Integer id){
        return new ResponseEntity<>(commentService.getPostComments(postId).get(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer postId,
                                                @PathVariable Integer authorId){
        return new ResponseEntity<>(commentService.delete(postId, authorId) ? "Success" :
                "Your are not the author of the article",  HttpStatus.NO_CONTENT);
    }

    @PostMapping
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

    @GetMapping
    public ResponseEntity<List<Article>> getPublicArticles(){
        return new ResponseEntity<>(articleService.getPublicArticle(), HttpStatus.OK);
    }

    @GetMapping("/changeStatus/{id}")
    public ResponseEntity changeStatus(@PathVariable Integer id,
                                               @RequestParam(value = "status") Article.Status status){
        articleService.changeStatus(id,status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Article>> getUsersArticle(
            @RequestParam(value = "id") Integer id){
        return new ResponseEntity<>(articleService.getMyArticle(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Integer authorId,
                                                @RequestParam(value = "postId") Integer id){
        return new ResponseEntity<String>(articleService.delete(id, authorId) ? "Success" :
                "Your are not the author of the article",  HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Integer id,
                                                @RequestParam(value = "postId") Integer postId,
                                                @RequestParam(value = "newText") String newText) {
        return new ResponseEntity<>(articleService.update(id, postId, newText) ? "Success" :
                "Your are not author of the post", HttpStatus.OK);
    }

}
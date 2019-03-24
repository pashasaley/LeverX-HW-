package blog.service;

import blog.model.Article;
import blog.model.Comment;

import java.util.List;

public interface CommentService {
    void save(Comment comment, Article article);

    List<Comment> getPostComments(Article article);

    void deleteComment(Article article);
}

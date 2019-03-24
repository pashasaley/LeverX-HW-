package blog.service;

import blog.model.Article;
import blog.model.User;

import java.util.List;

public interface ArticleService {
    boolean update(Article article, Integer authorId);

    void save(Article article);

    List<Article> getPublicArticle();

    List<Article> getMyArticle(User user);

    void delete(User user);
}

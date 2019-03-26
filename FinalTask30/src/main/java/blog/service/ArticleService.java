package blog.service;

import blog.model.Article;

import java.util.List;

public interface ArticleService {
    boolean update(Integer id, Integer authorId, String newText);

    void save(Article article);

    void changeStatus(Integer id, Article.Status status);

    List<Article> getPublicArticle();

    List<Article> getMyArticle(Integer id);

    boolean delete(Integer id, Integer authorId);

    Integer getAuthorById(Integer id);
}

package blog.repository;

import blog.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl implements AbstractRepository<Article> {
    private List<Article> articles = new ArrayList<>();

    @Override
    public void create(Article article) {
        articles.add(article);
    }

    @Override
    public List<Article> getAll() {
        return articles;
    }

    @Override
    public void delete(Article article) {
        articles.remove(article);
    }

    @Override
    public Article getById(Integer id) {
        return articles.get(id);
    }
}

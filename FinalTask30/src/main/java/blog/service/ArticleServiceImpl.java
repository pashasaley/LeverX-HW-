package blog.service;

import blog.model.Article;
import blog.repository.AbstractRepository;
import blog.repository.ArticleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private AbstractRepository<Article> articleRepository = new ArticleRepositoryImpl();
    private Integer id = 0;

    @Override
    public boolean update(Integer id, Integer authorId, String newText){
        boolean updated = false;
        for (Article article : articleRepository.getAll()){
            if(article.getAuthotId().equals(authorId) && article.getId().equals(id)){
                article.setText(newText);
                updated = true;
            }
        }
        return updated;
    }

    @Override
    public void save(Article article){
        article.setId(id);
        id++;
        articleRepository.create(article);
    }

    @Override
    public void changeStatus(Integer id, Article.Status status) {
        articleRepository.getById(id).setStatus(status);
    }

    @Override
    public List<Article> getPublicArticle(){
        List<Article> publicArticles = new ArrayList<>();
        for (Article article : articleRepository.getAll()){
            if(article.getStatus().equals(Article.Status.PUBLIC)){
                publicArticles.add(article);
            }
        }
        return publicArticles;
    }

    @Override
    public List<Article> getMyArticle(Integer id){
        List<Article> myArticle = new ArrayList<>();
        for(Article article : articleRepository.getAll()){
            if(id.equals(article.getAuthotId())){
                myArticle.add(article);
            }
        }
        return myArticle;
    }

    @Override
    public boolean delete(Integer id, Integer authorId) {
        boolean deleted = false;
        for(Article article : articleRepository.getAll()){
            if(authorId.equals(article.getAuthotId()) && id.equals(article.getId())){
                articleRepository.getAll().remove(article);
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    @Override
    public Integer getAuthorById(Integer id) {
        return articleRepository.getById(id).getAuthotId();
    }
}

package blog.service;

import blog.model.Article;
import blog.model.User;
import blog.repository.AbstractRepository;
import blog.repository.ArticleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private AbstractRepository<Article> articleRepository = new ArticleRepositoryImpl();

    @Override
    public boolean update(Article article, Integer authorId){
        return article.getAuthotId().equals(authorId);
    }

    @Override
    public void save(Article article){
        if (article!=null){
            List<Article> articles = articleRepository.getAll();
            if(!articles.isEmpty()){
                Article lastArticle = articles.get(articles.size() - 1);
                article.setId(lastArticle.getId());
                articleRepository.save(article);
            }
        }
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
    public List<Article> getMyArticle(User user){
        List<Article> myArticle = new ArrayList<>();
        for(Article article : articleRepository.getAll()){
            if(user.getId().equals(article.getAuthotId())){
                myArticle.add(article);
            }
        }
        return myArticle;
    }

    @Override
    public void delete(User user) {
        for(Article article : articleRepository.getAll()){
            if(user.getId().equals(article.getAuthotId())){
                articleRepository.getAll().remove(article);
            }
        }
    }
}

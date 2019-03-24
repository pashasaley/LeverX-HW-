package blog.service;

import blog.model.Article;
import blog.model.Comment;
import blog.repository.AbstractRepository;
import blog.repository.CommentRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    AbstractRepository<Comment> commentRepository = new CommentRepositoryImpl();

    @Override
    public void save(Comment comment, Article article) {
        if (comment!=null){
            List<Comment> comments = commentRepository.getAll();
            if(!comments.isEmpty()){
                Comment lastComment = comments.get(comments.size() - 1);
                comment.setId(lastComment.getId());
                comment.setAuthorId(article.getAuthotId());
                comment.setPostId(article.getId());
                commentRepository.save(comment);
            }
        }
    }

    @Override
    public List<Comment> getPostComments(Article article) {
        List<Comment> postComment = new ArrayList<>();
        for (Comment comment : commentRepository.getAll()){
            if (comment.getPostId().equals(article.getId())){
                postComment.add(comment);
            }
        }
        return postComment;
    }

    @Override
    public void deleteComment(Article article) {
        for (Comment comment : commentRepository.getAll()){
            if (comment.getPostId().equals(article.getId())){
                commentRepository.getAll().remove(comment);
            }
        }
    }
}

package blog.service;

import blog.model.Comment;
import blog.repository.AbstractRepository;
import blog.repository.CommentRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    AbstractRepository<Comment> commentRepository = new CommentRepositoryImpl();
    private Integer id = 0;

    @Override
    public void save(Comment comment) {
        comment.setId(id);
        id++;
        commentRepository.create(comment);
    }

    /*@Override
    public Comment getById(Integer id) {
        return commentRepository.getById(id);
    }*/

    @Override
    public List<Comment> getPostComments(Integer id) {
        List<Comment> postComment = new ArrayList<>();
        for (Comment comment : commentRepository.getAll()){
            if (comment.getPostId().equals(id)){
                postComment.add(comment);
            }
        }
        return postComment;
    }

    @Override
    public boolean delete(Integer postId, Integer authorId) {
        boolean deleted = false;
        for (Comment comment : commentRepository.getAll()){
            if (comment.getPostId().equals(postId) && comment.getAuthorId().equals(authorId)){
                commentRepository.getAll().remove(comment);
                deleted = true;
            }
        }
        return deleted;
    }
}

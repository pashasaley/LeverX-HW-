package blog.service;

import blog.model.Comment;

import java.util.List;

public interface CommentService {
    void save(Comment comment);

    //Comment getById(Integer id);

    List<Comment> getPostComments(Integer id);

    boolean delete(Integer postId, Integer AuthorId);
}

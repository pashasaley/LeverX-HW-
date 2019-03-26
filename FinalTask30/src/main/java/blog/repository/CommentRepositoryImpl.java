package blog.repository;

import blog.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements AbstractRepository<Comment> {
    private List<Comment> comments = new ArrayList<>();
    @Override
    public void create(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getAll() {
        return comments;
    }

    @Override
    public void delete(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public Comment getById(Integer id) {
        return comments.get(id);
    }
}

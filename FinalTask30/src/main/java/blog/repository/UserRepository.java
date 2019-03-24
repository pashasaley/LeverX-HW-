package blog.repository;

import blog.model.User;

import java.util.List;

public interface UserRepository {

    User getByEmail(String email);

    void save(User user);

    void delete(User user);

    List<User> getAll();

    User getById(Integer id);
}


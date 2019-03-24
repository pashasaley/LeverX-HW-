package blog.service;

import blog.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void delete(User order);

    List<User> getAll();

    User getById(Integer id);
}


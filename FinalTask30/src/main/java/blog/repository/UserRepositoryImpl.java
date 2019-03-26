package blog.repository;

import blog.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements AbstractRepository<User> {
    List<User> users = new ArrayList<>();

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public void delete(User user) {
       users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(Integer id) {
        return users.get(id);
    }
}

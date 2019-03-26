package blog.service;

import blog.model.User;

public interface UserService {

    boolean checkUser(String email, String password);

    boolean checkEmail(String email);

    boolean reset(Integer code, String newPassword);

    Integer generateCode(String email);

    void save(User user);

    User getById(Integer id);
}


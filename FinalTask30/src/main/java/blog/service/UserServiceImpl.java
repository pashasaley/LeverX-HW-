package blog.service;

import blog.model.User;
import blog.repository.UserRepository;
import blog.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();

    public void save(User user){
        if(user!=null) {
            userRepository.save(user);
        }
    }

    public void delete(User user){
        if(user != null){
            userRepository.delete(user);
        }
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getById(Integer id){
        if(id != null) {
            return userRepository.getById(id);
        }
        return null;
    }
}

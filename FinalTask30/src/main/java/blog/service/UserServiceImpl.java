package blog.service;

import blog.model.User;
import blog.repository.AbstractRepository;
import blog.repository.UserRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

//@Service
public class UserServiceImpl implements UserService {
    private AbstractRepository<User> userRepository = new UserRepositoryImpl();
    private Integer id = 0;
    private boolean hasAccount = false;
    private Map<String, Integer> codeToEmail = new HashMap<>();

    @Override
    public boolean checkUser(String email, String password) {
        boolean hasAccount = false;
        for (User user : userRepository.getAll()){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                hasAccount = true;
                break;
            }
        }
        return hasAccount;
    }

    @Override
    public boolean checkEmail(String email) {
        hasAccount = false;
        for (User user : userRepository.getAll()){
            if(user.getEmail().equals(email)){
                hasAccount = true;
                break;
            }
        }
        return hasAccount;
    }

    @Override
    public boolean reset(Integer code, String newPassword) {
        hasAccount = false;
        for (Map.Entry<String, Integer> entry : codeToEmail.entrySet()){
            if (entry.getValue().equals(code)){
                for (User u : userRepository.getAll()){
                    if (u.getEmail().equals(entry.getKey())) {
                        u.setPassword(newPassword);
                        hasAccount = true;
                        break;
                    }
                }
            }
        }
        return hasAccount;
    }

    @Override
    public Integer generateCode(String email) {
        Integer code = (int) (Math.random() * 8999 + 1000);
        if(checkEmail(email)) {
            codeToEmail.put(email, code);
            return codeToEmail.get(email);
        }else {
            return null;
        }
    }

    @Override
    public void save(User user){
        if(user!=null) {
            user.setId(id);
            userRepository.create(user);
            id++;
        }
    }

    @Override
    public User getById(Integer id){
        if(id != null) {
            return userRepository.getById(id);
        }
        return null;
    }
}

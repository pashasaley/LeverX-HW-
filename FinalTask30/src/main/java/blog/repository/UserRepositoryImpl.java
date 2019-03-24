package blog.repository;

import blog.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private List<User> users = new ArrayList<User>();

    public UserRepositoryImpl(){
        /*User user = new User();
        user.setId(1);
        user.setFirstName("Sanya");
        user.setPassword("0987654321");
        user.setEmail("sashagalay99@yandex.ru");*/
    }

    @Override
    public User getByEmail(String email){
        User temp = new User();
        for (User user : users){
            if(user.getEmail().equals(email)){
                temp.setId(user.getId());
                temp.setPassword(user.getPassword());
                temp.setFirstName(user.getFirstName());
                temp.setEmail(user.getEmail());
                break;
            }
        }
        return temp;
    }

    @Override
    public void save(User user){
        users.add(user);
    }

    @Override
    public void delete(User user){
        users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(Integer id){
        return users.get(id);
    }
}

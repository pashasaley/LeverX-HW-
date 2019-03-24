package blog.controller;

import blog.model.User;
import blog.service.UserService;
import blog.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private UserService userService = new UserServiceImpl();
    private boolean hasAccount = false;
    private Map<String, Integer> codeToEmail = new HashMap<>();
//fix


    public UserService getUserService() {
        return userService;
    }

    @GetMapping
    public ResponseEntity<User> getUser(String name){
        User user = null;
        for (User u : userService.getAll()){
            if (u.getFirstName().equals(name)){
                user = u;
                break;
            }
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/registrate")
    public ResponseEntity<User> registration(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password){
        User user = new User();
        user.setPassword(password);
        user.setFirstName(name);
        user.setEmail(email);
        user.setId(0);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authorization(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password){
        for (User user : userService.getAll()){
            if(user.getEmail().equals(email)&&user.getPassword().equals(password)){
                hasAccount = true;
                break;
            }
        }
        return new ResponseEntity<>(hasAccount ? "Success" :
                "User isn't registrated", HttpStatus.OK);
    }

    @PostMapping("/auth/forgotPassword")
    public ResponseEntity<Integer> authorization(
            @RequestParam(value = "email") String email){
        Integer code = (int) (Math.random() * 8999 + 1000);
        codeToEmail.put(email,code);
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @PostMapping("/auth/reset")
    public ResponseEntity<String> authorization(
            @RequestParam(value = "code") Integer code,
            @RequestParam(value = "newPassword") String newPassword){
        hasAccount = false;
        for (Map.Entry<String, Integer> entry : codeToEmail.entrySet()){
            if (entry.getValue().equals(code)){
                for (User u : userService.getAll()){
                    if (u.getEmail().equals(entry.getKey())) {
                        u.setPassword(newPassword);
                        hasAccount = true;
                        break;
                    }
                }
            }
        }
        return new ResponseEntity<>(hasAccount ? "Success" : "Wrong code", HttpStatus.OK);
    }
}

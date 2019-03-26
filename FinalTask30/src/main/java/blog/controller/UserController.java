package blog.controller;

import blog.model.User;
import blog.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private UserService userService = new UserServiceImpl();

    @GetMapping(value = "user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("user/registrate")
    public ResponseEntity<User> registration(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password){
        User user = new User();
        user.setPassword(password);
        user.setFirstName(name);
        user.setEmail(email);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("user/auth")
    public ResponseEntity<String> authorization(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password){
        return new ResponseEntity<>(userService.checkUser(email, password) ? "Success" :
                "User wasn't registrated", HttpStatus.OK);
    }

    @PostMapping("user/auth/forgotPassword")
    public ResponseEntity<Integer> forgotPass(
            @RequestParam(value = "email") String email){
        return new ResponseEntity<>(userService.generateCode(email), HttpStatus.OK);
    }

    @PostMapping("user/auth/reset")
    public ResponseEntity<String> reset(
            @RequestParam(value = "code") Integer code,
            @RequestParam(value = "newPassword") String newPassword){
        return new ResponseEntity<>(userService.reset(code, newPassword) ? "Success" : "Wrong code", HttpStatus.OK);
    }
}

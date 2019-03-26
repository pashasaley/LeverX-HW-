package blog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    //add second name, date
    private Integer id;
//аннотации для дат @CreatedDate @LastModifiedDate
    @NotNull
    @Size(min=2, max = 30)
    private String firstName;

    @NotNull
    @Size(min=2, max = 30)
    private String password;

    @NotNull
    @Size(min=2, max = 30)
    private String email;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", first name=" + firstName + ", email=" + email + "]";
    }
}


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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        if (id == null) {
            if (user.id != null)
                return false;
        } else if (!id.equals(user.id))
            return false;
        if (firstName == null) {
            if (user.firstName != null)
                return false;
        } else if (!firstName.equals(user.firstName))
            return false;
        if (email == null) {
            return user.email == null;
        } else return email.equals(user.email);
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", first name=" + firstName + ", email=" + email + "]";
    }
}


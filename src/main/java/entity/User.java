package entity;

import enums.Status;
import enums.Status2;
import enums.UserRole;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private Status status;
    private Status2 status2;


    @Override
    public String toString() {
        return "name: " + name + " || email: " + email + " || password: " + password;
    }
}

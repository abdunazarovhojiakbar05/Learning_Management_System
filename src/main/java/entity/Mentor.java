package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mentor {

    private String name;
    private String id;
    private String email;

    @Override
    public String toString() {
        return "name: " + name + " || id: " + id + " || email: " + email;
    }
}

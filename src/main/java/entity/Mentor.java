package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {

    private String name;
    private String id;
    private String email;

    @Override
    public String toString() {
        return "name: " + name + " || id: " + id + " || email: " + email;
    }
}

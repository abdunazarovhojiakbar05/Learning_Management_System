package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Course {

    private String id;
    private String name;
    private double price;
    private String duration;
    private LocalTime startTime;
    private LocalTime endTime;
    private Mentor mentor;

    @Override
    public String toString() {
        return "name: " + name + " || price: " + price + " || duration: " + duration + " || start: " + startTime + " || end: " + endTime + " || mentor: " + mentor;
    }

}

package entity;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString(of = {"creationDate", "courseName", "userName"})
@AllArgsConstructor
@NoArgsConstructor

public class Enrollment {

    private String enrollmentId;
    private String userID;
    private String CourseID;
    private LocalDate creationDate;
    private String courseName;
    private String userName;




}

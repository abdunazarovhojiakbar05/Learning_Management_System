package dto;

import java.time.LocalDate;

public record EnrollmentDTO(String enrollmentId, String courseId, String userId, LocalDate creationDate, String courseName,  String userName) {
}

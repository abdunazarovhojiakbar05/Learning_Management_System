package dto;

import entity.Mentor;
import entity.User;

import java.time.LocalTime;

public record CourseDTO(String courseId, String courseName, double price, String duration, LocalTime startTime, LocalTime endTime, Mentor mentor) {
}

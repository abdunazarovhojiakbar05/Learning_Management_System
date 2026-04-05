package dto;

import entity.User;

public record CourseDTO(String courseId, String courseName, int price, String duration, String startTime, String endTime, User mentor) {
}

package repository;

import dto.CourseDTO;
import entity.Course;
import entity.Mentor;
import entity.User;
import enums.Status;
import enums.Status2;
import enums.UserRole;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CourseRepository {


    private static volatile CourseRepository instance;


    private CourseRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static CourseRepository getInstance() {
        if (instance == null) {
            synchronized (CourseRepository.class) {
                if (instance == null) {
                    instance = new CourseRepository();
                }
            }
        }
        return instance;
    }


    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String dbUser = "postgres";
        String dbPass = "123";
        return DriverManager.getConnection(url, dbUser, dbPass);


    }


    public List<Course> searchUsersByName(String namePart) {
        String sql = "SELECT * FROM courses WHERE name ILIKE ?";

        List<Course> courseList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + namePart + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = mapResultSetToUser(rs);
                    courseList.add(course);
                }
            }
        } catch (SQLException e) {
            System.err.println("Qidiruvda xatolik: " + e.getMessage());
        }
        return courseList;
    }

    private Course mapResultSetToUser(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getString("id"));
        course.setName(rs.getString("name"));
        course.setDuration(rs.getString("duration"));
        course.setStartTime(rs.getTime("startTime").toLocalTime());
        course.setEndTime(rs.getTime("endTime").toLocalTime());
        course.setMentor(rs.getObject("mentor", User.class));

        return course;
    }

    public boolean saveCourse(CourseDTO courseDTO) {
        String sql = "INSERT INTO courses (id, name, price,  duration, \"startTime\", \"endTime\", mentor ) VALUES (?, ?, ?, ?, ?, ?, ? )";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, courseDTO.courseId());
            ps.setString(2, courseDTO.courseName());
            ps.setDouble(3, courseDTO.price());
            ps.setString(4, courseDTO.duration());

            ps.setObject(5, courseDTO.startTime(), java.sql.Types.TIME);
            ps.setObject(6, courseDTO.endTime(), java.sql.Types.TIME);

            ps.setString(7, courseDTO.mentor().getEmail());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
package repository;

import dto.UserDTO;
import entity.User;
import enums.Status;
import enums.Status2;
import enums.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static UserRepository userRepository;

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String dbUser = "postgres";
    private final String dbPass = "123";

    private UserRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }


    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Bazadan foydalanuvchini olishda xatolik: " + e.getMessage());
        }
        return null;
    }


    public List<User> searchUsersByName(String namePart) {
         String sql = "SELECT * FROM users WHERE name ILIKE ?";

        List<User> userList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setString(1, "%" + namePart + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = mapResultSetToUser(rs);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Qidiruvda xatolik: " + e.getMessage());
        }
        return userList;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        String role = rs.getString("role");
        if (role != null) user.setRole(UserRole.valueOf(role.toUpperCase()));

        String status = rs.getString("status");
        if (status != null) user.setStatus(Status.valueOf(status.toUpperCase()));

        String status2 = rs.getString("status2");
        if (status2 != null) user.setStatus2(Status2.valueOf(status2.toUpperCase()));

        return user;
    }


    public boolean saveUser(UserDTO user) {

        String sql = "INSERT INTO users (id, name, email, password, role, status, status2) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.id());
            ps.setString(2, user.name());
            ps.setString(3, user.email());
            ps.setString(4, user.password());
            ps.setString(5, user.userRole().name());
            ps.setString(6, user.status().name());
            ps.setString(7, "ACTIVE");

            ps.executeUpdate();
           return true;

        } catch (SQLException e) {
          return false;
        }
    }



    public boolean deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            int rowsAffected = ps.executeUpdate();


            return rowsAffected > 0;

        } catch (SQLException e) {

            return false;
        }
    }


}
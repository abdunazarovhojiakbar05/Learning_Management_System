package service;


import dto.UserDTO;
import entity.User;
import enums.Status2;
import enums.UserRole;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminService {

    static UserRepository userRepository = UserRepository.getInstance();
    private static AdminService adminService;

    private AdminService() {
    }

    public static AdminService getInstance() {
        if (adminService == null) {
            adminService = new AdminService();
        }
        return adminService;
    }


    public boolean activeUser(String email) {


        User user = userRepository.getUserByEmail(email);
        if (Objects.equals(user.getStatus(), Status2.BLOCKED)) {
            user.setStatus2(Status2.ACTIVE);
            return true;
        }
        return false;
    }

    public boolean blockUser(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user != null && user.getStatus().equals(Status2.ACTIVE)) {
            user.setStatus2(Status2.BLOCKED);
            return true;
        }
        return false;
    }

    public boolean saveUser(UserDTO userDTO) {
        return userRepository.saveUser(userDTO);
    }

    public List<User> getUserByNamePart(String name) {

        List<User> user = userRepository.searchUsersByName(name);

        return new ArrayList<>(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public boolean deleteUserByEmail(String email) {

        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            return false;
        }

        if (user.getRole() == UserRole.ADMIN) {
            return false;
        }

        return userRepository.deleteUser(email);

    }

    public boolean updateUser(User u) {


        return userRepository.updateUser(u);
    }

    public boolean updatePassword(User u) {

        return userRepository.updateUser(u);
    }
}

package service;

import dto.UserDTO;
import entity.User;
import enums.Status2;
import enums.UserRole;
import repository.UserRepository;

import java.util.Objects;

public class UserService {

    static UserRepository userRepository = UserRepository.getInstance();
    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void saveUser(UserDTO user) {
        userRepository.saveUser(user);
    }



}

package service;

import entity.User;
import enums.UserRole;
import repository.UserRepository;

import java.util.Objects;

public class AuthService {


    static UserRepository userRepository = UserRepository.getInstance();
    private static AuthService  authService;

    private AuthService() {
    }

    public static AuthService getInstance() {
        if (authService == null) {
            authService = new AuthService() ;
        }
        return authService;
    }


    public boolean checkUserAndCode(int code, String email, int num) {
        User user = userRepository.getUserByEmail(email);
        return !Objects.nonNull(user) && code == num;
    }

    public UserRole checkUser(String email, String password) {
        User allUser = userRepository.getUserByEmail(email);
        if (allUser!=null&&allUser.getPassword().equals(password)) {
            return allUser.getRole();
        }
        return null;
    }
}

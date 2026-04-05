package service;


import dto.UserDTO;
import entity.User;
import enums.Status2;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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


        User user = userRepository.getAllUser(email);
        if(user.getStatus().equals(Status2.BLOCKED)) {
            user.setStatus2(Status2.ACTIVE);
            return true;
        }
        return false;
    }

    public boolean blockUser(String email) {
        User user = userRepository.getAllUser(email);
        if( user != null && user.getStatus().equals(Status2.ACTIVE)) {
            user.setStatus2(Status2.BLOCKED);
            return true;
        }
        return false;
    }

    public boolean saveUser(UserDTO userDTO) {
      return   userRepository.saveUser(userDTO);
    }

    public List<User> getUserByNamePart(String name) {

        List<User> user=  userRepository.searchUsersByName(name);

        return new ArrayList<>(user);
    }
}

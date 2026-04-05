package controller.admin_controller;

import dto.UserDTO;
import entity.User;
import enums.Status;
import enums.Status2;
import enums.UserRole;
import service.AdminService;

import java.util.List;

import static util.Util.*;

public class CrudUser {

    static AdminService adminService = AdminService.getInstance();

    public static void menu() {
        while (true) {
            System.out.println("""
                    1. Create User
                    2. Read User
                    3. Update User
                    4. Delete User
                    5. back
                    """);

            int menu = getNum("choose one");

            switch (menu) {
                case 1 -> createUser();
                case 2 -> ReadUser();
                case 3 -> UpdateUser();
                case 4 -> DeleteUser();
                case 0 -> {
                    AdminController.main();
                }
                default -> System.out.println("Invalid Input");
            }
        }
    }

    public static void createUser() {
        String id = getUserId();
        String name = getStr("name");
        String email = getStr("email");
        String password = getStr("password");
        Status status = Status.DEFAULT;
        Status2 status2 = Status2.ACTIVE;
        UserRole role = UserRole.USER;

        adminService.saveUser(new UserDTO(id, name, email, password, status, status2, role));


    }

    public static void ReadUser() {
        System.out.println(" <= find with name part => ");
        String name = getStr("enter name part or full");
        List<User> user = adminService.getUserByNamePart(name);
        user.forEach(System.out::println);
        if (user.isEmpty()) System.out.println("User not found");

    }

    public static void UpdateUser() {
        ReadUser();





    }

    public static void DeleteUser() {
    }

}

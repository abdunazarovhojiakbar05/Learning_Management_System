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
                case 4 -> deleteUser();
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
        String name = getStr("Yangilash uchun ism qismini kiriting");
        List<User> users = adminService.getUserByNamePart(name);

        if (users.isEmpty()) {
            System.out.println("Foydalanuvchi topilmadi.");
            return;
        }

        users.forEach(System.out::println);
        String email = getStr("Aniq qaysi foydalanuvchi? (Emailni kiriting)");
        User u = adminService.getUserByEmail(email);

        if (u != null) {
            updateUser(u);
        } else {
            System.out.println("Bunday emailli foydalanuvchi yo'q.");
        }
    }

    public static void updateUser(User u) {
        System.out.println("""
                1. update name
                2. update password
                3. back
                """);

        int menu = getNum("choose one");
        switch (menu) {
            case 1 -> updateName(u);
            case 2 -> updatePassword(u);
            case 3 -> {
                return;
            }
        }
    }

    public static void deleteUser() {

        ReadUser();

        String name = getStr("enter name part or full");
        List<User> user = adminService.getUserByNamePart(name);
        if (!user.isEmpty()) {
            user.forEach(System.out::println);
            String email = getStr("enter email");
            boolean res = adminService.deleteUserByEmail(email);
            if (res) System.out.println("User deleted");
            else System.out.println("User not found");

        }
        System.out.println("User not found");
    }

    public static void updateName(User u) {
        System.out.println("Eski ismingiz: " + u.getName());
        String newName = getStr("Yangi ismni kiriting: ");

        u.setName(newName);


        boolean success = adminService.updateUser(u);
        if (success) {
            System.out.println("✅ Ism muvaffaqiyatli o'zgartirildi!");
        } else {
            System.out.println("❌ Xatolik: Bazada ismni o'zgartirib bo'lmadi.");
        }
    }

    public static void updatePassword(User u) {
        System.out.println("your new password is " + u.getPassword());
        String str = getStr("enter new password");
        u.setPassword(str);

        boolean success = adminService.updatePassword(u);
        if (success) {
            System.out.println("✅ parol muvaffaqiyatli o'zgartirildi!");
        } else {
            System.out.println("❌ Xatolik: Bazada parolni o'zgartirib bo'lmadi.");

        }
    }
}

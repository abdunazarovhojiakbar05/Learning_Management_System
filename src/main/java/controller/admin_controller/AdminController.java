package controller.admin_controller;

import util.Util;

public class AdminController {
    public static void main() {

        while (true) {
            System.out.println("""
                    --- ADMIN PANEL ---
                    1. Crud courses
                    2. Crud Users
                    3. Blocking User
                    4. Active User
                    5. Log out (Chiqish)
                    """);
            int menu = Util.getNum("choose one");

            String email = "test@gmail.com";
            switch (menu) {
                case 1 -> CrudCourse.menu();
                case 2 -> CrudUser.menu();
                case 3 -> BlockingUser.menu(email);
                case 4 -> ActiveUser.menu(email);
                case 5 -> {
                    return;
                }
                default -> System.out.println("invalid input");
            }

        }
    }


}

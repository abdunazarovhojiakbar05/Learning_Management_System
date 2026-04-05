package controller.user_controller;

import static util.Util.getNum;

public class ProfileSettings {
    public static void profileSettings() {
        while (true) {
            System.out.println("""
                    1. Edit Name
                    2. Change Profile Photo
                    3. Change Password
                    0. back
                    """);
            int menu = getNum("choose one");
            switch (menu) {
                case 1 -> {}
                case 2 -> {}
                case 3 -> {}
                case 0  ->  UserController.menu();
                default -> {
                    System.out.println("invalid menu");
                }
            }

        }
    }
}

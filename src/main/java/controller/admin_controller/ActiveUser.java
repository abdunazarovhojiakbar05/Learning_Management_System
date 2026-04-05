package controller.admin_controller;

import service.AdminService;
import service.UserService;

public class ActiveUser {
    static AdminService adminService = AdminService.getInstance();
    public static void menu(String email) {

        boolean res = adminService.activeUser(email);

        if (res) System.out.println(" user status \"ACTIVE\"");
        else System.out.println("user status unchanged");

    }
}

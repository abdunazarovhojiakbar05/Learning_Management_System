package controller.admin_controller;

import service.AdminService;

public class BlockingUser {


    static AdminService adminService = AdminService.getInstance();

    public static void menu(String email) {

        boolean res = adminService.blockUser(email);

        if (res) System.out.println(" user status \"BLOCKED\"");
        else System.out.println("user status unchanged");

    }

}

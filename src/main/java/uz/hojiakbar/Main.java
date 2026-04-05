package uz.hojiakbar;

import controller.AuthController;
import database.DatabaseSeeder.DatabaseSeeder;

public class Main {
    public static void main(String[] args) {

       // DatabaseSeeder.seedUsers(50);

        AuthController authController = new AuthController();
        authController.main();
    }
}
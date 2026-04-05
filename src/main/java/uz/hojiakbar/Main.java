package uz.hojiakbar;

import controller.AuthController;
import database.DatabaseSeeder.DatabaseSeeder;
import database.DatabaseSeeder.DatabaseSeeder2;

public class Main {
    public static void main(String[] args) {

     //  DatabaseSeeder.seedUsers(50);
        DatabaseSeeder2.seedUsers(20);

        AuthController authController = new AuthController();
        authController.main();
    }
}
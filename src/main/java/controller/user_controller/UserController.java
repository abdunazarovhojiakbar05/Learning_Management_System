package controller.user_controller;

import static util.Util.getNum;

public class UserController {
    public static void menu() {

        System.out.println("""
                 1. Barcha kurslar
                 2. Mening kurslarim
                 3. Test topshirish
                 4. Sertifikatlarim
                 5. Profil sozlamalari
                 0. Chiqish
                """);

        int menu = getNum("menuni tanlang");

        switch (menu) {
            case 1 -> AllCourses.allCourses();
            case 2 -> MyCourses.myCourses();
            case 3 -> Testing.testing();
            case 4 -> MyCertificates.myCertificates();
            case 5 -> ProfileSettings.profileSettings();
            case 0 -> {
                return;
            }
            default -> {
                System.out.println("invalid menu");
            }
        }
    }





}

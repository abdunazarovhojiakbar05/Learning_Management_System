package controller.user_controller;

import static util.Util.getNum;

public class MyCourses {
    public static void myCourses() {
        while (true) {
            System.out.println("""
                    1. Course Categories   /// Kurslarni yo'nalishlar bo'yicha saralash.
                    2. Search & Filter    ///Kerakli kursni tez topish.
                    3. Enrollment        /// Kursga yozilish (ro'yxatdan o'tish) tugmasi.
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

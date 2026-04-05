package controller.user_controller;

import static util.Util.getNum;

public class Testing {
    public static void testing() {
        while (true) {
            System.out.println("""
                    1. Take a Qui            /// Onlayn test topshirish.
                    2. Submit Assignment    /// Uy vazifasini fayl shaklida yuklash.
                    3. Grades / Results    /// Olgan ballarini ko'rish.
                    0. back
                    """);
            int menu = getNum("choose one");
            switch (menu) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 0 -> {
                    UserController.menu();
                }
                default -> {
                    System.out.println("invalid menu");
                }
            }

        }
    }
}

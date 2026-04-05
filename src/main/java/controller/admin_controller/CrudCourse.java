package controller.admin_controller;

import static util.Util.getNum;

public class CrudCourse {
    public static void menu() {

        while (true) {
            System.out.println("""
                    1. Create Course
                    2. Read Course
                    3. Update Course
                    4. Delete Course
                    5. back
                    """);

            int menu = getNum("choose one");

            switch (menu) {
                case 1 -> createCourse();
                case 2 -> ReadCourse();
                case 3 -> UpdateCourse();
                case 4 -> DeleteCourse();
                case 0 -> {
                    AdminController.main();
                }
                default -> System.out.println("Invalid Input");
            }
        }
    }


    public static void createCourse() {

    }

    public static void ReadCourse() {
    }

    public static void UpdateCourse() {
    }

    public static void DeleteCourse() {
    }
}

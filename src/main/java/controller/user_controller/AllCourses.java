package controller.user_controller;

import static util.Util.getNum;

public class AllCourses {
    public static void allCourses() {

            while (true) {
                System.out.println("""
                    1. Show Active Courses    /// hozirda davom etayotgan va oqiyotgan  kurslar 
                    2. Progress Tracking     ///testlardan toplangan ballarni ortacha hisobi
                    3. Upcoming Deadlines   ///yaqin orada topshirilishi kerak bolgan testlar 
                    0. back
                    """);

                int menu = getNum("choose one");
                switch (menu) {
                    case 1 -> {}
                    case 2 -> {}
                    case 3 -> {}
                    case 0  -> UserController.menu();
                    default -> {
                        System.out.println("invalid menu");
                    }
                }
            }
        }
    }


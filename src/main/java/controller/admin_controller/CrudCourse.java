package controller.admin_controller;

import dto.CourseDTO;
import entity.Course;
import entity.Mentor;
import service.CourseService;

import java.time.LocalTime;
import java.util.List;

import static util.Util.*;

public class CrudCourse {

    static CourseService courseService = CourseService.getInstance();

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
        String id = getCourseId();
        String name = getStr("enter name");
        double price = getNum("enter price");
        String duration = getStr("enter duration");
        LocalTime startTime = LocalTime.parse(getStr("enter start time"));
        LocalTime endTime = LocalTime.parse(getStr("enter end time"));

        String mentorName = getStr("enter mentor name");
        String mentorId = getMentorId();
        String email = getStr("enter email");

        Mentor mentor = new Mentor(mentorName, mentorId, email);

        boolean res = courseService.saveCourse(new CourseDTO(id, name, price, duration, startTime, endTime, mentor));

        if (res) System.out.println("Course Created");
        else System.out.println("Course already exists or wrong fields");

    }

    public static List<Course> ReadCourse() {
        System.out.println("<= enter course part or full name =>");
        String name = getStr("enter name");
        List<Course> course = courseService.getCourseByPartOrFull(name);
        course.forEach(System.out::println);
        return course;
    }

    public static void UpdateCourse() {

        List<Course> course = ReadCourse();
        course.forEach(System.out::println);

    }

    public static void DeleteCourse() {
    }
}

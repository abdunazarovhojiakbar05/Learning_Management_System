package controller.admin_controller;

import dto.CourseDTO;
import entity.Course;
import entity.Mentor;
import entity.User;
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
                    0. back
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

        String name = getStr("enter name");
        updateCourse(name);

    }

    public static void DeleteCourse() {
        ReadCourse();

        String name = getStr("enter course name part or full");
        List<Course> course = courseService.getCourseByPartOrFull(name);
        if (!course.isEmpty()) {
            course.forEach(System.out::println);
            String nameCourse = getStr("enter name");
            boolean res = courseService.deleteCourseByEmail(nameCourse);
            if (res) System.out.println("User deleted");
            else System.out.println("User not found");

        }
        System.out.println("User not found");
    }


    public static void updateCourse(String name) {

        Course course = courseService.getCourseByName(name);

        System.out.println("""
                1. change name
                2. change price
                3. change duration
                4. change start
                5. change mentor
                0. back
                """);
        int menu = getNum("choose one");
        switch (menu) {
            case 1 -> changeName(course);
            case 2 -> changePrice(course);
            case 3 -> changeDuration(course);
            case 4 -> changeStart(course);
            case 5 -> ChangeMentor(course);
            case 0 -> {
                return;
            }
        }

    }

    public static void changeName(Course course) {
        System.out.println("cours's old name: " + course.getName());
        String name = getStr("enter new name");
        course.setName(name);

        boolean isUpdated = courseService.updateCourseName(course);

        if (isUpdated) {
            System.out.println("✅ ism muvaffaqiyatli almashtirildi!");
        } else {
            System.out.println("❌ Xatolik: Bazada ismni yangilab bo'lmadi.");
        }

    }

    public static void changePrice(Course course) {
        System.out.println("cours's old price: " + course.getPrice());
        double price = getNum("enter new price");
        course.setPrice(price);

        boolean isUpdated = courseService.updateCoursePrice(course);

        if (isUpdated) {
            System.out.println("✅ narx muvaffaqiyatli almashtirildi!");
        } else {
            System.out.println("❌ Xatolik: Bazada narxni yangilab bo'lmadi.");
        }
    }

    public static void changeDuration(Course course) {
        System.out.println("cours's old duration: " + course.getDuration());
        String duration = getStr("enter new duration");
        course.setDuration(duration);

        boolean isUpdated = courseService.updateCourseDuration(course);

        if (isUpdated) {
            System.out.println("✅ Davomiylik muvaffaqiyatli almashtirildi!");
        } else {
            System.out.println("❌ Xatolik: Bazada davomiylikni yangilab bo'lmadi.");
        }
    }

    public static void changeStart(Course course) {
        System.out.println("cours's old start: " + course.getStartTime());
        LocalTime startTime = LocalTime.parse(getStr("enter start time"));
        course.setStartTime(startTime);

        boolean isUpdated = courseService.updateCourStartTime(course);

        if (isUpdated) {
            System.out.println("✅ boshlanish vaqt muvaffaqiyatli almashtirildi!");
        } else {
            System.out.println("❌ Xatolik: Bazada boshlanish vaqtni yangilab bo'lmadi.");
        }
    }

    public static void ChangeMentor(Course course) {
        System.out.println("Kursning hozirgi mentori: " + course.getMentor().getName());

        String mentorName = getStr("Yangi mentor ismini kiriting: ");
        String mentorId = getMentorId();
        String email = getStr("Yangi mentor emailini kiriting: ");

        Mentor newMentor = new Mentor(mentorName, mentorId, email);

        course.setMentor(newMentor);


        boolean isUpdated = courseService.updateCourseMentor(course);

        if (isUpdated) {
            System.out.println("✅ Mentor muvaffaqiyatli almashtirildi!");
        } else {
            System.out.println("❌ Xatolik: Bazada mentorni yangilab bo'lmadi.");
        }
    }
}
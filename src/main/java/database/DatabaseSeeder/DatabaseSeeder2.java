package database.DatabaseSeeder;

import com.github.javafaker.Faker;
import dto.CourseDTO;
import dto.UserDTO;
import entity.Mentor;
import enums.Status;
import enums.Status2;
import enums.UserRole;
import repository.UserRepository;
import service.AdminService;
import service.CourseService;

import java.time.LocalTime;
import java.util.UUID;

public class DatabaseSeeder2 {

    static CourseService courseService = CourseService.getInstance();

    public static void seedUsers(int count) {
        Faker faker = new Faker();


        System.out.println(count + " ta kurs qo'shilmoqda...");

        for (int i = 0; i < count; i++) {
            String id = UUID.randomUUID().toString();
            String name = faker.name().fullName();
            double price = faker.random().nextDouble();
            String duration = LocalTime.now().toString();
            LocalTime startTime = LocalTime.now();
            LocalTime endTime = LocalTime.now();



            String email = faker.internet().emailAddress(name.toLowerCase().replace(" ", "."));


            CourseDTO courseDTO = new CourseDTO(
                    id,
                    name,
                    price,
                    duration,
                    startTime,
                    endTime,
                    new Mentor(name, id, email)
            );

            boolean isSaved = courseService.saveUser(courseDTO);

            if (isSaved) {
                System.out.println("Qo'shildi: " + name + " (" + email + ")");
            } else {
                System.err.println("Xatolik yuz berdi: " + name);
            }
        }

        System.out.println("\nTabriklayman! Baza to'ldirildi.");
    }
}

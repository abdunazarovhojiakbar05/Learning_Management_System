package database.DatabaseSeeder;

import com.github.javafaker.Faker;
import dto.CourseDTO;
import entity.Mentor;
import service.CourseService;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

public class DatabaseSeeder2 {

    static CourseService courseService = CourseService.getInstance();

    public static void seedUsers(int count) {
        Faker faker = new Faker();

        System.out.println(count + " ta kurs qo'shilmoqda...");

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String id = UUID.randomUUID().toString();
            String name = faker.educator().course();
            double price = random.nextDouble(1_000_000, 5_000_000);

            int num = random.nextInt(2, 5);
            String duration =  num + " hour";



            String startTime1 = random.nextInt(10, 24) + ":" + random.nextInt(10, 60);
            LocalTime startTime =  LocalTime.parse(startTime1);

            LocalTime endTime =  LocalTime.parse(startTime1).plusHours(num);


            String email = faker.internet().emailAddress(name.toLowerCase().replace(" ", "."));

            CourseDTO courseDTO = new CourseDTO(id, name, price, duration, startTime, endTime, new Mentor(name, id, email));

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

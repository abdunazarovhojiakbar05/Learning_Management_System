package database.DatabaseSeeder;

import com.github.javafaker.Faker;
import dto.UserDTO;
import enums.Status;
import enums.Status2;
import enums.UserRole;
import repository.UserRepository;
import service.AdminService;

import java.util.UUID;

public class DatabaseSeeder {

    static AdminService adminService = AdminService.getInstance();

    public static void seedUsers(int count) {
        Faker faker = new Faker();


        System.out.println(count + " ta foydalanuvchi qo'shilmoqda...");

        for (int i = 0; i < count; i++) {
            String id = UUID.randomUUID().toString();
            String name = faker.name().fullName();

            String email = faker.internet().emailAddress(name.toLowerCase().replace(" ", "."));
            String password = "111";

            UserDTO userDTO = new UserDTO(
                    id,
                    name,
                    email,
                    password,
                    Status.DEFAULT,
                    Status2.ACTIVE,
                    UserRole.USER
            );

            boolean isSaved = adminService.saveUser(userDTO);

            if (isSaved) {
                System.out.println("Qo'shildi: " + name + " (" + email + ")");
            } else {
                System.err.println("Xatolik yuz berdi: " + name);
            }
        }

        System.out.println("\nTabriklayman! Baza to'ldirildi.");
    }
}

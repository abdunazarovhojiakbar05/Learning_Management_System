package controller;

import controller.admin_controller.AdminController;
import controller.user_controller.UserController;
import dto.LoginDTO;
import dto.UserDTO;
import enums.Status;
import enums.Status2;
import enums.UserRole;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import service.AuthService;
import service.UserService;
import util.Util;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static util.Util.*;


public class AuthController {

    static AuthService authService = AuthService.getInstance();
    static UserService userService = UserService.getInstance();

    public void main(){

        while(true){
            System.out.println("""
                    1. login
                    2. registration
                    3. exit
                    """);
            int menu = getNum("choose one");

            switch (menu){
                case 1 -> login();
                case 2 -> registration();
                case 3 -> {System.exit(0);}
                default -> System.out.println("invalid input");

            }
        }
    }


    public void login(){
        String email = getStr("enter email");
        String password = getStr("enter password");

        UserRole role = authService.checkUser(email, password);

        if(role == null){
            System.out.println("role is null");
        } else if(role.equals(UserRole.ADMIN)){
            AdminController.main();
        } else if (role.equals(UserRole.USER)) {
            UserController.menu();
        }else{
            System.out.println("email or password wrong.");
        }


    }

    public static void registration(){
        Random random = new Random();
        int num = random.nextInt(1000, 10000);


        String name = Util.getStr("enter name");
        String email = Util.getStr("enter email");
        String password = Util.getStr("enter password");
        getSMSCode(new LoginDTO(name, email), num);

        int code = Util.getNum("enter code");

        boolean res  = authService.checkUserAndCode(code, email, num);
          if (res) {
              userService.saveUser(new UserDTO(getUserId(), name, email, password, Status.DEFAULT, Status2.ACTIVE, UserRole.USER ));
              System.out.println("user added");

          }
              else System.out.println("User already exist");

    }


    public static void getSMSCode(LoginDTO dto, int num ){

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.required", "true");

        String userName = "e6cfcee1a42f71";
        String password = "ffa247ae67d095";



        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setSubject("Verification Code");
            message.setContent("<h1 style=\"color:red;\">Your code: " + num + "</h1>", "text/html");
            message.setFrom(new InternetAddress("test@example.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(dto.email()));

            CompletableFuture.runAsync(() -> {
                try {
                    Transport.send(message);
                    System.out.println("\n[System] Email sent to: " + dto.email());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Message sending initiated...");




    }


}

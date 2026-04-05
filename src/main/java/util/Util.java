package util;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Util {



    static Scanner scNum =  new Scanner(System.in);
    static Scanner scStr =  new Scanner(System.in);

    @Getter
    private static String UserId =  UUID.randomUUID().toString();

    public static String getStr(String text) {
        System.out.print(text + ": ");
        return scStr.nextLine();
    }

    public static int getNum(String text) {
        while (true) {
            try {
                System.out.print(text + ": ");
                return scNum.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Xato! Faqat raqam kiriting.");
                scNum.next();
            }
        }
    }


}

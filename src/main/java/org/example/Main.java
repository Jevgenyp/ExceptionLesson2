package org.example;

import java.util.Scanner;

//Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения,
// вместо этого, необходимо повторно запросить у пользователя ввод данных.
public class Main {

    public static void main(String[] args) {
        System.out.println("Введенное число: " + getInputFloat());
    }

    public static float getInputFloat() {
        Scanner scanner = new Scanner(System.in);
        float userFloat = 0;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Пожалуйста, введите дробное число: ");
            if (scanner.hasNextFloat()) {
                userFloat = scanner.nextFloat();
                validInput = true;
            } else {
                System.out.println("Это не дробное число! Попробуйте еще раз.");
                scanner.next(); // Очистка буфера
            }
        }

        return userFloat;
    }
}
  
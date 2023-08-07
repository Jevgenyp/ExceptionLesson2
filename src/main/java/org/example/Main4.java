
package org.example;
import java.util.Scanner;
// Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
// Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку:");

        String input = scanner.nextLine();

        try {
            if (input.trim().isEmpty()) { // используем trim() для удаления пробельных символов в начале и конце строки
                throw new EmptyStringException("Пустые строки вводить нельзя!");
            }

            System.out.println("Вы ввели: " + input);
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }
    }
}

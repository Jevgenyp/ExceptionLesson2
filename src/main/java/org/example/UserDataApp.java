package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;
import java.util.*;

public class UserDataApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        showInputExample();
        System.out.println("Введите данные: Фамилия Имя Отчество, дата рождения, номер телефона, пол");
        String input = scanner.nextLine();

        try {
            User user = parseInput(input);
            writeToFile(user);
            System.out.println("Данные успешно записаны.");
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели меньше или больше данных, чем требуется.");
            showInputExample();
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
            showInputExample();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showInputExample() {
        System.out.println("Пример корректного ввода:");
        System.out.println("Иванов Иван Иванович 12.05.1985 1234567890 m");
    }

    private static User parseInput(String input) throws DataFormatException {
        String[] parts = input.split(" ");

        if (parts.length != 6) {
            throw new InputMismatchException();
        }

        Pattern datePattern = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");
        Pattern genderPattern = Pattern.compile("^[fm]$");

        if (!datePattern.matcher(parts[3]).matches()) {
            throw new DataFormatException("Неверный формат даты рождения.");
        }

        if (!genderPattern.matcher(parts[5]).matches()) {
            throw new DataFormatException("Неверный формат пола.");
        }

        String phoneNumber;
        try {
            phoneNumber = Long.toString(Long.parseLong(parts[4]));
        } catch (NumberFormatException e) {
            throw new DataFormatException("Неверный формат номера телефона.");
        }

        return new User(parts[0], parts[1], parts[2], parts[3], phoneNumber, parts[5].charAt(0));
    }

    private static void writeToFile(User user) throws IOException {
        Path path = Paths.get(user.getLastName() + ".txt");
        String content = String.format("%s %s %s, %s, %s, %c\n",
                user.getLastName(), user.getFirstName(), user.getMiddleName(),
                user.getBirthDate(), user.getPhoneNumber(), user.getGender());

        Files.write(path, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    static class User {
        private String lastName;
        private String firstName;
        private String middleName;
        private String birthDate;
        private String phoneNumber;
        private char gender;

        public User(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, char gender) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public char getGender() {
            return gender;
        }
    }

    static class DataFormatException extends Exception {
        public DataFormatException(String message) {
            super(message);
        }
    }
}

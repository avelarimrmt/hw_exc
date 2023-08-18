import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

    try {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные через пробел: (Фамилия Имя Отчество датарождения номертелефона пол):");
        String input = scanner.nextLine();

        String[] data = input.split(" ");
        if (data.length > 6) {

            throw new IllegalArgumentException("Вы ввели большее количество данных, чем требуется");

        }
        if (data.length < 6) {

            throw new IllegalArgumentException("Вы ввели меньшее количество данных, чем требуется");

        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String birth = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        parseData(lastName, firstName, middleName, birth, phoneNumber, gender);
        saveRecordInFile(lastName, firstName, middleName, birth, phoneNumber, gender);
        System.out.println("Данные записаны в файл");
    } catch (IllegalArgumentException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void parseData(String lastName, String firstName, String middleName, String birth, String phoneNumber, String gender) {
        if (!isValidDate(birth)) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }

        if (!isValidGender(gender)) {
            throw new IllegalArgumentException("Неверный формат пола");
        }
    }

    private static boolean isValidDate(String date) {
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d+");
    }

    private static boolean isValidGender(String gender) {
        return gender.equals("f") || gender.equals("m");
    }

    private static void saveRecordInFile(String lastName, String firstName, String middleName, String birth, String phoneNumber, String gender) throws IOException {
        FileWriter fileWriter = new FileWriter(lastName + ".txt", true);
        fileWriter.write(lastName + firstName + middleName + birth + " " + phoneNumber + gender + "\n");
        fileWriter.close();
    }
}
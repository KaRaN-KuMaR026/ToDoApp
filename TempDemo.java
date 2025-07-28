import java.util.Arrays;
import java.util.Scanner;

public class TempDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        System.out.println(a.isEmpty());
    }

    public static boolean validateDate(String dueDate) {
        String[] parts = dueDate.trim().split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int date = Integer.parseInt(parts[2]);
        return true;
    }
}

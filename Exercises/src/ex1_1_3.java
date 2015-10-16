import java.io.IOException;
import java.util.Scanner;

/************************************************
 *                                              *
 ************************************************/
public class ex1_1_3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        if (x == y && y == z) {
            System.out.println("equal");
        }
        else System.out.println("not equal");
    }
}

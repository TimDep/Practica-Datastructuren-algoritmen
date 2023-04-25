import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = sc.nextInt();
        for (int i = 1; i <= aantalTestgevallen; i++) {
            int aantal = sc.nextInt();
            for (int j = 1; j <= aantal + 2; j++) {
                for (int l = aantal + 2; l > j; l--) {
                    System.out.print(" ");
                }
                for (int k = 0; k < 2 * j - 1; k++) {
                    System.out.print('*');

                }
                System.out.println("");
            }
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < aantal + 1; j++) {
                    System.out.print(" ");
                }
                System.out.println('*');
            }
        }


    }
}
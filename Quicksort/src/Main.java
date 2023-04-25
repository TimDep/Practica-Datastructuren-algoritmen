import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int[] rij = new int[6];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            rij[i] = sc.nextInt();
        }
        sort(0, 5);
    }

    public static void sort(int onder, int boven) {
        int i = onder;
        int j = boven;
        int x = rij[(onder + boven) / 2];
        int temp;
        do {
            while (rij[i] < x) i++;
            while (rij[j] > x) j--;
            if (i <= j) {
                temp = rij[i];
                rij[i] = rij[j];
                rij[j] = temp;
                i++;
                j--;
                System.out.println(Arrays.toString(rij));
            }
        } while(i<=j);
        if (onder<j) sort(onder, j);
        if (i<boven) sort(i, boven);

    }
}
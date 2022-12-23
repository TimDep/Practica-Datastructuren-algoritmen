import java.util.Scanner;

class Tekst {
    public int maxLengte;
    public String[] woorden;
    public int[] lengtes;

    public Tekst(String[] woorden, int maxLengte) {
        this.woorden = woorden;
        this.maxLengte = maxLengte;
        lengtes = new int[woorden.length + 1];
        for (int i = 0; i < woorden.length; i++) {
            lengtes[i] = woorden[i].length();
        }
    }

    public int vindBesteScore() {
        int rooster[][] = new int[woorden.length + 1][woorden.length + 1];
        for (int i = 0; i < rooster.length; i++) {
            for (int j = 0; j < rooster[0].length; j++) {
                rooster[i][j] = 999999;
            }
        }
        rooster[0][0] = 0;
        int temp;
        for (int i = 0; i < woorden.length; i++) {
            temp = lengtes[i];
            for (int j = i + 1; j <= woorden.length; j++) {
                if (temp <= maxLengte) {
                    rooster[i + 1][j] = Math.min(rooster[i][j], (int) Math.pow(maxLengte - temp, 2) + rooster[i][i]);
                    if (j < woorden.length) {
                        temp += lengtes[j] + 1;
                    }
                }
            }
        }
        return rooster[woorden.length][woorden.length];
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < aantalTestgevallen; i++) {
            int maximaleLengte = Integer.parseInt(sc.nextLine());
            String[] woorden = sc.nextLine().split(" ");
            Tekst tekst = new Tekst(woorden, maximaleLengte);
            System.out.println(i + 1 + " " + tekst.vindBesteScore());

        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

class Coordinaten {
    private int x;
    private int y;

    public Coordinaten(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Main {

    public static ArrayList<Coordinaten> lijst = new ArrayList<>();

    public static ArrayList<Integer> besteCombinaties = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= aantalTestgevallen; i++) {
            lijst.clear();
            int b = sc.nextInt();
            int h = sc.nextInt();
            int tijd = sc.nextInt();
            int tussentijd = 0;
            int aantal =0;
            Character[][] huizen = new Character[h][b];
            ArrayList<String> tussenArray = new ArrayList<>();
            for (int j = 0; j < h; j++) {
                tussenArray.add(sc.next());
            }
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < b; k++) {
                    huizen[j][k] = tussenArray.get(j).charAt(k);
                }
            }
            System.out.print(i + " ");
            voedselZoeken(huizen);
            routeZoeken(tijd, tussentijd, aantal);
            System.out.println(lijst.size());
            System.out.println();
        }
    }

    private static void voedselZoeken(Character[][] huizen) {
        for (int i = 0; i < huizen.length; i++) {
            for (int j = 0; j < huizen[0].length; j++) {
                if (huizen[i][j].equals('G')) {
                    lijst.add(new Coordinaten(i, j));
                }
            }
        }
        for (int i = 0; i < huizen.length; i++) {
            for (int j = 0; j < huizen[0].length; j++) {
                if (huizen[i][j].equals('E')) {
                    lijst.add(new Coordinaten(j, i));
                }
            }
        }
    }

    private static int routeZoeken(int tijd, int tussentijd, int aantal) {
        if (besteCombinaties.isEmpty() && tussentijd == tijd){
            besteCombinaties.add(aantal);
        }
        if (tussentijd == tijd && aantal < besteCombinaties.get(0)){

        }
        for (int i = 1; i < lijst.size(); i++) {
            tussentijd += Math.abs(lijst.get(i).getX() - lijst.get(i-1).getX()) + Math.abs(lijst.get(i).getY() - lijst.get(i-1).getY());
            aantal++;
            aantal += routeZoeken(tijd, tussentijd, aantal);
        }
        return aantal;
    }
}
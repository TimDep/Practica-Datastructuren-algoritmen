import java.util.*;

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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Main {

    public static List<Integer> lijstAfstandenNaarKat = new ArrayList<>();
    public static List<Coordinaten> lijstCoordinaten = new ArrayList<>();
    public static List<Integer> besteCombinaties = new ArrayList<>();
    public static Map<String, Integer> afstandenPunten = new HashMap<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= aantalTestgevallen; i++) {
            lijstAfstandenNaarKat.clear();
            lijstCoordinaten.clear();
            besteCombinaties.clear();
            afstandenPunten.clear();
            int b = sc.nextInt();
            int h = sc.nextInt();
            int tijd = sc.nextInt();
            int tussentijd = 0;
            List<Integer> punten = new ArrayList<>();
            Coordinaten kat = new Coordinaten(0, 0);
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
            System.out.println();
            System.out.print(i + " ");
            voedselZoeken(huizen, kat);
            onnodigeAfstandenVerwijderen(tijd);
            afstandenBerekenen();
            punten.add(0);
            besteRouteZoeken(tijd, tussentijd, punten);
            System.out.print(besteCombinaties.get(0));

        }
    }

    private static void voedselZoeken(Character[][] huizen, Coordinaten kat) {
        for (int i = 0; i < huizen.length; i++) {
            for (int j = 0; j < huizen[0].length; j++) {
                if (huizen[i][j].equals('G')) {
                    kat.setX(i);
                    kat.setY(j);
                }
            }
        }
        for (int i = 0; i < huizen.length; i++) {
            for (int j = 0; j < huizen[0].length; j++) {
                if (huizen[i][j].equals('E')) {
                    lijstCoordinaten.add(new Coordinaten(i, j));
                    int afstand = Math.abs(kat.getX() - i) + Math.abs(kat.getY() - j);
                    lijstAfstandenNaarKat.add(afstand);
                }
            }
        }
    }

    private static void onnodigeAfstandenVerwijderen(int tijd) {
        for (int i = 0; i < lijstAfstandenNaarKat.size(); i++) {
            if (lijstAfstandenNaarKat.get(i) * 2 >= tijd) {
                lijstAfstandenNaarKat.remove(i);
                lijstCoordinaten.remove(i);
            }
        }

    }

    private static void afstandenBerekenen() {
        for (int i = 0; i < lijstCoordinaten.size(); i++) {
            for (int j = 0; j < lijstCoordinaten.size(); j++) {
                if (i != j) {
                    int afstand = Math.abs(lijstCoordinaten.get(i).getX() - lijstCoordinaten.get(j).getX()) + Math.abs(lijstCoordinaten.get(i).getY() - lijstCoordinaten.get(j).getY());
                    String punten = (i + 1) + "" + (j + 1);
                    afstandenPunten.put(punten, afstand);
                }
            }
        }
    }

    private static void besteRouteZoeken(int tijd, int tussentijd, List<Integer> punten) {
        if(!besteCombinaties.isEmpty() && besteCombinaties.get(0) == lijstCoordinaten.size()){
            return;
        }
        if (tussentijd != 0 && punten.size() == 2) {
            if (tussentijd + lijstAfstandenNaarKat.get(punten.get(punten.size() - 1) - 1) <= tijd) {
                besteCombinaties.add(1);
            } else {
                besteCombinaties.add(0);
            }
        }
        if (tussentijd != 0 && punten.size() > 2) {
            String vorigPunt = punten.get(punten.size() - 1).toString();
            String vorig2dePunt = punten.get(punten.size() - 2).toString();
            tussentijd += afstandenPunten.get(vorigPunt + vorig2dePunt) + 1;

            if (tussentijd + lijstAfstandenNaarKat.get(punten.get(punten.size() - 1) - 1) > tijd) {
                punten.remove(punten.size() - 1);
                return;
            }
            if (tussentijd + lijstAfstandenNaarKat.get(punten.get(punten.size() - 1) - 1) <= tijd && punten.size() > besteCombinaties.get(0)) {
                besteCombinaties.clear();
                besteCombinaties.add(punten.size() - 1);
            }
        }
        for (int i = 1; i <= lijstCoordinaten.size(); i++) {
            if (punten.size() == 1) {
                tussentijd = lijstAfstandenNaarKat.get(i - 1) + 1;
                punten.add(i);
                besteRouteZoeken(tijd, tussentijd, punten);
            }
            if (!punten.contains(i)) {
                punten.add(i);
                besteRouteZoeken(tijd, tussentijd, punten);
            }
        }
        punten.remove(punten.size() - 1);

    }
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = sc.nextInt();
        for (int i = 0; i < aantalTestgevallen; i++) {
            int budget = sc.nextInt();
            int aantalKroegen = sc.nextInt();
            List<Integer> prijslijst = new ArrayList<>();
            List<Integer> gezelligheidsScorelijst = new ArrayList<>();
            for (int j = 0; j < aantalKroegen; j++) {
                int prijs = sc.nextInt();
                prijslijst.add(prijs);
                int gezelligheidsScore = sc.nextInt();
                gezelligheidsScorelijst.add(gezelligheidsScore);
            }
            System.out.println(i + 1 + " " + hoogsteGezelligheidZoeken(prijslijst, gezelligheidsScorelijst, budget));
        }
    }

    private static int hoogsteGezelligheidZoeken(List<Integer> prijslijst, List<Integer> gezelligheidsScorelijst, int budget) {
        Integer[] pl = new Integer[prijslijst.size()];
        pl = prijslijst.toArray(pl);
        Integer[] gs = new Integer[gezelligheidsScorelijst.size()];
        gs = gezelligheidsScorelijst.toArray(gs);

        int[] hoogsteGezelligheid = new int[budget + 1];

        for (int i = 1; i < pl.length + 1; i++) {
            for (int p = budget; p>= 0; p--) {
                if (pl[i - 1] <= p) {
                    hoogsteGezelligheid[p] = Math.max(hoogsteGezelligheid[p], hoogsteGezelligheid[p - pl[i - 1]] + gs[i - 1]);
                }
            }
        }
        return hoogsteGezelligheid[budget];
    }
}
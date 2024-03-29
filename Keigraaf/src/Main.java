import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = sc.nextInt();
        for (int i = 1; i <= aantalTestgevallen; i++) {
            int aantalKnopen = sc.nextInt();
            Map<Integer, ArrayList<Integer>> bogen = new HashMap<>();
            Map<ArrayList<Integer>, Integer> knopen = new HashMap<>();
            ArrayList<Integer> beginknopen = new ArrayList<>();
            for (int j = 0; j < aantalKnopen; j++) {
                beginknopen.add(sc.nextInt());
            }
            knopen.put(beginknopen, 0);
            int aantalBogen = sc.nextInt();
            for (int j = 0; j < aantalBogen; j++) {
                int vertrek = sc.nextInt();
                int aankomst = sc.nextInt();
                bogen.computeIfAbsent(vertrek - 1, k -> new ArrayList<>()).add(aankomst - 1);
                bogen.computeIfAbsent(aankomst - 1, k -> new ArrayList<>()).add(vertrek - 1);
            }
            ArrayList<Integer> tussenArray = new ArrayList<>(beginknopen);
            oplossen(bogen, knopen, tussenArray);
        }
    }

    private static void oplossen(Map<Integer, ArrayList<Integer>> bogen, Map<ArrayList<Integer>, Integer> knopen, ArrayList<Integer> tussenArray) {
        int periode = 0;
        while (periode == 0) {
            ArrayList<Integer> resultaat = new ArrayList<>();
            ArrayList<Integer> resten = new ArrayList<>(tussenArray.size());
            for (int i = 0; i < tussenArray.size(); i++) {
                int rest = tussenArray.get(i) % bogen.get(i).size();
                resten.add(rest);
                resultaat.add(rest);
            }
            for (int i = 0; i < tussenArray.size(); i++) {
                for (int j = 0; j < bogen.get(i).size(); j++) {
                    int index = bogen.get(i).get(j);
                    resultaat.set(index, resultaat.get(index) + ((tussenArray.get(i) - resten.get(i)) / bogen.get(i).size()));
                }
            }
            periode = arraysVergelijken(knopen, resultaat);
            tussenArray.clear();
            tussenArray.addAll(resultaat);
        }
        System.out.println(periode);
    }

    private static int arraysVergelijken(Map<ArrayList<Integer>, Integer> knopen, ArrayList<Integer> resultaat) {
        if (knopen.containsKey(resultaat)) {
            return knopen.size() - knopen.get(resultaat);
        }
        knopen.put(resultaat, knopen.size());
        return 0;
    }
}
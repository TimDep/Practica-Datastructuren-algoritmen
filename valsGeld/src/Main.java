import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String alfabet = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalWeegsessie = Integer.parseInt(sc.nextLine());
        int aantalWegingen = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < aantalWeegsessie; i++) {
            Map<Character, ArrayList<Integer>> alfabetMap = creerMap();
            ArrayList<String> geldWegen = new ArrayList<>();
            for (int j = 0; j < aantalWegingen; j++) {
                String linkerschaal = sc.next();
                String rechterschaal = sc.next();
                String toestandRechterschaal = sc.next();
                lettersOverlopen(alfabetMap, linkerschaal, rechterschaal, toestandRechterschaal);
            }

        }
    }

    private static Map<Character, ArrayList<Integer>> creerMap() {
        Map<Character, ArrayList<Integer>> alfabetMap = new HashMap<>();
        for (int i = 0; i < alfabet.length(); i++) {
            alfabetMap.put(alfabet.charAt(i), new ArrayList<>());
        }
        return alfabetMap;
    }

    private static void lettersOverlopen(Map<Character, ArrayList<Integer>> alfabetMap, String linkerschaal, String rechterschaal, String toestandRechterschaal) {
        for (int i = 0; i < linkerschaal.length(); i++) {
            if (toestandRechterschaal.equals("evenwicht")) {
                if (alfabetMap.get(linkerschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(0);

                } else if (alfabetMap.get(rechterschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(0);

                } else {
                    alfabetMap.get(linkerschaal.charAt(i)).clear();
                    alfabetMap.get(linkerschaal.charAt(i)).set(0, 0);
                    alfabetMap.get(rechterschaal.charAt(i)).clear();
                    alfabetMap.get(rechterschaal.charAt(i)).set(0, 0);
                }

            } else if (toestandRechterschaal.equals("omlaag")) {
                if (alfabetMap.get(linkerschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(0);
                    alfabetMap.get(linkerschaal.charAt(i)).add(1);

                } else if (alfabetMap.get(rechterschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(-1);
                    alfabetMap.get(rechterschaal.charAt(i)).add(0);

                } else {
                    //TODO for loop verlaten en schrijven inconsistente gegevens
                }
            } else if (toestandRechterschaal.equals("omhoog")) {
                if (alfabetMap.get(linkerschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(-1);
                    alfabetMap.get(linkerschaal.charAt(i)).add(0);

                } else if (alfabetMap.get(rechterschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(0);
                    alfabetMap.get(rechterschaal.charAt(i)).add(1);

                } else {
                    //TODO for loop verlaten en schrijven inconsistente gegevens
                }
            }
        }
    }
}

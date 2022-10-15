import java.util.*;

public class Main {

    private static final String alfabet = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalWeegsessie = sc.nextInt();
        Map<Character, ArrayList<Integer>> alfabetMap = creerMap();
        for (int i = 0; i < aantalWeegsessie; i++) {
            int aantalWegingen = sc.nextInt();
            wisArrayMap(alfabetMap);
            for (int j = 0; j < aantalWegingen; j++) {
                String linkerschaal = sc.next();
                String rechterschaal = sc.next();
                String toestandRechterschaal = sc.next();
                lettersOverlopen(alfabetMap, linkerschaal, rechterschaal, toestandRechterschaal);
            }
            System.out.println(alfabetMap);
            mapOverlopen(alfabetMap);
        }
    }

    private static Map<Character, ArrayList<Integer>> creerMap() {
        Map<Character, ArrayList<Integer>> alfabetMap = new HashMap<>();
        for (int i = 0; i < alfabet.length(); i++) {
            alfabetMap.put(alfabet.charAt(i), new ArrayList<>());
        }
        return alfabetMap;
    }

    private static Map<Character, ArrayList<Integer>> wisArrayMap(Map<Character, ArrayList<Integer>> alfabetMap) {
        for (int i = 0; i < alfabet.length(); i++) {
            alfabetMap.get(alfabet.charAt(i)).clear();
        }
        return alfabetMap;
    }

    private static void lettersOverlopen(Map<Character, ArrayList<Integer>> alfabetMap, String linkerschaal, String rechterschaal, String toestandRechterschaal) {
        for (int i = 0; i < linkerschaal.length(); i++) {
            if (toestandRechterschaal.equals("evenwicht")) {
                if (alfabetMap.get(linkerschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(0);
                } else if (alfabetMap.get(linkerschaal.charAt(i)).size() == 2) {
                    alfabetMap.get(linkerschaal.charAt(i)).clear();
                    alfabetMap.get(linkerschaal.charAt(i)).add(0);

                }
                if (alfabetMap.get(rechterschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(0);

                } else if (alfabetMap.get(rechterschaal.charAt(i)).size() == 2) {
                    alfabetMap.get(rechterschaal.charAt(i)).clear();
                    alfabetMap.get(rechterschaal.charAt(i)).add(0);
                }

            } else if (toestandRechterschaal.equals("omlaag")) {
                if (alfabetMap.get(linkerschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(0);
                    alfabetMap.get(linkerschaal.charAt(i)).add(1);

                } else if (alfabetMap.get(linkerschaal.charAt(i)).contains(0) && alfabetMap.get(linkerschaal.charAt(i)).size() == 1) {
                    alfabetMap.get(linkerschaal.charAt(i)).clear();
                    alfabetMap.get(linkerschaal.charAt(i)).add(1);

                } else if (alfabetMap.get(linkerschaal.charAt(i)).size() == 2 && !alfabetMap.get(linkerschaal.charAt(i)).contains(1)) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(1);

                }
                if (alfabetMap.get(rechterschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(0);
                    alfabetMap.get(rechterschaal.charAt(i)).add(-1);

                } else if (alfabetMap.get(rechterschaal.charAt(i)).contains(0) && alfabetMap.get(rechterschaal.charAt(i)).size() == 1) {
                    alfabetMap.get(rechterschaal.charAt(i)).clear();
                    alfabetMap.get(rechterschaal.charAt(i)).add(-1);
                } else if (alfabetMap.get(rechterschaal.charAt(i)).size() == 2 && !alfabetMap.get(rechterschaal.charAt(i)).contains(-1)) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(-1);
                }
            } else if (toestandRechterschaal.equals("omhoog")) {
                if (alfabetMap.get(linkerschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(0);
                    alfabetMap.get(linkerschaal.charAt(i)).add(-1);

                } else if (alfabetMap.get(linkerschaal.charAt(i)).contains(0) && alfabetMap.get(linkerschaal.charAt(i)).size() == 1) {
                    alfabetMap.get(linkerschaal.charAt(i)).clear();
                    alfabetMap.get(linkerschaal.charAt(i)).add(-1);

                } else if (alfabetMap.get(linkerschaal.charAt(i)).size() == 2 && !alfabetMap.get(linkerschaal.charAt(i)).contains(-1)) {
                    alfabetMap.get(linkerschaal.charAt(i)).add(-1);

                }
                if (alfabetMap.get(rechterschaal.charAt(i)).isEmpty()) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(0);
                    alfabetMap.get(rechterschaal.charAt(i)).add(1);

                } else if (alfabetMap.get(rechterschaal.charAt(i)).contains(0) && alfabetMap.get(rechterschaal.charAt(i)).size() == 1) {
                    alfabetMap.get(rechterschaal.charAt(i)).clear();
                    alfabetMap.get(rechterschaal.charAt(i)).add(1);

                } else if (alfabetMap.get(rechterschaal.charAt(i)).size() == 2 && !alfabetMap.get(rechterschaal.charAt(i)).contains(1)) {
                    alfabetMap.get(rechterschaal.charAt(i)).add(1);
                }
            }
        }
    }

    private static void mapOverlopen(Map<Character, ArrayList<Integer>> alfabetMap) {
        int teller = 0;
        char sleutel = '\0';
        int zwaarderOfLichter;
        int inconsistent = 0;
        int meerderInconsistent = 0;
        for (Map.Entry<Character, ArrayList<Integer>> entry : alfabetMap.entrySet()) {
            if (entry.getValue().size() == 2) {
                teller++;
                sleutel = entry.getKey();
            } else if (entry.getValue().size() == 1) {
                if (!entry.getValue().contains(0)) {
                    inconsistent++;
                }
            }else if (entry.getValue().size() == 3) {
                meerderInconsistent++;

            }
        }
        if (teller > 1 && meerderInconsistent == 0) {
            System.out.println("Te weinig gegevens.");
        } else if (teller == 1) {
            zwaarderOfLichter = alfabetMap.get(sleutel).get(1);
            if (zwaarderOfLichter == -1) {
                System.out.println("Het valse geldstuk " + sleutel + " is zwaarder.");
            } else {
                System.out.println("Het valse geldstuk " + sleutel + " is lichter.");
            }
        } else if (inconsistent >= 2 || meerderInconsistent>0) {
            System.out.println("Inconsistente gegevens.");
        } else {
            System.out.println("Te weinig gegevens.");
        }

    }
}

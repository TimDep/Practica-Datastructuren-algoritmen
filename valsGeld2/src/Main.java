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
                alfabetMap.get(linkerschaal.charAt(i)).add(null);
                alfabetMap.get(rechterschaal.charAt(i)).add(null);

            } else if (toestandRechterschaal.equals("omhoog")) {
                alfabetMap.get(linkerschaal.charAt(i)).add(0);
                alfabetMap.get(rechterschaal.charAt(i)).add(1);

            } else if (toestandRechterschaal.equals("omlaag")) {
                alfabetMap.get(linkerschaal.charAt(i)).add(1);
                alfabetMap.get(rechterschaal.charAt(i)).add(0);
            }
        }
    }

    private static void mapOverlopen(Map<Character, ArrayList<Integer>> alfabetMap) {
        int meestVoorkomend =0;
        int inconsistent = 0;
        int teWeinig=0;
        int evenwicht =0;
        int teller =0;
        char sleutel = '\0';
        for (Map.Entry<Character, ArrayList<Integer>> entry : alfabetMap.entrySet()) {
            if(entry.getValue().size()>0){
                teller++;
            }
            if(!entry.getValue().contains(null)){
                if(entry.getValue().contains(0) && entry.getValue().contains(1)){
                    inconsistent++;
                }
                else if(entry.getValue().contains(0)){
                    if (meestVoorkomend<entry.getValue().size()){
                        sleutel = entry.getKey();
                        meestVoorkomend = entry.getValue().size();
                    }
                    else if(meestVoorkomend==entry.getValue().size()){
                        teWeinig++;
                    }

                }
                else if(entry.getValue().contains(1)){
                    if (meestVoorkomend<entry.getValue().size()){
                        sleutel = entry.getKey();
                        meestVoorkomend = entry.getValue().size();
                    }
                    else if(meestVoorkomend==entry.getValue().size()){
                        teWeinig++;
                    }
                }
            }
            else if(entry.getValue().contains(null) && entry.getValue().contains(1) ||entry.getValue().contains(null) && entry.getValue().contains(0) ) {
                inconsistent++;
            }
            else if(entry.getValue().contains(null)){
                evenwicht++;
            }
        }
        if (teWeinig>2 && teWeinig== teller-1){
            inconsistent++;
            sleutel = '\0';
        }
        if(teWeinig>0 && inconsistent==0){
            System.out.println("Te weinig gegevens.");
        }
        else if (!(sleutel =='\0') && teWeinig<=inconsistent){
                if (alfabetMap.get(sleutel).get(0) == 0) {
                    System.out.println("Het valse geldstuk " + sleutel + " is zwaarder.");
                } else {
                    System.out.println("Het valse geldstuk " + sleutel + " is lichter.");
                }

        }else if (inconsistent>0) {
            System.out.println("Inconsistente gegevens.");
        }
        else if (evenwicht==teller){
            System.out.println("Te weinig gegevens.");
        }


    }




}
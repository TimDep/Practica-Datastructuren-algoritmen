import java.util.*;

import static java.util.Map.entry;

public class Main {

    private static Character standaardRichting = 'O';
    private static int verdiep=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        if (aantalTestgevallen < 1) {
            System.out.println("Er kunnen niet minder dan 1 testgeval zijn.");
        } else if (aantalTestgevallen > 1000) {
            System.out.println("Dit ligt buiten de limieten van dit programma.");
            System.exit(0);
        }
        for (int i = 1; i <= aantalTestgevallen; i++) {
            int aantalSegmenten = Integer.parseInt(sc.next());
            if (aantalSegmenten < 6 ){
                System.out.println("Dit zijn te weinig segmenten om een achtbaan te maken.");
            } else if (aantalSegmenten >500) {
                System.out.println("Dit zijn te veel segmenten om een achtbaan te maken.");
            } else {
                String segment = sc.nextLine();
                Map<Integer, ArrayList<String>> uitkomst = zoekenNaarCharacter(segment);

                System.out.println(uitkomst.get(verdiep));
            }
        }


    }

    private static Map<String, String> krijgDeOmhoogMap() {
        return Map.ofEntries(
                entry("O", "/"),
                entry("Z", "#"),
                entry("W", "\\"),
                entry("N", "#")
        );
    }

    private static Map<String, String> krijgDeOmlaagMap() {
        return Map.ofEntries(
                entry("O", "\\"),
                entry("Z", "#"),
                entry("W", "/"),
                entry("N", "#")
        );
    }

    private static Map<Integer, ArrayList<String>> initialiseerAchtbaan() {
        Map<Integer, ArrayList<String>> achtbaan = new HashMap<>();
        achtbaan.put(verdiep, new ArrayList<>());
        return achtbaan;
    }

    private static Integer voegTekenToeAanHetEindeVanDeAchtbaan(
            Map<Integer, ArrayList<String>> achtbaan,
            Integer verdiep,
            String teken,
            Integer bepaaldePlaats
    ) {
        bepaaldePlaats++;
        achtbaan.get(verdiep).add(teken);
        return bepaaldePlaats;
    }

    private static Integer voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(
            Map<Integer, ArrayList<String>> achtbaan,
            Integer verdiep,
            String teken,
            Integer bepaaldePlaats
    ) {
        bepaaldePlaats--;
        achtbaan.get(verdiep).set(bepaaldePlaats, teken);
        return bepaaldePlaats;
    }

    private static Map<Integer, ArrayList<String>> zoekenNaarCharacter(String segment){
        Map<String, String> omhoog = krijgDeOmhoogMap();
        Map<String, String> omlaag = krijgDeOmlaagMap();
        Map<Integer, ArrayList<String>> achtbaan = initialiseerAchtbaan();
        ArrayList<String> route = new ArrayList<>();

        Integer bepaaldePlaats = 0;

        int westgaan = 1;


        for(int i =0; i <segment.length();i++) {
            if (segment.charAt(i) == 'L') {
                if (standaardRichting == 'O') {
                    bepaaldePlaats = voegTekenToeAanHetEindeVanDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats);
                    System.out.println("Bepaalde plaatst =>" + bepaaldePlaats);
                    standaardRichting = 'N';
                } else if (standaardRichting == 'W') {
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats);
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'N') {
                    standaardRichting = 'W';

                } else if (standaardRichting == 'Z') {
                    int index = achtbaan.get(verdiep).size() - 1;
                    route.set(index, "_");
                    achtbaan.put(verdiep, route);
                    standaardRichting = 'O';

                }
            } else if (segment.charAt(i) == 'R') {
                if (standaardRichting == 'O') {
                    bepaaldePlaats = voegTekenToeAanHetEindeVanDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats);
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'W') {
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats);
                    standaardRichting = 'N';

                } else if (standaardRichting == 'N') {
                    standaardRichting = 'O';

                } else if (standaardRichting == 'Z') {
                    int index = achtbaan.get(verdiep).size() - 1;
                    route.set(index, "_");
                    achtbaan.put(verdiep, route);
                    standaardRichting = 'W';

                }

            } else if (segment.charAt(i) == 'S') {
                bepaaldePlaats = voegTekenToeAanHetEindeVanDeAchtbaan(achtbaan, verdiep, "=", bepaaldePlaats);


            } else if (segment.charAt(i) == 'V') {
                if (standaardRichting == 'W') {
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats);

                } else if (standaardRichting == 'O') {
                    bepaaldePlaats = voegTekenToeAanHetEindeVanDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats);
                } else {

                }


            } else if (segment.charAt(i) == 'U') {


            } else if (segment.charAt(i) == 'D') {

            }
        }
        return achtbaan;
    }



    //private static void getArray() {

        //achtbaan.put(0, route);

        //achtbaan.get(0).add("#");

        //ArrayList<String> copyRoute = achtbaan.get(0);
        //achtbaan.get(0).clear();
        //achtbaan.get(0).add("");
        //achtbaan.get(0).addAll(copyRoute);

        //boolean naarBeneden = true;
        //if (naarBeneden) {
          //  Integer achtbaanLengte = achtbaan.get(0).size();
        //}

        //boolean overschrijven = true;
        //if (overschrijven) {
          //  achtbaan.get(0).set(3, "#");
        //}




    }
    //int index = windrichtingen.indexOf((standaardRichting));
    //            if(index == windrichtingen.length()-1){
    //                index = 0;
    //                standaardRichting = windrichtingen.charAt(index);
    //            }
    //            else{
    //                standaardRichting = windrichtingen.charAt(index+1);
    //            }


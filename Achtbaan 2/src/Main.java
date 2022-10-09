import java.util.*;

import static java.util.Map.entry;

public class Main {


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
            if (aantalSegmenten < 6) {
                System.out.println("Dit zijn te weinig segmenten om een achtbaan te maken.");
            } else if (aantalSegmenten > 500) {
                System.out.println("Dit zijn te veel segmenten om een achtbaan te maken.");
            } else {
                String segment = sc.nextLine();
                int verdiep = 0;
                Map<Integer, ArrayList<String>> uitkomst = zoekenNaarCharacter(segment, verdiep);

                System.out.println(uitkomst);
                verdiep = 0;

            }
        }


    }

    private static Map<Integer, ArrayList<String>> initialiseerAchtbaan(int verdiep) {
        Map<Integer, ArrayList<String>> achtbaan = new HashMap<>();
        achtbaan.put(verdiep, new ArrayList<>());
        return achtbaan;
    }

    private static Map<Integer, ArrayList<String>> zoekenNaarCharacter(String segment, int verdiep) {
        Map<Integer, ArrayList<String>> achtbaan = initialiseerAchtbaan(verdiep);
        ArrayList<String> route = new ArrayList<>();

        int bepaaldePlaats = 0;
        int achterOfVoor = 0;
        char standaardRichting = 'O';


        for (int i = 0; i < segment.length(); i++) {
            initNieuwVerdiep(achtbaan, verdiep, bepaaldePlaats);
            if (segment.charAt(i) == 'L') {
                if (standaardRichting == 'O') {
                    achtbaan.get(verdiep).add("_");
                    achterOfVoor = 1;
                    standaardRichting = 'N';
                } else if (standaardRichting == 'W') {
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats);
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'N') {
                    standaardRichting = 'W';

                } else if (standaardRichting == 'Z') {
                    achtbaan.get(verdiep).set(bepaaldePlaats, "_");
                    bepaaldePlaats++;
                    standaardRichting = 'O';

                }
            } else if (segment.charAt(i) == 'R') {
                if (standaardRichting == 'O') {
                    achtbaan.get(verdiep).add("_");
                    achterOfVoor = 2;
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'W') {
                    bepaaldePlaats--;
                    achtbaan.get(verdiep).set(bepaaldePlaats, "_");
                    standaardRichting = 'N';

                } else if (standaardRichting == 'N') {
                    achtbaan.get(verdiep).set(bepaaldePlaats, "_");
                    bepaaldePlaats++;
                    standaardRichting = 'O';

                } else if (standaardRichting == 'Z') {
                    standaardRichting = 'W';

                }

            } else if (segment.charAt(i) == 'S') {
                achtbaan.get(verdiep).add("=");
                bepaaldePlaats++;


            } else if (segment.charAt(i) == 'V') {
                if (standaardRichting == 'W') {

                    if (achterOfVoor == 1) {

                    } else if (achterOfVoor == 2) {
                        bepaaldePlaats--;
                        achtbaan.get(verdiep).set(bepaaldePlaats, "_");
                    }


                } else if (standaardRichting == 'O') {
                    achtbaan.get(verdiep).add("_");
                    bepaaldePlaats++;
                }
            } else if (segment.charAt(i) == 'U') {
                if (standaardRichting == 'O') {
                    achtbaan.get(verdiep).add("/");
                    bepaaldePlaats++;
                    verdiep++;
                } else if (standaardRichting == 'W') {
                    bepaaldePlaats--;
                    achtbaan.get(verdiep).set(bepaaldePlaats, "\\");
                    verdiep++;

                } else if (standaardRichting == 'Z') {
                    achtbaan.get(verdiep).set(bepaaldePlaats, "#");
                    verdiep++;

                } else {
                    verdiep++;


                }


            } else if (segment.charAt(i) == 'D') {
                if (standaardRichting == 'O') {
                    achtbaan.get(verdiep).add("\\");
                    bepaaldePlaats++;
                    verdiep--;
                } else if (standaardRichting == 'W') {
                    bepaaldePlaats--;
                    achtbaan.get(verdiep).set(bepaaldePlaats, "/");
                    verdiep--;
                } else if (standaardRichting == 'Z') {
                    achtbaan.get(verdiep).set(bepaaldePlaats, "#");
                    verdiep--;

                } else {
                    verdiep--;

                }

            }
        }
        achtbaanCompleetMaken(achtbaan);

        return achtbaan;
    }

    private static ArrayList<String> vulAchtbaanMetPunten(int bepaaldePlaats) {
        ArrayList<String> vullen = new ArrayList<>();
        for (int i = 0; i < bepaaldePlaats; i++) {
            vullen.add(".");
        }
        return vullen;
    }

    private static void initNieuwVerdiep(Map<Integer, ArrayList<String>> achtbaan, int verdiep, int bepaaldePlaats) {
        if (!achtbaan.containsKey(verdiep)) {
            achtbaan.put(verdiep, vulAchtbaanMetPunten(bepaaldePlaats));

        }
    }

    private static void achtbaanCompleetMaken(Map<Integer, ArrayList<String>> achtbaan) {
        int langsteLijn = zoekLangsteAchtbaan(achtbaan);
        for (ArrayList<String> achtbaanLijn : achtbaan.values()) {
            if (achtbaanLijn.size() < langsteLijn) {
                int verschil = langsteLijn - achtbaanLijn.size();
                achtbaanLijn.addAll(vulAchtbaanMetPunten(verschil));
            }
        }

    }

    private static int zoekLangsteAchtbaan(Map<Integer, ArrayList<String>> achtbaan) {
        int langsteLijn = 0;
        for (ArrayList<String> achtbaanLijn : achtbaan.values()) {
            int achtbaanLengte = achtbaanLijn.size();
            if (achtbaanLengte > langsteLijn) {
                langsteLijn = achtbaanLengte;
            }
        }
        return langsteLijn;
    }

    private static Integer voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(
            Map<Integer, ArrayList<String>> achtbaan,
            Integer verdiep,
            String teken,
            Integer bepaaldePlaats
    ) {
        bepaaldePlaats--;
        if (bepaaldePlaats == -1) {
            ArrayList<String> copyRoute = achtbaan.get(verdiep);
            achtbaan.get(verdiep).clear();
            achtbaan.get(verdiep).add(teken);
            achtbaan.get(verdiep).addAll(copyRoute);
            bepaaldePlaats=0;
        } else {
            achtbaan.get(verdiep).set(bepaaldePlaats, teken);
        }

        return bepaaldePlaats;
    }


}
import java.util.*;

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

                printenUitkomst(uitkomst, i);
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
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    achtbaan.get(verdiep).add("_");
                    achterOfVoor = 1;
                    standaardRichting = 'N';
                } else if (standaardRichting == 'W') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'N') {
                    standaardRichting = 'W';

                } else if (standaardRichting == 'Z') {
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                    standaardRichting = 'O';

                }
            } else if (segment.charAt(i) == 'R') {
                if (standaardRichting == 'O') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    achtbaan.get(verdiep).add("_");
                    achterOfVoor = 2;
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'W') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                    standaardRichting = 'N';

                } else if (standaardRichting == 'N') {
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                    standaardRichting = 'O';

                } else if (standaardRichting == 'Z') {
                    standaardRichting = 'W';

                }

            } else if (segment.charAt(i) == 'S') {
                achtbaan.get(verdiep).add("=");
                bepaaldePlaats++;


            } else if (segment.charAt(i) == 'V') {
                if (standaardRichting == 'W') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    if (achterOfVoor == 1) {
                        if (achtbaan.get(verdiep).get(bepaaldePlaats - 1) == ".") {
                            bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                        } else {
                            bepaaldePlaats--;
                        }


                    } else if (achterOfVoor == 2) {
                        bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);


                    }


                } else if (standaardRichting == 'O') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                }
            } else if (segment.charAt(i) == 'U') {
                if (standaardRichting == 'O') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "/", bepaaldePlaats, standaardRichting);
                    verdiep++;
                } else if (standaardRichting == 'W') {
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "\\", bepaaldePlaats, standaardRichting);
                    verdiep++;

                } else if (standaardRichting == 'Z') {
                    achtbaan.get(verdiep).set(bepaaldePlaats, "#");
                    verdiep++;

                } else {
                    verdiep++;

                }


            } else if (segment.charAt(i) == 'D') {
                if (standaardRichting == 'O') {
                    verdiep--;
                    initNieuwVerdiep(achtbaan, verdiep, bepaaldePlaats);
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "\\", bepaaldePlaats, standaardRichting);

                } else if (standaardRichting == 'W') {
                    verdiep--;
                    initNieuwVerdiep(achtbaan, verdiep, bepaaldePlaats);
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "/", bepaaldePlaats, standaardRichting);


                } else if (standaardRichting == 'Z') {
                    verdiep--;
                    initNieuwVerdiep(achtbaan, verdiep, bepaaldePlaats);

                    achtbaan.get(verdiep).set(bepaaldePlaats, "#");

                } else {
                    verdiep--;

                    achtbaan.get(verdiep).set(bepaaldePlaats, "#");
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
            Integer bepaaldePlaats,
            char windrichting
    ) {
        System.out.println(bepaaldePlaats);
        if (windrichting == 'W') {
            bepaaldePlaats--;
        }
        if (bepaaldePlaats >= achtbaan.get(verdiep).size()) {
            achtbaan.get(verdiep).add(teken);
        } else {
            if (bepaaldePlaats == -1) {
                ArrayList<String> copyRoute = new ArrayList<>(achtbaan.get(verdiep));
                achtbaan.get(verdiep).clear();
                achtbaan.get(verdiep).add(teken);
                achtbaan.get(verdiep).addAll(copyRoute);
                Iterator<Map.Entry<Integer, ArrayList<String>>> itr = achtbaan.entrySet().iterator();
                while (itr.hasNext()) {
                    Map.Entry<Integer, ArrayList<String>> route = itr.next();
                    if (route.getKey() != verdiep) {
                        ArrayList<String> addPuntje = new ArrayList<>(route.getValue());
                        route.getValue().clear();
                        route.getValue().add(".");
                        route.getValue().addAll(addPuntje);
                    }
                }
                bepaaldePlaats = 0;


            } else {
                achtbaan.get(verdiep).set(bepaaldePlaats, teken);
            }
        }

        if (windrichting == 'O') {
            bepaaldePlaats++;
        }

        return bepaaldePlaats;
    }

    private static void isDeLengteVanDeArrayCorrect(Map<Integer, ArrayList<String>> achtbaan, int verdiep, int bepaaldePlaats) {
        if (achtbaan.get(verdiep).size() != bepaaldePlaats) {
            int verschil = bepaaldePlaats - achtbaan.get(verdiep).size();
            achtbaan.get(verdiep).addAll(vulAchtbaanMetPunten(verschil));

        }
    }


    private static void printenUitkomst(Map<Integer, ArrayList<String>> uitkomst, int test) {
        Map<Integer, ArrayList<String>> gesorteerdeMap = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
        gesorteerdeMap.putAll(uitkomst);
        for (Map.Entry<Integer, ArrayList<String>> entry : gesorteerdeMap.entrySet()) {
            String routeAanEen = String.join("", entry.getValue());
            System.out.println(test + " " + routeAanEen);
        }
    }


}
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
        int bepaaldePlaats = 0;
        boolean achtbaanDeelIsNietZichtbaar = false;
        char standaardRichting = 'O';

        for (int i = 0; i < segment.length(); i++) {
            initNieuwVerdiep(achtbaan, verdiep, bepaaldePlaats);
            char routeSegment = segment.charAt(i);
            if (routeSegment == 'L') {

                if (standaardRichting == 'O') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting, true);
                    achtbaanDeelIsNietZichtbaar = true;
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

            } else if (routeSegment == 'R') {

                if (standaardRichting == 'O') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting, true);
                    achtbaanDeelIsNietZichtbaar = false;
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

            } else if (routeSegment == 'S') {
                achtbaan.get(verdiep).add("=");
                bepaaldePlaats++;
            } else if (routeSegment == 'V') {

                if (standaardRichting == 'W') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    if (bepaaldePlaats - 1 < 0 || !achtbaanDeelIsNietZichtbaar) {
                        bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                    } else if (achtbaan.get(verdiep).get(bepaaldePlaats - 1).equals(".") && achtbaanDeelIsNietZichtbaar) {
                        bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                    } else if (achtbaanDeelIsNietZichtbaar) {
                        bepaaldePlaats--;
                    }
                } else if (standaardRichting == 'O') {
                    isDeLengteVanDeArrayCorrect(achtbaan, verdiep, bepaaldePlaats);
                    bepaaldePlaats = voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, "_", bepaaldePlaats, standaardRichting);
                }
            } else if (routeSegment == 'U') {

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

            } else if (routeSegment == 'D') {

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
        return voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(achtbaan, verdiep, teken, bepaaldePlaats, windrichting, false);
    }

    private static Integer voegTekenToeOpEenBepaaldePlaatsInDeAchtbaan(
            Map<Integer, ArrayList<String>> achtbaan,
            Integer verdiep,
            String teken,
            Integer bepaaldePlaats,
            char windrichting,
            boolean isDraaiBeweging
    ) {
        if (windrichting == 'W') {
            bepaaldePlaats--;
        }
        if (bepaaldePlaats >= achtbaan.get(verdiep).size()) {
            achtbaan.get(verdiep).add(teken);
        } else if (bepaaldePlaats == -1) {
            ArrayList<String> copyRoute = new ArrayList<>(achtbaan.get(verdiep));
            achtbaan.get(verdiep).clear();
            achtbaan.get(verdiep).add(teken);
            achtbaan.get(verdiep).addAll(copyRoute);
            for (Map.Entry<Integer, ArrayList<String>> route : achtbaan.entrySet()) {
                if (route.getKey() != verdiep) {
                    ArrayList<String> voegPuntjeToe = new ArrayList<>(route.getValue());
                    route.getValue().clear();
                    route.getValue().add(".");
                    route.getValue().addAll(voegPuntjeToe);
                }
            }
            bepaaldePlaats = 0;
        } else {
            achtbaan.get(verdiep).set(bepaaldePlaats, teken);
        }
        if ((windrichting == 'O' && !isDraaiBeweging) || windrichting == 'Z') {
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
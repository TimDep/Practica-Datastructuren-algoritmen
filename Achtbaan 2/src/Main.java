import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        if (aantalTestgevallen < 1) {
            System.out.println("Er kunnen niet minder dan 1 testgeval zijn.");
            System.exit(0);
        } else if (aantalTestgevallen > 1000) {
            System.out.println("Dit ligt buiten de limieten van dit programma.");
            System.exit(0);
        }
        for (int i = 1; i <= aantalTestgevallen; i++) {
            int aantalSegmenten = Integer.parseInt(sc.next());

            if (aantalSegmenten < 6) {
                System.out.println("Dit zijn te weinig segmenten om een achtbaan te maken.");
                System.exit(0);
            } else if (aantalSegmenten > 500) {
                System.out.println("Dit zijn te veel segmenten om een achtbaan te maken.");
                System.exit(0);
            } else {
                String segment = sc.nextLine();
                if (segment.length() != aantalSegmenten) {
                    System.out.println("Je hebt teveel segmenten ingetypt dan je vooraf declareerde.");
                    System.exit(0);
                } else {
                    int verdiep = 0;
                    Map<Integer, ArrayList<String>> uitkomst = zoekenNaarCharacters(segment, verdiep);
                    printenUitkomst(uitkomst, i);
                }

            }
        }
    }

    private static Map<Integer, ArrayList<String>> initialiseerAchtbaan(int verdiep) { //als er een nieuw verdiep is moet er een nieuwe arraylist geinitialiseerd worden
        Map<Integer, ArrayList<String>> achtbaan = new HashMap<>();
        achtbaan.put(verdiep, new ArrayList<>());
        return achtbaan;
    }

    private static Map<Integer, ArrayList<String>> zoekenNaarCharacters(String segment, int verdiep) {
        Map<Integer, ArrayList<String>> achtbaan = initialiseerAchtbaan(verdiep);
        int bepaaldePlaats = 0;
        boolean achtbaanDeelIsNietZichtbaar = false;
        char standaardRichting = 'O';

        for (int i = 0; i < segment.length(); i++) {
            initNieuwVerdiep(achtbaan, verdiep, bepaaldePlaats); //per letter kijken of we op een nieuw verdiep zitten
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

    private static ArrayList<String> vulAchtbaanMetPunten(int bepaaldePlaats) { //hierbij worden er puntjes toegevoegd aan de array tot een bepaalde plaats
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

    private static void achtbaanCompleetMaken(Map<Integer, ArrayList<String>> achtbaan) { //hierbij zullen we de het verschil berkenen per arraylist om te weten hoeveel we moeten toevoegen
        int langsteLijn = zoekLangsteAchtbaan(achtbaan);
        for (ArrayList<String> achtbaanLijn : achtbaan.values()) {
            if (achtbaanLijn.size() < langsteLijn) {
                int verschil = langsteLijn - achtbaanLijn.size();
                achtbaanLijn.addAll(vulAchtbaanMetPunten(verschil));
            }
        }
    }

    private static int zoekLangsteAchtbaan(Map<Integer, ArrayList<String>> achtbaan) { //hierbij zoeken we de langste arraylist per verdiep
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
            boolean bepaaldePlaatsNietOptellen
    ) {
        if (windrichting == 'W') { //indien we west gaan zullen we eerst de bepaalde plaats aftrekken zodanig dat we op de juiste plaats ervoor kunnen invullen
            bepaaldePlaats--;
        }
        if (bepaaldePlaats >= achtbaan.get(verdiep).size()) { //als de plaats waar we moeten toevoegen gelijk is an de lengte van de array moeten we gewoon het teken toevoegen
            achtbaan.get(verdiep).add(teken);
        } else if (bepaaldePlaats == -1) { // als we op een negatieve index moeten plaatsen betekent dat we een rij voor die arraylist moeten invoeren
            ArrayList<String> copyRoute = new ArrayList<>(achtbaan.get(verdiep));
            achtbaan.get(verdiep).clear();
            achtbaan.get(verdiep).add(teken);
            achtbaan.get(verdiep).addAll(copyRoute);
            for (Map.Entry<Integer, ArrayList<String>> route : achtbaan.entrySet()) { //dit zorgt ervoor dat we op de andere verdiepen dan deze van daarnet ook een punt gaan toevoegen zodanig dat alles tegenover elkaar op de juiste plaats blijft staan
                if (route.getKey() != verdiep) {
                    ArrayList<String> voegPuntjeToe = new ArrayList<>(route.getValue());
                    route.getValue().clear();
                    route.getValue().add(".");
                    route.getValue().addAll(voegPuntjeToe);
                }
            }
            bepaaldePlaats = 0;
        } else {
            achtbaan.get(verdiep).set(bepaaldePlaats, teken); // indien geen -1 moeten we gewoon het teken op die plaats gaan vervangen
        }
        if ((windrichting == 'O' && !bepaaldePlaatsNietOptellen) || windrichting == 'Z') { //achter dat we het teken toegevoegd hebben zullen we de index/bepaaldeplaats verhogen, de !bepaaldeplaatsnietoptellen wordt gebruikt als we met een naar links of naar rechts zitten en verder moeten naar oost tellen we ook op anders niet.
            bepaaldePlaats++;
        }
        return bepaaldePlaats;
    }

    private static void isDeLengteVanDeArrayCorrect(Map<Integer, ArrayList<String>> achtbaan, int verdiep, int bepaaldePlaats) { //indien we op een verdere plaats in de array komen waar tussen nog geen tekens staan zullen we die dan er tussen vullen met puntjes
        if (achtbaan.get(verdiep).size() != bepaaldePlaats) {
            int verschil = bepaaldePlaats - achtbaan.get(verdiep).size();
            achtbaan.get(verdiep).addAll(vulAchtbaanMetPunten(verschil));
        }
    }

    private static void printenUitkomst(Map<Integer, ArrayList<String>> uitkomst, int test) {
        Map<Integer, ArrayList<String>> gesorteerdeMap = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder()); //hierbij worden de verdiepen gesorteerd aan de hand van een Treemap
        gesorteerdeMap.putAll(uitkomst);
        for (Map.Entry<Integer, ArrayList<String>> entry : gesorteerdeMap.entrySet()) {
            String routeAanEen = String.join("", entry.getValue());
            System.out.println(test + " " + routeAanEen);
        }
    }
}
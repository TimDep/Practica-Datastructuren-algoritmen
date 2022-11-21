import java.util.*;

class Codes {
    private String code;
    private Character letter;

    public Codes(String code, Character letter) {
        this.code = code;
        this.letter = letter;
    }

    public String getCode() {
        return this.code;
    }

    public Character getLetter() {
        return this.letter;
    }

    public static Comparator<Codes> codesComparator = new Comparator<>() {
        @Override
        public int compare(Codes c1, Codes c2) {
            return Integer.compare(c2.getCode().length(), c1.getCode().length());
        }
    };
}

public class Main {

    public static ArrayList<String> woorden = new ArrayList<>();
    public static ArrayList<Codes> code = new ArrayList<>();
    public static StringBuilder oplossing = new StringBuilder();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= aantalTestgevallen; i++) {
            code.clear();
            woorden.clear();
            oplossing.delete(0, oplossing.length());
            String signalen = sc.nextLine();
            int lettercodes = Integer.parseInt(sc.nextLine());
            for (int j = 0; j < lettercodes; j++) {
                Character letter = sc.nextLine().charAt(0);
                String lettercode = sc.nextLine();
                code.add(new Codes(lettercode, letter));
            }
            code.sort(Codes.codesComparator);
            System.out.print(i + " ");
            woordZoeken(signalen);
            Collections.sort(woorden);
            if (woorden.isEmpty()) {
                System.out.println("ONMOGELIJK");
            } else {
                System.out.println(woorden.get(0));
            }
        }
    }

    private static void woordZoeken(String signaal) {
        int lengte = oplossing.length();
        if (signaal.isEmpty() && !woorden.contains(oplossing.toString()) && woorden.isEmpty()) {
            woorden.add(oplossing.toString());
        }
        if (!woorden.isEmpty()) {
            if (lengte > woorden.get(0).length()) {
                oplossing.deleteCharAt(oplossing.length() - 1);
                return;
            } else if (lengte < woorden.get(0).length() && signaal.equals("")) {
                woorden.clear();
                woorden.add(oplossing.toString());
            } else if (lengte == woorden.get(0).length() && signaal.equals("")) {
                woorden.add(oplossing.toString());
            }
        }
        for (Codes codes : code) {
            if (signaal.startsWith(codes.getCode())) {
                oplossing.append(codes.getLetter());
                woordZoeken(signaal.substring(codes.getCode().length()));
            }
        }
        if (oplossing.length() != 0) {
            oplossing.deleteCharAt(oplossing.length() - 1);
        }
    }
}
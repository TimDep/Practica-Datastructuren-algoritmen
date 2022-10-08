import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String brailleAlfabetInvoer = sc.nextLine();
        char[][] brailleCharacters = converteerInvoerNaarArray(brailleAlfabetInvoer, sc);
        ArrayList<String> brailleLijst = converteerCharArrayNaarStringArray(brailleCharacters);

        int tests = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= tests; i++) {
            String braillelijn = sc.nextLine();
            char[][] braille = converteerInvoerNaarArray(braillelijn, sc);
            String woord = vertaalBrailleNaarLetters(braille, brailleLijst);
            System.out.println(i + " " + woord);
        }
    }

    private static char[][] converteerInvoerNaarArray(String s, Scanner sc) {
        char[][] invoer = new char[3][s.length()];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < s.length(); j++)
                invoer[i][j] = s.charAt(j);
            if (i < 2)
                s = sc.nextLine();
        }
        return (invoer);
    }

    private static ArrayList<String> converteerCharArrayNaarStringArray(char[][] brailleCharacters) {
        StringBuilder tussenSb = new StringBuilder();
        ArrayList<String> BrailleLijst = new ArrayList<>();
        int l = 0;
        for (int k = 0; k < (brailleCharacters[0].length / 2); k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = k * 2; j < k * 2 + 2; j++) {
                    tussenSb.append(brailleCharacters[i][j]);

                }

            }
            BrailleLijst.add(tussenSb.toString());
            tussenSb.setLength(0);
        }

        return BrailleLijst;
    }

    private static String vertaalBrailleNaarLetters(char[][] c, ArrayList<String> BrailleLijst) {
        StringBuilder tussenSb = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        for (int k = 0; k < (c[0].length / 2); k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = k * 2; j < k * 2 + 2; j++) {
                    tussenSb.append(c[i][j]);

                }

            }
            letters.append(converterenNaarNederlandseLetter(tussenSb.toString(), BrailleLijst, alfabet));
            tussenSb.setLength(0);
        }

        return letters.toString();

    }

    private static String converterenNaarNederlandseLetter(String braille, ArrayList<String> brailleLijst, String alfabet) {
        if (brailleLijst.contains(braille)) {
            int indexNummer = brailleLijst.indexOf(braille);
            return Character.toString(alfabet.charAt(indexNummer));
        } else {
            return "Letter/letters staat niet in het brailleschrift";
        }
    }
}


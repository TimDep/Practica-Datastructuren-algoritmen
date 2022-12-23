
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    private static final String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String brailleAlfabetInvoer = sc.nextLine();
        char[][] brailleCharacters = converteerInvoerNaarArray(brailleAlfabetInvoer, sc);
        HashMap<String, String> braillelijst = charArrayNaarMap(brailleCharacters);
        int tests = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= tests; i++) {
            String braillelijn = sc.nextLine();
            char[][] braille = converteerInvoerNaarArray(braillelijn, sc);
            System.out.println(i + " " +vertaalBrailleNaarLetters(braille, braillelijst) );
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

    private static HashMap<String, String> charArrayNaarMap(char[][] brailleCharacters){
        StringBuilder sb = new StringBuilder();
        HashMap<String, String> braillelijst = new HashMap<>();
        for (int k = 0; k < (brailleCharacters[0].length / 2); k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = k * 2; j < k * 2 + 2; j++) {
                    sb.append(brailleCharacters[i][j]);
                }
            }
            braillelijst.put(sb.toString(), String.valueOf(alfabet.charAt(k)));
            sb.setLength(0);
        }
        return braillelijst;
    }

    private static String vertaalBrailleNaarLetters(char[][] braille, HashMap<String, String> braillelijst){
        StringBuilder sb = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        for (int k = 0; k < (braille[0].length / 2); k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = k * 2; j < k * 2 + 2; j++) {
                    sb.append(braille[i][j]);
                }
            }
            String brailletekens = sb.toString();
            if (braillelijst.containsKey(brailletekens)){
                letters.append(braillelijst.get(brailletekens));
            }
            sb.setLength(0);
        }
        return letters.toString();
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mapAanvullenMetBasisGevallen();
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < aantalTestgevallen; i++) {
            String schietConfig = sc.nextLine();
            System.out.println(i + 1 + " " + getAantalCombinaties(schietConfig));
        }
    }

    private static void mapAanvullenMetBasisGevallen() {
        map.put("GS", 1);
        map.put("SG", 1);
        map.put("GG", 0);
        map.put("SS", 0);
        map.put("G", 0);
        map.put("S", 0);
    }

    private static int getAantalCombinaties(String schietConfig) {
        if (map.containsKey(schietConfig)) {
            return map.get(schietConfig);
        } else {
            int aantal = 0;
            if (schietConfig.charAt(0) != schietConfig.charAt(1))
                aantal += getAantalCombinaties(schietConfig.substring(2));
            if (schietConfig.charAt(0) != schietConfig.charAt(schietConfig.length() - 1))
                aantal += getAantalCombinaties(schietConfig.substring(1, schietConfig.length() - 1));
            for (int i = 2; i < schietConfig.length() - 1; i++) {
                if (schietConfig.charAt(0) != schietConfig.charAt(i)) {
                    aantal += (getAantalCombinaties(schietConfig.substring(1, i)) * getAantalCombinaties(schietConfig.substring(i + 1)));
                }
            }
            map.put(schietConfig, aantal);
            return aantal;
        }
    }
}






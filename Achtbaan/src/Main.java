import java.util.*;

import static java.util.Map.entry;

public class Main {

    private static final String windrichtingen = "OZWN";

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
                for (int j =0; j<segment.length(); j++){
                    String hallo = zoekenNaarCharacter(segment.charAt(j));
                }
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

    private static String zoekenNaarCharacter(char segment){
        Map<String, String> omhoog = krijgDeOmhoogMap();
        Map<String, String> omlaag = new HashMap<>();
//
//        omhoog.put("O", "/");
//        omhoog.put("Z", "#");
//        omhoog.put("W", "\\");
//        omhoog.put("N", "#");
        omlaag.put("O","\\");
        omlaag.put("Z", "#");
        omlaag.put("W","/");
        omlaag.put("N", "#");

        Character standaardRichting = 'O';


        if(Character.toString(segment) == "L" ){
            int index = windrichtingen.indexOf((standaardRichting));
            if(index == 0){
                index = windrichtingen.length();
                standaardRichting = windrichtingen.charAt(index);
            }
            else{
                standaardRichting = windrichtingen.charAt(index-1);
            }
        } else if(Character.toString(segment) == "R" ){
            int index = windrichtingen.indexOf((standaardRichting));
            if(index == windrichtingen.length()-1){
                index = 0;
                standaardRichting = windrichtingen.charAt(index);
            }
            else{
                standaardRichting = windrichtingen.charAt(index+1);
            }
        } else if (Character.toString(segment) == "S") {

        } else if (Character.toString(segment) == "V") {

        } else if (Character.toString(segment) == "U") {

        } else if (Character.toString(segment) == "D") {

        }


        System.out.println(omlaag.get("O"));
        return null;
    }



    private static void getArray() {
        Map<Integer, ArrayList<String>> achtbaan = new HashMap<>();
        ArrayList<String> route = new ArrayList<>();
        achtbaan.put(0, route);

        achtbaan.get(0).add("#");

        ArrayList<String> copyRoute = achtbaan.get(0);
        achtbaan.get(0).clear();
        achtbaan.get(0).add("");
        achtbaan.get(0).addAll(copyRoute);

        boolean naarBeneden = true;
        if (naarBeneden) {
            Integer achtbaanLengte = achtbaan.get(0).size();
        }

        boolean overschrijven = true;
        if (overschrijven) {
            achtbaan.get(0).set(3, "#");
        }




    }

}
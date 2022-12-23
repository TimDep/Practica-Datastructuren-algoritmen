import java.util.*;


public class main {
    static ArrayList<Lettercode> codes = new ArrayList<>();
    static ArrayList<String> woord = new ArrayList<>();
    static StringBuilder antwoord = new StringBuilder();
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int aantalTesten = Integer.parseInt(sc.nextLine());


        for(int i = 1; i <= aantalTesten; i++){
            codes.clear();
            woord.clear();
            antwoord.delete(0, antwoord.length());
            String input  = sc.nextLine();
            int aantal = Integer.parseInt(sc.nextLine());
            codes = new ArrayList<>(aantal);
            Character letter;
            String code;
            for(int j = 0; j < aantal; j++){
                letter = sc.nextLine().charAt(0);
                code = sc.nextLine();

                codes.add(new Lettercode(letter, code));
            }
            System.out.print(i + " ");
            codes.sort(Lettercode.letterCodeVergelijken);
            recursieFormule(input);
            Collections.sort(woord);
            if(woord.isEmpty()) System.out.println("ONMOGELIJK");
            else System.out.println(woord.get(0));

        }
    }

    public static void recursieFormule(String i){
        int x = antwoord.length();
        if(i.isEmpty()){
            if(!woord.contains(antwoord.toString()) && woord.isEmpty()) woord.add(antwoord.toString());
        }
        if (!woord.isEmpty()){
            if (x > woord.get(0).length()){ antwoord.deleteCharAt(antwoord.length() - 1); return;}
            else if (x < woord.get(0).length()&& i.isEmpty()) {
                woord.clear();
                woord.add(antwoord.toString());
            }
            else if( x == woord.get(0).length() && i.isEmpty() && !woord.contains(antwoord.toString())) woord.add(antwoord.toString());

        }

        for(Lettercode l: codes){
            if(i.startsWith(l.getCode())){
                antwoord.append(l.getLetter());
                recursieFormule(i.substring(l.getCode().length()));
            }
        }
        if(antwoord.length() != 0) antwoord.deleteCharAt(antwoord.length() - 1);
    }
}

class Lettercode{
    Character letter;
    String code;

    public Lettercode(Character letter, String code){
        this.letter = letter;
        this.code = code;
    }

    public Character getLetter() {
        return letter;
    }

    public String getCode() {
        return code;
    }

    public static Comparator<Lettercode>  letterCodeVergelijken= new Comparator<>() {
        @Override
        public int compare(Lettercode o1, Lettercode o2) {
            return Integer.compare(o2.getCode().length(), o1.getCode().length());
        }
    };


}
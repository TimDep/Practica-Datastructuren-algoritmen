import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        String[] strs = {"cir","car"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int temp= 999999;
        for (String str : strs) {
            if (temp > str.length()) {
                temp = str.length();
            }
        }
        Character letter;
        List<StringBuilder> letters = new ArrayList<>();
        for (int i=0; i<temp;i++){
            letter=strs[0].charAt(i);
            int teller =0;
            for (int j =1; j<strs.length; j++){
                if (letter==strs[j].charAt(i)){
                    teller++;
                }
            }
            if (teller == strs.length-1){
                sb.append(letter);
            }
            else{
                letters.add(sb);
                sb.delete(0, sb.length()-1);
            }

        }
        if (sb.isEmpty()){
            return "";
        }
        for (StringBuilder opl :letters){

        }
    }
}
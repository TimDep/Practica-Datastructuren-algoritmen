import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = sc.nextInt();
        for (int i=0;i<aantalTestgevallen; i++){
            int aantalNeeVragen = sc.nextInt();
            int maximaalAantalJaar = sc.nextInt();
            System.out.println(i+1 +" "+berekenAantalJaVragen(aantalNeeVragen, maximaalAantalJaar));

        }
    }

    private static int berekenAantalJaVragen(int N, int I){
        if( N >= I){
            return 1;
        }
        int [][] rooster = new int[N+1][I+1];
        for (int i =1; i<= N;i++){
            rooster[i][0]=0;
            rooster[i][1]=1;
        }
        for (int i = 1; i <= I;i++){
            rooster[1][i] = i;
        }
        int temp;
        for (int i=2; i<=N; i++){
            for (int j=2; j<=I; j++){
                rooster[i][j] = 999999;
                for (int v=1; v<=j; v++){
                    temp = Math.max(rooster[i-1][v-1], 1+rooster[i][j-v]);
                    if (temp<rooster[i][j]){
                        rooster[i][j] = temp;
                    }
                }
            }
        }
        return rooster[N][I];
    }
}
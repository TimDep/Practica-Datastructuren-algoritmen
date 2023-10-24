import java.util.*;


public class Main {
    public static void main(String[] args) {
        int n =6;
        int k = 6;
        eiVal(n,k);

    }

    public static void eiVal(int n , int k){
        int[][] rooster = new int[n+1][k+1];
        for(int i =1; i<=n; i++){
            rooster[i][0]=0;
            rooster[i][1]=1;
        }
        for (int j=1; j<=k; j++) rooster[1][j]=j;
        int tmp;
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                rooster[i][j] = 9999999;
                for (int v = 1; v <= j; v++) {
                    tmp=1+Math.max(rooster[i-1][v-1],rooster[i][j-v]);
                    if (tmp < rooster[i][j]) rooster[i][j] = tmp;
                }
            }
        }
        System.out.println(Arrays.deepToString(rooster));
    }
}
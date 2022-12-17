import java.util.Scanner;

class creerGraaf {
    int knopen;
    int verbindingen;
    creerBoog[] boog;

    creerGraaf(int knopen, int verbindingen) {
        this.knopen = knopen;
        this.verbindingen = verbindingen;
        boog = new creerBoog[verbindingen];
        for (int i = 0; i < verbindingen; i++) {
            boog[i] = new creerBoog();
        }
    }
}

class creerBoog {
    int s;
    int d;
    int w;

    creerBoog() {
        s = d = w = 0;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = sc.nextInt();
        for (int i = 0; i < aantalTestgevallen; i++) {
            int aantalKnopen = sc.nextInt();
            int aantalVerbindingen = sc.nextInt();
            creerGraaf graaf = new creerGraaf(aantalKnopen, aantalVerbindingen);
            for (int j = 0; j < aantalVerbindingen; j++) {
                graaf.boog[j].s = sc.nextInt() - 1;
                graaf.boog[j].d = sc.nextInt() - 1;
                graaf.boog[j].w = sc.nextInt();
            }
            System.out.println("");
            System.out.print(i + 1 + " ");
            bellmanFord(graaf, 0);
        }
    }

    public static void bellmanFord(creerGraaf graaf, int s) {
        int knopen = graaf.knopen;
        int verbindingen = graaf.verbindingen;
        int[] afstand = new int[knopen];
        for (int i = 0; i < knopen; i++) {
            afstand[i] = 999999999;
        }
        afstand[s] = 0;
        for (int i = 1; i < knopen; i++) {
            for (int j = 0; j < verbindingen; j++) {
                int u = graaf.boog[j].s;
                int v = graaf.boog[j].d;
                int w = graaf.boog[j].w;
                if (afstand[u] != 999999999 && afstand[u] + w < afstand[v]) {
                    afstand[v] = afstand[u] + w;
                }
            }
        }
        for (int i = 0; i < verbindingen; i++) {
            int u = graaf.boog[i].s;
            int v = graaf.boog[i].d;
            int w = graaf.boog[i].w;
            if (afstand[u] != 999999999 && afstand[u] + w < afstand[v]) {
                System.out.print("min oneindig");
                return;
            }
        }
        if (afstand[knopen - 1] == 999999999) {
            System.out.print("plus oneindig");
        } else {
            System.out.print(afstand[knopen - 1]);
        }
    }

}
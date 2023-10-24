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
        int aantalMijnvelden = sc.nextInt();
        for (int i = 0; i < aantalMijnvelden; i++) {
            int rijen = sc.nextInt();
            int kolommen = sc.nextInt();
            int aantalKnopen = (rijen * kolommen) + 2;
            int aantalVerbindingen = 0;
            if (rijen > 2 && kolommen > 3) {
                aantalVerbindingen = rijen * (kolommen + 1) + (2 * (kolommen - 2) * (rijen - 1)) + ((rijen) * (kolommen - 3));
            } else if (kolommen > 1 && rijen > 1) {
                aantalVerbindingen = rijen * (kolommen + 1) + (2 * (kolommen - 2) * (rijen - 1));
            } else if (kolommen == 1 && rijen > 1) {
                aantalVerbindingen = 2 * rijen;
            } else if (rijen == 1 && kolommen > 1) {
                aantalVerbindingen = kolommen + 1;
            } else {
                aantalVerbindingen = 2;
            }
            int[][] matrix = new int[rijen][kolommen];
            for (int j = 0; j < rijen; j++) {
                for (int k = 0; k < kolommen; k++) {
                    matrix[j][k] = sc.nextInt();
                }
            }
            creerGraaf graaf = new creerGraaf(aantalKnopen, aantalVerbindingen);
            System.out.println();
            System.out.print(i + 1 + " ");
            if (rijen > 1 && kolommen > 1) {
                matrixOmzettenNaarKnopen(matrix, graaf);
                dijkstra(graaf, 0, matrix.length, matrix[0].length);
            } else {
                zoekKorsteInSingleArray(matrix);
            }
        }
    }

    public static void zoekKorsteInSingleArray(int[][] matrix) {
        int temp = 999999;
        if (matrix.length == 1) {
            temp = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                temp += matrix[0][j];
            }
            System.out.print(matrix[0].length + " " + temp);
        } else {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][0] < temp) {
                    temp = matrix[j][0];
                }
            }
            System.out.print(1 + " " + temp);
        }

    }


    public static void matrixOmzettenNaarKnopen(int[][] matrix, creerGraaf graaf) {
        int verbindingen = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length + 1; j++) {
                if (j == 0) {
                    graaf.boog[verbindingen].s = 0;
                    graaf.boog[verbindingen].d = (i * matrix[0].length) + j + 1;
                    graaf.boog[verbindingen].w = matrix[i][j];
                    verbindingen++;
                } else if (j == 1 || j == matrix[0].length - 1) {
                    graaf.boog[verbindingen].s = (i * matrix[0].length) + j;
                    graaf.boog[verbindingen].d = (i * matrix[0].length) + j + 1;
                    graaf.boog[verbindingen].w = matrix[i][j];
                    verbindingen++;
                } else if (j == matrix[0].length) {
                    graaf.boog[verbindingen].s = (i * matrix[0].length) + j;
                    graaf.boog[verbindingen].d = matrix.length * matrix[0].length + 1;
                    graaf.boog[verbindingen].w = 0;
                    verbindingen++;
                } else {
                    graaf.boog[verbindingen].s = (i * matrix[0].length) + j;
                    graaf.boog[verbindingen].d = (i * matrix[0].length) + j + 1;
                    graaf.boog[verbindingen].w = matrix[i][j];
                    verbindingen++;
                    if (matrix.length > 2) {
                        graaf.boog[verbindingen].s = (i * matrix[0].length) + j + 1;
                        graaf.boog[verbindingen].d = (i * matrix[0].length) + j;
                        graaf.boog[verbindingen].w = matrix[i][j];
                        verbindingen++;
                    }
                }
                if (i >= 1 && j >= 1 && j < matrix[0].length - 1) {
                    graaf.boog[verbindingen].s = (i * matrix[0].length) + j + 1;
                    graaf.boog[verbindingen].d = ((i - 1) * matrix[0].length) + j + 1;
                    graaf.boog[verbindingen].w = matrix[i - 1][j];
                    verbindingen++;
                    graaf.boog[verbindingen].s = ((i - 1) * matrix[0].length) + j + 1;
                    graaf.boog[verbindingen].d = (i * matrix[0].length) + j + 1;
                    graaf.boog[verbindingen].w = matrix[i][j];
                    verbindingen++;
                }
            }
        }
    }

    public static void dijkstra(creerGraaf graaf, int beginpunt, int matrixRijLengte, int matrixKolomLengte) {
        int knopen = graaf.knopen;
        int verbindingen = graaf.verbindingen;
        int[] aantalKnopen = new int[knopen];
        int[] afstand = new int[knopen];
        for (int i = 0; i < knopen; i++) {
            aantalKnopen[i] = 0;
            afstand[i] = 999999;
        }
        afstand[beginpunt] = 0;
        for (int i = 1; i < knopen; i++) {
            for (int j = 0; j < verbindingen; j++) {
                int u = graaf.boog[j].s;
                int v = graaf.boog[j].d;
                int w = graaf.boog[j].w;
                if (afstand[u] != 999999 && afstand[u] + w < afstand[v]) {
                    afstand[v] = afstand[u] + w;
                    aantalKnopen[v] = aantalKnopen[u] + 1;
                }
            }
        }
        int kortsteKnopen = aantalKnopen[knopen - 1]-1;
        for (int i = matrixKolomLengte; i<matrixRijLengte*matrixKolomLengte; i+=matrixKolomLengte){
            if (aantalKnopen[i]<kortsteKnopen && afstand[i] == afstand[knopen-1]){
                kortsteKnopen = aantalKnopen[i];
            }
        }
        System.out.print(kortsteKnopen+ " " + afstand[knopen - 1]);

    }
}
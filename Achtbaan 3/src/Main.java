import java.util.*;

class achtbaan {
    private Integer x;
    private Integer y;
    private Integer z;
    private String symbool;

    public achtbaan(Integer x, Integer y, Integer z, String symbool) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.symbool = symbool;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return this.z;
    }

    public String getSymbool() {
        return this.symbool;
    }

    public static Comparator<achtbaan> achtbaanComparator = new Comparator<achtbaan>() {
        @Override
        public int compare(achtbaan z1, achtbaan z2) {
            return z1.getZ().compareTo(z2.getZ());
        }
    };
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aantalTestgevallen = Integer.parseInt(sc.nextLine());
        if (aantalTestgevallen < 1) {
            System.out.println("Er kunnen niet minder dan 1 testgeval zijn.");
            System.exit(0);
        } else if (aantalTestgevallen > 1000) {
            System.out.println("Dit ligt buiten de limieten van dit programma.");
            System.exit(0);
        }
        for (int i = 1; i <= aantalTestgevallen; i++) {
            int aantalSegmenten = Integer.parseInt(sc.next());
            if (aantalSegmenten < 6) {
                System.out.println("Dit zijn te weinig segmenten om een achtbaan te maken.");
                System.exit(0);
            } else if (aantalSegmenten > 500) {
                System.out.println("Dit zijn te veel segmenten om een achtbaan te maken.");
                System.exit(0);
            } else {
                String segment = sc.nextLine();
                if (segment.length() - 1 != aantalSegmenten) {
                    System.out.println("Je hebt teveel segmenten ingetypt dan je vooraf declareerde.");
                    System.exit(0);
                } else {
                    ArrayList<achtbaan> plaats = zoekenNaarCharacters(segment);
                    verschuivenVanCoordinaten(plaats, i);
                }
            }
        }
    }

    private static ArrayList<achtbaan> zoekenNaarCharacters(String segment) {
        ArrayList<achtbaan> segmenten = new ArrayList<>();
        int x = 0;
        int y = 0;
        int z = 0;
        char standaardRichting = 'O';

        for (int i = 0; i < segment.length(); i++) {
            char routeSegment = segment.charAt(i);
            if (routeSegment == 'L') {
                if (standaardRichting == 'O') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    z--;
                    standaardRichting = 'N';
                } else if (standaardRichting == 'W') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    z++;
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'N') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    x--;
                    standaardRichting = 'W';
                } else if (standaardRichting == 'Z') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    x++;
                    standaardRichting = 'O';
                }

            } else if (routeSegment == 'R') {
                if (standaardRichting == 'O') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    z++;
                    standaardRichting = 'Z';
                } else if (standaardRichting == 'W') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    z--;
                    standaardRichting = 'N';
                } else if (standaardRichting == 'N') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    x++;
                    standaardRichting = 'O';
                } else if (standaardRichting == 'Z') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    x--;
                    standaardRichting = 'W';
                }

            } else if (routeSegment == 'S') {
                segmenten.add(new achtbaan(x, y, z, "="));
                x++;

            } else if (routeSegment == 'V') {
                if (standaardRichting == 'W') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    x--;
                } else if (standaardRichting == 'O') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    x++;
                } else if (standaardRichting == 'N') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    z--;
                } else if (standaardRichting == 'Z') {
                    segmenten.add(new achtbaan(x, y, z, "_"));
                    z++;
                }
            } else if (routeSegment == 'U') {
                if (standaardRichting == 'O') {
                    segmenten.add(new achtbaan(x, y, z, "/"));
                    x++;
                    y++;
                } else if (standaardRichting == 'W') {
                    segmenten.add(new achtbaan(x, y, z, "\\"));
                    x--;
                    y++;
                } else if (standaardRichting == 'Z') {
                    segmenten.add(new achtbaan(x, y, z, "#"));
                    z++;
                    y++;
                } else if (standaardRichting == 'N') {
                    segmenten.add(new achtbaan(x, y, z, "#"));
                    z--;
                    y++;
                }

            } else if (routeSegment == 'D') {
                if (standaardRichting == 'O') {
                    y--;
                    segmenten.add(new achtbaan(x, y, z, "\\"));
                    x++;
                } else if (standaardRichting == 'W') {
                    y--;
                    segmenten.add(new achtbaan(x, y, z, "/"));
                    x--;
                } else if (standaardRichting == 'Z') {
                    y--;
                    segmenten.add(new achtbaan(x, y, z, "#"));
                    z++;
                } else if (standaardRichting == 'N') {
                    y--;
                    segmenten.add(new achtbaan(x, y, z, "#"));
                    z--;
                }
            }
        }
        return segmenten;
    }

    private static void verschuivenVanCoordinaten(ArrayList<achtbaan> segmenten, int tests) {
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        int minZ = 0;
        int maxZ = 0;

        for (int i = 0; i < segmenten.size(); i++) {
            if (segmenten.get(i).getX() > maxX) {
                maxX = segmenten.get(i).getX();
            } else if (segmenten.get(i).getX() < minX) {
                minX = segmenten.get(i).getX();
            }
            if (segmenten.get(i).getY() > maxY) {
                maxY = segmenten.get(i).getY();
            } else if (segmenten.get(i).getY() < minY) {
                minY = segmenten.get(i).getY();
            }
            if (segmenten.get(i).getZ() > maxZ) {
                maxZ = segmenten.get(i).getZ();
            } else if (segmenten.get(i).getZ() < minZ) {
                minZ = segmenten.get(i).getZ();
            }
        }
        if(minX<0){
            minX=-minX;
            maxX+=minX;
            for (int i =0; i<segmenten.size();i++){
               int x = segmenten.get(i).getX();
               segmenten.get(i).setX(x+minX);
           }
        }
        if(minY<0){
            minY=-minY;
            maxY+=minY;
            for (int i =0; i<segmenten.size();i++){
                int y = segmenten.get(i).getY();
                segmenten.get(i).setY(y+minY);
            }
        }
        String[][] uitkomst = arrayMaken(maxX, maxY);
        sorterenOpZWaarde(segmenten);
        arrayGaanVullenMetSymbolen(uitkomst, segmenten, maxY, tests);

    }

    private static String[][] arrayMaken(int maxX, int maxY){
        String[][] uitkomst = new String[maxY+1][maxX+1];
        for (int i = 0; i<=maxY; i++){
            for(int j=0; j<=maxX;j++){
                uitkomst[i][j] = ".";
            }
        }
        return uitkomst;
    }

    private static void sorterenOpZWaarde(ArrayList<achtbaan> segmenten){
        segmenten.sort(achtbaan.achtbaanComparator);
    }

    private static void arrayGaanVullenMetSymbolen(String[][] uitkomst, ArrayList<achtbaan> segmenten, int maxY, int test){
        for (int i =0; i<segmenten.size(); i++){
            uitkomst[maxY-segmenten.get(i).getY()][segmenten.get(i).getX()] = segmenten.get(i).getSymbool();
        }
        for (String[] strings : uitkomst) {
            System.out.print(test+" ");
            for (int j = 0; j < strings.length; j++) {
                System.out.print(strings[j] + "");
            }
            System.out.println();
        }
    }
}

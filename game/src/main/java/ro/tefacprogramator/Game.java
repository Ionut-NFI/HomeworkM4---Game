package ro.tefacprogramator;

public class Game 
{
    public static void main( String[] args )
    {
        Play();
    }

    public static void Play(){
        Character ch1 = new Character();
        Character ch2 = new Character();
        FileMethods q = new FileMethods();
        CityMap cityMap = new CityMap();

        cityMap.setMapX(q.ReadRowFromFile());
        cityMap.setMapY(q.ReadColumnFromFile());

        char[] matrix1D = q.ReadOtherLineFromFile().toCharArray();

        cityMap.setMap(q.makeMatrix2D(matrix1D, cityMap));

        ch1.setMapPositionX(q.RPosition(matrix1D, cityMap)[0]);
        ch1.setMapPositionY(q.RPosition(matrix1D, cityMap)[1]);

        ch2.setMapPositionX(q.JPosition(matrix1D, cityMap)[0]);
        ch2.setMapPositionY(q.JPosition(matrix1D, cityMap)[1]);

        System.out.println("R position -> " + ch1.getMapPositionX() + " " + ch1.getMapPositionY());
        System.out.println("J position -> " + ch2.getMapPositionX() + " " + ch2.getMapPositionY());
        

        


        // print matrix 2D
        System.out.println("Row number -> " + q.ReadRowFromFile());
        System.out.println("Column number -> " + q.ReadColumnFromFile());
        for (int i = 0; i < cityMap.getMapX(); i++) {
            for (int j = 0; j < cityMap.getMapY(); j++) {
                System.out.print(cityMap.getMap()[i][j]);
            }
            System.out.println();
        }
        Logic(ch2, ch2, q, cityMap);
    }

    public static void Logic(Character ch1, Character ch2, FileMethods q, CityMap map) {
        System.out.println("Logic function");
        System.out.println();

        boolean[][] visitedMap = new boolean[q.ReadRowFromFile()][q.ReadColumnFromFile()];

        int dist = 0;


        for (int i = 0; i < map.getMapX(); i++) {
            for (int j = 0; j < map.getMapY(); j++) {
                System.out.print(visitedMap[i][j] + " ");
            }
            System.out.println();
        }
        
        boolean answer = isSafe(map, visitedMap, 2, 2);
        System.out.println(answer);

        answer = isValid(5, 2, map);
        System.out.println(answer);

        int min_dist = fiindMinDistance(map, visitedMap, ch2, ch2, Integer.MAX_VALUE, 0);

        if(min_dist != Integer.MAX_VALUE){
            System.out.println("The shortest distance is "+min_dist);
        }
    }

    public static boolean isSafe(CityMap map, boolean visitedMap[][], int x, int y) {

        return !(map.getMap()[x][y] == '#' || visitedMap[x][y] == true);
    }

    public static boolean isValid(int x, int y, CityMap map){
        return (x < map.getMapX() && y <map.getMapY() && x >=0 && y>=0);
    }

    public static int fiindMinDistance(CityMap map,boolean visitedMap[][], Character ch1, Character ch2, int min_dist,int dist){
        
        
        return min_dist;
    }
}

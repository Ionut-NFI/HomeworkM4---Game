package ro.tefacprogramator;

/**
 * Hello world!
 *
 */
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

        int x = getRandomNumber(1, q.ReadRowFromFile());
        int y = getRandomNumber(1, q.ReadColumnFromFile());

        ch1.setMapPositionX(x);
        ch1.setMapPositionY(y);


        x = getRandomNumber(1, q.ReadRowFromFile());
        y = getRandomNumber(1, q.ReadColumnFromFile());

        ch2.setMapPositionX(x);
        ch2.setMapPositionY(y);

        
        
        System.out.println(q.ReadRowFromFile());
        System.out.println(q.ReadColumnFromFile());

        CityMap cityMap = new CityMap();
        cityMap.setMapX(q.ReadRowFromFile());
        cityMap.setMapY(q.ReadColumnFromFile());
        char[] matrix1D = q.ReadOtherLineFromFile().toCharArray();
        char[][] matrix2D = makeMatrix2D(matrix1D, cityMap);
        cityMap.setMap(matrix2D);

        // print matrix 2D
        
        for (int i = 0; i < cityMap.getMapX(); i++) {
            for (int j = 0; j < cityMap.getMapY(); j++) {
                System.out.print(cityMap.getMap()[i][j]);
            }
            System.out.println();
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static char[][] makeMatrix2D(char[] matrix1D, CityMap cityMap) {
        char [][] tempMap = new char[cityMap.getMapX()][cityMap.getMapY()];
    int count = 0;
    for(int i = 0; i < cityMap.getMapX();i++){
        for(int j = 0; j< cityMap.getMapY();j++){
            tempMap[i][j] = matrix1D[count];
            count++;
        }
    }
    return tempMap;
    }
}

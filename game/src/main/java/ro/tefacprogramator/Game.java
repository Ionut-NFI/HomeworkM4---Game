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
    }


    
}

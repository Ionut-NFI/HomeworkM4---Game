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
        q.ReadOtherLineFromFile();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

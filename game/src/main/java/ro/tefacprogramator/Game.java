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

        int x = getRandomNumber(1, 10);
        int y = getRandomNumber(1, 10);

        ch1.setMapPositionX(x);
        ch1.setMapPositionY(y);


        x = getRandomNumber(1, 10);
        y = getRandomNumber(1, 10);

        ch2.setMapPositionX(x);
        ch2.setMapPositionY(y);


        FileMethods x1 = new FileMethods();
        x1.ReadRowFromFile();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

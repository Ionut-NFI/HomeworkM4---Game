package ro.tefacprogramator;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;


public class AppTest 
{
    @Test
    public void shouldSetCharacterMapPositionX()
    {
        Character c = new Character();
        c.setMapPositionX(10);
        Assert.assertEquals( 10 , c.getMapPositionX() );
    }

    @Test
    public void shouldSetCharacterMapPositionY()
    {
        Character c = new Character();
        c.setMapPositionY(5);
        Assert.assertEquals( 5 , c.getMapPositionY() );
    }

}

package ro.tefacprogramator;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
        Character c = new Character();
    
    @Test
    public void shouldSetCharacterMapPositionX()
    {
        
        c.setMapPositionX(10);
        Assert.assertEquals( 10 , c.getMapPositionX() );
    }

    @Test
    public void shouldSetCharacterMapPositionY()
    {
        
        c.setMapPositionY(5);
        Assert.assertEquals( 5 , c.getMapPositionY() );
    }
}

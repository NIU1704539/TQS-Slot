package slot.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class PlayerTest {

    Player player;

    @BeforeEach 
    public void setUp()
    {
        player = new Player();
    }

    @Test 
    public void TestPlayer() // this test covers the functions for getCredit and the default constructor
    {
        assertEquals(1000,player.getCredit());
    }

    @Test 
    public void TestPlayerWithParameters()
    {
        player = new Player(1);
        assertEquals(1, player.getCredit());

        player = new Player(0);
        assertEquals(0, player.getCredit());

        player = new Player(-1);
        assertEquals(1000, player.getCredit());
    }

    @Test
    public void TestSetCredit()
    {
        // valid value
        player.setCredit(500);
        assertEquals(500, player.getCredit());

        // boundary: 0 is valid
        player.setCredit(0);
        assertEquals(0, player.getCredit());

        // boundary: -1 is invalid, credit does not change
        player.setCredit(-1);
        assertEquals(0, player.getCredit());
    }

    @Test
    public void TestSubstractCredit()
    {
        // valid value within the credit
        assertEquals(900, player.substractCredit(100));
        assertEquals(900, player.getCredit());

        // boundary: subtracting 0 does not change the credit
        assertEquals(900, player.substractCredit(0));
        assertEquals(900, player.getCredit());

        // boundary: -1 is invalid
        assertEquals(-1, player.substractCredit(-1));
        assertEquals(900, player.getCredit());

        // boundary: credit + 1 is invalid
        assertEquals(-1, player.substractCredit(901));
        assertEquals(900, player.getCredit());

        // boundary: subtracting exactly the credit leaves 0
        assertEquals(0, player.substractCredit(900));
        assertEquals(0, player.getCredit());

        // with credit 0 nothing can be subtracted
        assertEquals(-1, player.substractCredit(1));
        assertEquals(0, player.getCredit());
    }

    @Test
    public void TestAddCredit()
    {
        // valid value
        assertEquals(1100, player.addCredit(100));
        assertEquals(1100, player.getCredit());

        // boundary: adding 0 does not change the credit
        assertEquals(1100, player.addCredit(0));
        assertEquals(1100, player.getCredit());

        // boundary: -1 is invalid
        assertEquals(-1, player.addCredit(-1));
        assertEquals(1100, player.getCredit());
    }

}

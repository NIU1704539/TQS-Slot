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

    



}

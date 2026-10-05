package slot.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
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
        setUp();
        assertEquals(player.getCredit(),1000);
    }

    @Test 
    public void TestPlayer(int credit)
    {
        player = new Player(500);
        assertEquals(player.getCredit(), 500);
    }

    



}

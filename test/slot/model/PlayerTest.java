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
    public void TestgetCredit()
    {
        setUp();
        AssertEquals(Player.getCredit(),0);
        
    }

    @Test 
    public void TestPlayer()
    {
        setUp();
        AssertEquals(Player.getCredit(),0);
    }



}

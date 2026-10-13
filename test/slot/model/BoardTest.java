package slot.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class BoardTest {

    MockRNG pseudoRNG;
    Board grid;

    @BeforeEach 
    public void setUp()
    {
        pseudoRNG = new MockRNG();
        grid = new Board();
    }

    @Test 
    public void BoardRngTest()
    {
        

    }

}

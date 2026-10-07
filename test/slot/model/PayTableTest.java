package slot.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayTableTest {

    PayTable symbol;

    @BeforeEach 
    void setUp()
    {
        symbol = new PayTable();
    }

    @Test 
    void PayTableTest()
    {
        assertEquals(0.15, symbol.getWeights());
        assertEquals(0.14, symbol.getWeights());
        assertEquals(0.13, symbol.getWeights());
        assertEquals(0.12, symbol.getWeights());
        assertEquals(0.11, symbol.getWeights());
        assertEquals(0.10, symbol.getWeights());
        assertEquals(0.10, symbol.getWeights());
        assertEquals(0.08, symbol.getWeights());
        assertEquals(0.05, symbol.getWeights());
        assertEquals(0.02, symbol.getWeights());
    }

}

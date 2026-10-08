package slot.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayTableTest {

    PayTable payTable;

    @BeforeEach 
    void setUp()
    {
        payTable = new PayTable();
    }

    @Test 
    void PayTableTest()
    {
        assertEquals(0.15, payTable.getWeight(Symbol.J), 1e-9);
        assertEquals(0.14, payTable.getWeight(Symbol.Q), 1e-9);
        assertEquals(0.13, payTable.getWeight(Symbol.K), 1e-9);
        assertEquals(0.12, payTable.getWeight(Symbol.A), 1e-9);
        assertEquals(0.11, payTable.getWeight(Symbol.CHERRY), 1e-9);
        assertEquals(0.10, payTable.getWeight(Symbol.LEMON), 1e-9);
        assertEquals(0.10, payTable.getWeight(Symbol.KIWI), 1e-9);
        assertEquals(0.08, payTable.getWeight(Symbol.BLUEBERRY), 1e-9);
        assertEquals(0.05, payTable.getWeight(Symbol.MELON), 1e-9);
        assertEquals(0.02, payTable.getWeight(Symbol.WILD), 1e-9);
    }

}

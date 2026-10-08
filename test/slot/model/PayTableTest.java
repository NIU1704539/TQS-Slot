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

        assertArrayEquals(new double[]{2.40, 1.20, 0.60}, payTable.getPayouts(Symbol.J), 1e-9);
        assertArrayEquals(new double[]{2.80, 1.40, 0.70}, payTable.getPayouts(Symbol.Q), 1e-9);
        assertArrayEquals(new double[]{3.60, 1.80, 0.90}, payTable.getPayouts(Symbol.K), 1e-9);
        assertArrayEquals(new double[]{5.20, 2.60, 1.30}, payTable.getPayouts(Symbol.A), 1e-9);
        assertArrayEquals(new double[]{9.20, 4.60, 2.30}, payTable.getPayouts(Symbol.CHERRY), 1e-9);
        assertArrayEquals(new double[]{23.00, 7.00, 3.00}, payTable.getPayouts(Symbol.LEMON), 1e-9);
        assertArrayEquals(new double[]{35.00, 11.00, 5.00}, payTable.getPayouts(Symbol.BLUEBERRY), 1e-9);
        assertArrayEquals(new double[]{47.00, 15.00, 7.00}, payTable.getPayouts(Symbol.KIWI), 1e-9);
        assertArrayEquals(new double[]{77.00, 21.00, 10.00}, payTable.getPayouts(Symbol.MELON), 1e-9);
        assertArrayEquals(new double[]{77.00, 77.00, 77.00}, payTable.getPayouts(Symbol.WILD), 1e-9);
    }
}

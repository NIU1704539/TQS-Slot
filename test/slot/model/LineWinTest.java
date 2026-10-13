package slot.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class LineWinTest 
{
    LineWin test;

    @BeforeEach 
    void setUp()
    {
        test = new LineWin(6,Symbol.CHERRY, 3);
    }

    @Test
    void LineWinInvalidTest()
    {
        assertThrows(AssertionError.class, () -> new LineWin(11, Symbol.CHERRY, 3));
        assertThrows(AssertionError.class, () -> new LineWin(4, null, 3));
        assertThrows(AssertionError.class, () -> new LineWin(4, Symbol.CHERRY, 7));

        assertThrows(AssertionError.class, () -> new LineWin(-1, Symbol.CHERRY, 3));
        assertThrows(AssertionError.class, () -> new LineWin(4, Symbol.CHERRY, -1));
    }

    @Test
    void LineWinTest()
    {
        assertEquals(6, test.getPayline());
        assertEquals(Symbol.CHERRY, test.getSymbol());
        assertEquals(3, test.getAmount());
    } 
}

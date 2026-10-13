package slot.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    MockRNG pseudoRNG;
    Board grid;

    @Test
    public void boardFillsGridWithRngSymbols()
    {
        pseudoRNG = new MockRNG(new double[]{
            0.60, 0.60, 0.60, 0.10, 0.20,   // fila 0
            0.10, 0.20, 0.35, 0.50, 0.70,   // fila 1
            0.80, 0.90, 0.95, 0.10, 0.20    // fila 2
        });
        grid = new Board(pseudoRNG);

        // fila 0
        assertEquals(Symbol.CHERRY, grid.getSymbol(0, 0));
        assertEquals(Symbol.CHERRY, grid.getSymbol(0, 1));
        assertEquals(Symbol.CHERRY, grid.getSymbol(0, 2));
        assertEquals(Symbol.J, grid.getSymbol(0, 3));
        assertEquals(Symbol.Q, grid.getSymbol(0, 4));

        // fila 1
        assertEquals(Symbol.J, grid.getSymbol(1, 0));
        assertEquals(Symbol.Q, grid.getSymbol(1, 1));
        assertEquals(Symbol.K, grid.getSymbol(1, 2));
        assertEquals(Symbol.A, grid.getSymbol(1, 3));
        assertEquals(Symbol.LEMON, grid.getSymbol(1, 4));

        // fila 2
        assertEquals(Symbol.KIWI, grid.getSymbol(2, 0));
        assertEquals(Symbol.BLUEBERRY, grid.getSymbol(2, 1));
        assertEquals(Symbol.MELON, grid.getSymbol(2, 2));
        assertEquals(Symbol.J, grid.getSymbol(2, 3));
        assertEquals(Symbol.Q, grid.getSymbol(2, 4));
    }

}

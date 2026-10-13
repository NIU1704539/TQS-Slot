package slot.model;

import static org.junit.jupiter.api.Assertions.*;
import static slot.model.Symbol.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class BoardTest {

    MockRNG pseudoRNG;
    Board board;

    /**
     * Crea un Board cuyo primer spin() deja exactamente la matriz indicada.
     */
    private Board boardWith(Symbol[][] grid)
    {
        pseudoRNG = new MockRNG(grid);
        return new Board(pseudoRNG);
    }

    /**
     * Crea un Board con la fila superior (payline 0) indicada y el resto sin premios.
     */
    private Board boardWithTopRow(Symbol... topRow)
    {
        return boardWith(new Symbol[][]{
            topRow,
            {J, Q, K, A, J},
            {Q, K, A, J, Q}
        });
    }

    // ---------------------------------------------------------------- spin / constructores

    @Test
    public void boardFillsGridWithRngSymbols()
    {
        pseudoRNG = new MockRNG(new double[]{
            0.60, 0.60, 0.60, 0.10, 0.20,   // fila 0
            0.10, 0.20, 0.35, 0.50, 0.70,   // fila 1
            0.80, 0.90, 0.95, 0.10, 0.20    // fila 2
        });
        board = new Board(pseudoRNG);

        // fila 0
        assertEquals(Symbol.CHERRY, board.getSymbol(0, 0));
        assertEquals(Symbol.CHERRY, board.getSymbol(0, 1));
        assertEquals(Symbol.CHERRY, board.getSymbol(0, 2));
        assertEquals(Symbol.J, board.getSymbol(0, 3));
        assertEquals(Symbol.Q, board.getSymbol(0, 4));

        // fila 1
        assertEquals(Symbol.J, board.getSymbol(1, 0));
        assertEquals(Symbol.Q, board.getSymbol(1, 1));
        assertEquals(Symbol.K, board.getSymbol(1, 2));
        assertEquals(Symbol.A, board.getSymbol(1, 3));
        assertEquals(Symbol.LEMON, board.getSymbol(1, 4));

        // fila 2
        assertEquals(Symbol.KIWI, board.getSymbol(2, 0));
        assertEquals(Symbol.BLUEBERRY, board.getSymbol(2, 1));
        assertEquals(Symbol.MELON, board.getSymbol(2, 2));
        assertEquals(Symbol.J, board.getSymbol(2, 3));
        assertEquals(Symbol.Q, board.getSymbol(2, 4));
    }

    @Test
    public void spinAsksRngOncePerCell()
    {
        pseudoRNG = new MockRNG(new double[]{0.10});
        board = new Board(pseudoRNG);

        // el constructor hace el primer spin: 3 x 5 = 15 llamadas
        assertEquals(Board.ROWS * Board.COLS, pseudoRNG.getNumLlamadas());

        board.spin();
        assertEquals(2 * Board.ROWS * Board.COLS, pseudoRNG.getNumLlamadas());
    }

    @Test
    public void spinRegeneratesGrid()
    {
        double[] values = new double[2 * Board.ROWS * Board.COLS];
        for (int i = 0; i < values.length; i++)
        {
            // primera tirada todo J, segunda tirada todo MELON
            values[i] = (i < Board.ROWS * Board.COLS) ? MockRNG.symbolToValue(J) : MockRNG.symbolToValue(MELON);
        }
        pseudoRNG = new MockRNG(values);
        board = new Board(pseudoRNG);
        assertEquals(J, board.getSymbol(0, 0));
        assertEquals(J, board.getSymbol(2, 4));

        board.spin();
        for (int i = 0; i < Board.ROWS; i++)
        {
            for (int j = 0; j < Board.COLS; j++)
            {
                assertEquals(MELON, board.getSymbol(i, j));
            }
        }
    }

    @Test
    public void defaultConstructorFillsGridWithRealRng()
    {
        board = new Board();

        for (int i = 0; i < Board.ROWS; i++)
        {
            for (int j = 0; j < Board.COLS; j++)
            {
                assertNotNull(board.getSymbol(i, j));
            }
        }
    }

    // ---------------------------------------------------------------- getSymbol / getGrid

    @Test
    public void getSymbolInvalidPositions()
    {
        board = boardWithTopRow(J, J, J, J, J);

        // valores frontera fuera de rango
        assertThrows(AssertionError.class, () -> board.getSymbol(-1, 0));
        assertThrows(AssertionError.class, () -> board.getSymbol(Board.ROWS, 0));
        assertThrows(AssertionError.class, () -> board.getSymbol(0, -1));
        assertThrows(AssertionError.class, () -> board.getSymbol(0, Board.COLS));

        // valores frontera dentro de rango
        assertEquals(J, board.getSymbol(0, 0));
        assertEquals(Q, board.getSymbol(Board.ROWS - 1, Board.COLS - 1));
    }

    @Test
    public void getGridReturnsCopy()
    {
        Symbol[][] expected = {
            {J, Q, K, A, CHERRY},
            {LEMON, KIWI, BLUEBERRY, MELON, WILD},
            {Q, K, A, J, LEMON}
        };
        board = boardWith(expected);

        Symbol[][] copy = board.getGrid();
        assertEquals(Board.ROWS, copy.length);
        for (int i = 0; i < Board.ROWS; i++)
        {
            assertArrayEquals(expected[i], copy[i]);
        }

        // modificar la copia no afecta al tablero
        copy[0][0] = WILD;
        assertEquals(J, board.getSymbol(0, 0));
    }

    // ---------------------------------------------------------------- getLineSymbols

    @Test
    public void getLineSymbolsFollowsPaylines()
    {
        board = boardWith(new Symbol[][]{
            {J,     Q,    K,         A,     CHERRY},
            {LEMON, KIWI, BLUEBERRY, MELON, WILD},
            {Q,     K,    A,         J,     LEMON}
        });

        assertArrayEquals(new Symbol[]{J, Q, K, A, CHERRY},                    board.getLineSymbols(0)); // fila superior
        assertArrayEquals(new Symbol[]{LEMON, KIWI, BLUEBERRY, MELON, WILD},   board.getLineSymbols(1)); // fila central
        assertArrayEquals(new Symbol[]{Q, K, A, J, LEMON},                     board.getLineSymbols(2)); // fila inferior
        assertArrayEquals(new Symbol[]{J, KIWI, A, MELON, CHERRY},             board.getLineSymbols(3)); // V
        assertArrayEquals(new Symbol[]{Q, KIWI, K, MELON, LEMON},              board.getLineSymbols(4)); // V invertida
        assertArrayEquals(new Symbol[]{LEMON, Q, K, A, WILD},                  board.getLineSymbols(5));
        assertArrayEquals(new Symbol[]{LEMON, K, A, J, WILD},                  board.getLineSymbols(6));
        assertArrayEquals(new Symbol[]{J, Q, BLUEBERRY, J, LEMON},             board.getLineSymbols(7));
        assertArrayEquals(new Symbol[]{Q, K, BLUEBERRY, A, CHERRY},            board.getLineSymbols(8));
        assertArrayEquals(new Symbol[]{LEMON, K, BLUEBERRY, A, WILD},          board.getLineSymbols(9));
    }

    @Test
    public void getLineSymbolsInvalidLine()
    {
        board = boardWithTopRow(J, J, J, J, J);

        assertThrows(AssertionError.class, () -> board.getLineSymbols(-1));
        assertThrows(AssertionError.class, () -> board.getLineSymbols(Board.PAYLINES.length));
    }

    // ---------------------------------------------------------------- evaluateLine

    @Test
    public void evaluateLineNoWin()
    {
        // solo 2 iguales
        assertNull(boardWithTopRow(CHERRY, CHERRY, J, Q, K).evaluateLine(0));
        // la racha se rompe en medio
        assertNull(boardWithTopRow(CHERRY, CHERRY, J, CHERRY, CHERRY).evaluateLine(0));
        // la racha no empieza por la izquierda
        assertNull(boardWithTopRow(J, CHERRY, CHERRY, CHERRY, CHERRY).evaluateLine(0));
    }

    @Test
    public void evaluateLineThreeFourFive()
    {
        LineWin win = boardWithTopRow(A, A, A, J, Q).evaluateLine(0);
        assertEquals(0, win.getPayline());
        assertEquals(A, win.getSymbol());
        assertEquals(3, win.getAmount());

        win = boardWithTopRow(LEMON, LEMON, LEMON, LEMON, Q).evaluateLine(0);
        assertEquals(LEMON, win.getSymbol());
        assertEquals(4, win.getAmount());

        win = boardWithTopRow(MELON, MELON, MELON, MELON, MELON).evaluateLine(0);
        assertEquals(MELON, win.getSymbol());
        assertEquals(5, win.getAmount());
    }

    @Test
    public void evaluateLineWithWild()
    {
        // WILD en medio sustituye al símbolo
        LineWin win = boardWithTopRow(CHERRY, WILD, CHERRY, J, Q).evaluateLine(0);
        assertEquals(CHERRY, win.getSymbol());
        assertEquals(3, win.getAmount());

        // WILD al principio toma el valor del primer símbolo normal
        win = boardWithTopRow(WILD, WILD, KIWI, KIWI, J).evaluateLine(0);
        assertEquals(KIWI, win.getSymbol());
        assertEquals(4, win.getAmount());

        // línea entera de WILD
        win = boardWithTopRow(WILD, WILD, WILD, WILD, WILD).evaluateLine(0);
        assertEquals(WILD, win.getSymbol());
        assertEquals(5, win.getAmount());

        // WILD no salva una racha de 2 seguida de otro símbolo
        assertNull(boardWithTopRow(WILD, CHERRY, J, J, J).evaluateLine(0));
    }

    // ---------------------------------------------------------------- checkWins

    @Test
    public void checkWinsWithoutWins()
    {
        // todas las paylines empiezan por J, Q: ninguna llega a 3
        board = boardWith(new Symbol[][]{
            {J, Q, MELON, MELON, MELON},
            {J, Q, MELON, MELON, MELON},
            {J, Q, MELON, MELON, MELON}
        });

        assertTrue(board.checkWins().isEmpty());
    }

    @Test
    public void checkWinsReturnsEveryWinningLine()
    {
        board = boardWith(new Symbol[][]{
            {MELON, MELON, MELON, MELON, MELON},
            {J,     Q,     MELON, A,     J},
            {J,     Q,     K,     A,     J}
        });

        List<LineWin> wins = board.checkWins();
        assertEquals(2, wins.size());

        // payline 0: fila superior, 5 MELON
        assertEquals(0, wins.get(0).getPayline());
        assertEquals(MELON, wins.get(0).getSymbol());
        assertEquals(5, wins.get(0).getAmount());

        // payline 7: (0,0) (0,1) (1,2) (2,3) (2,4) -> 3 MELON
        assertEquals(7, wins.get(1).getPayline());
        assertEquals(MELON, wins.get(1).getSymbol());
        assertEquals(3, wins.get(1).getAmount());
    }

    // ---------------------------------------------------------------- toString

    @Test
    public void toStringShowsGrid()
    {
        board = boardWith(new Symbol[][]{
            {J,     Q,    K,         A,     CHERRY},
            {LEMON, KIWI, BLUEBERRY, MELON, WILD},
            {Q,     K,    A,         J,     LEMON}
        });

        String expected =
            "J | Q | K | A | CHERRY\n" +
            "LEMON | KIWI | BLUEBERRY | MELON | WILD\n" +
            "Q | K | A | J | LEMON\n";

        assertEquals(expected, board.toString());
    }
}

package slot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Núcleo computacional del sistema.
 * Genera la matriz de {@link Symbol} y valida si hay líneas ganadoras cuando Game lo solicita.
 */


public class Board {
    private Symbol grid[][];
    private RNG rng;
    public static final int ROWS = 3;
    public static final int COLS = 5;

    /**
     * Las 10 paylines (resources/paylines.png). PAYLINES[line][col] es la fila que se usa en esa columna.
     */
    public static final int[][] PAYLINES = {
        {0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1},
        {2, 2, 2, 2, 2},
        {0, 1, 2, 1, 0},
        {2, 1, 0, 1, 2},
        {1, 0, 0, 0, 1},
        {1, 2, 2, 2, 1},
        {0, 0, 1, 2, 2},
        {2, 2, 1, 0, 0},
        {1, 2, 1, 0, 1}
    };

    /**
     * Crea el tablero con un RNG real y genera una matriz inicial aleatoria.
     */
    public Board()
    {
        this(new RNG());
    }

    /**
     * Crea el tablero con el RNG recibido (permite inyectar MockRNG en los tests)
     * y genera una matriz inicial.
     */
    public Board(RNG rng)
    {
        grid = new Symbol[ROWS][COLS];
        this.rng = rng;
        spin();
    }

    /**
     * Rellena las 15 casillas de la matriz con símbolos obtenidos del RNG.
     */
    public void spin()
    {
        for (int i = 0; i < ROWS; i++) 
        {
            for (int j = 0; j < COLS; j++) 
            {
                double valorDouble = rng.getrandNumber();
                grid[i][j] = rng.RNGtoSymbol(valorDouble);
            }
        }
    }

    /**
     * Devuelve el símbolo de la posición (row, col). Posiciones fuera de rango no son válidas.
     */
    public Symbol getSymbol(int row, int col)
    {
        assert(row >= 0 && row < ROWS) : "invalid row value";
        assert(col >= 0 && col < COLS) :  "invalid col value";  

        return grid[row][col]; 
    }

    /**
     * Devuelve una copia de la matriz para que no se pueda modificar desde fuera.
     */
    public Symbol[][] getGrid()
    {
        Symbol[][] copy = new Symbol[ROWS][];
        for (int i = 0; i < ROWS; i++)
        {
            copy[i] = grid[i].clone();
        }
        return copy;
    }

    /**
     * Devuelve los 5 símbolos que caen sobre la payline indicada (0-9), de izquierda a derecha.
     */
    public Symbol[] getLineSymbols(int line)
    {
        assert(line >= 0 && line < PAYLINES.length) : "invalid payline value";

        Symbol[] symbols = new Symbol[COLS];
        for (int col = 0; col < COLS; col++)
        {
            symbols[col] = grid[PAYLINES[line][col]][col];
        }
        return symbols;
    }

    /**
     * Cuenta los símbolos iguales consecutivos desde la izquierda en la payline indicada (0-9),
     * teniendo en cuenta que WILD sustituye a cualquier símbolo.
     * Devuelve un LineWin si hay 3 o más; si no, devuelve null.
     */
    public LineWin evaluateLine(int line)
    {
        Symbol[] symbols = getLineSymbols(line);

        // el símbolo de la línea es el primero que no es WILD (si todos son WILD, es WILD)
        Symbol target = Symbol.WILD;
        for (Symbol s : symbols)
        {
            if (s != Symbol.WILD)
            {
                target = s;
                break;
            }
        }

        int amount = 0;
        while (amount < COLS && (symbols[amount] == target || symbols[amount] == Symbol.WILD))
        {
            amount++;
        }

        if (amount < 3)
            return null;

        return new LineWin(line, target, amount);
    }

    /**
     * Recorre las 10 paylines y devuelve la lista de líneas ganadoras
     * (número de línea, símbolo y cantidad).
     */
    public List<LineWin> checkWins()
    {
        List<LineWin> wins = new ArrayList<>();
        for (int line = 0; line < PAYLINES.length; line++)
        {
            LineWin win = evaluateLine(line);
            if (win != null)
                wins.add(win);
        }
        return wins;
    }

    /**
     * Representación del tablero en texto para mostrarlo por consola.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                if (j > 0)
                    sb.append(" | ");
                sb.append(grid[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

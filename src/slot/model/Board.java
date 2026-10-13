package slot.model;

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
     * Crea el tablero con un RNG real y genera una matriz inicial aleatoria.
     */
    public Board()
    {
        grid = new Symbol[ROWS][COLS];
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
        return null;
    }

    /**
     * Devuelve los 5 símbolos que caen sobre la payline indicada (1-10), de izquierda a derecha.
     */
    public Symbol[] getLineSymbols(int line)
    {
        return null;
    }

    /**
     * Cuenta los símbolos iguales consecutivos desde la izquierda (teniendo en cuenta WILD).
     * Devuelve el símbolo y la cantidad si hay 3 o más; si no, indica que no hay premio.
     */
    public LineWin evaluateLine(Symbol[] symbols)
    {
        return null;
    }

    /**
     * Recorre las 10 paylines y devuelve la lista de líneas ganadoras
     * (número de línea, símbolo y cantidad).
     */
    public List<LineWin> checkWins()
    {
        return null;
    }

    /**
     * Representación del tablero en texto para mostrarlo por consola.
     */
    @Override
    public String toString()
    {
        return null;
    }

}

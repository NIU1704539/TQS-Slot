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

    }

    /**
     * Crea el tablero con el RNG recibido (permite inyectar MockRNG en los tests)
     * y genera una matriz inicial.
     */
    public Board(RNG rng)
    {

    }

    /**
     * Rellena las 15 casillas de la matriz con símbolos obtenidos del RNG.
     */
    public void spin()
    {

    }

    /**
     * Devuelve el símbolo de la posición (row, col). Posiciones fuera de rango no son válidas.
     */
    public Symbol getSymbol(int row, int col)
    {

    }

    /**
     * Devuelve una copia de la matriz para que no se pueda modificar desde fuera.
     */
    public Symbol[][] getGrid()
    {

    }

    /**
     * Devuelve los 5 símbolos que caen sobre la payline indicada (1-10), de izquierda a derecha.
     */
    public Symbol[] getLineSymbols(int line)
    {

    }

    /**
     * Cuenta los símbolos iguales consecutivos desde la izquierda (teniendo en cuenta WILD).
     * Devuelve el símbolo y la cantidad si hay 3 o más; si no, indica que no hay premio.
     */
    public LineWin evaluateLine(Symbol[] symbols)
    {

    }

    /**
     * Recorre las 10 paylines y devuelve la lista de líneas ganadoras
     * (número de línea, símbolo y cantidad).
     */
    public List<LineWin> checkWins()
    {

    }

    /**
     * Representación del tablero en texto para mostrarlo por consola.
     */
    @Override
    public String toString()
    {

    }

}

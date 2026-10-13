package slot.model;

public class MockRNG extends RNG {

    private double[] lista;
    private int indice;

    public MockRNG(double[] lista)
    {
        assert(lista != null && lista.length > 0) : "the mockRNG list cannot be null or empty";
        indice = 0;
        this.lista = lista;
    }

    /**
     * Crea un MockRNG que, al llamar a spin(), rellena el tablero exactamente con la matriz indicada
     * (recorrida por filas, de izquierda a derecha).
     */
    public MockRNG(Symbol[][] grid)
    {
        this(symbolsToValues(grid));
    }

    public int getNumLlamadas()
    {
        return indice;
    }

    @Override
    public double getrandNumber()
    {
        double valor = lista[indice % lista.length];
        indice++;
        return valor;
    }

    /**
     * Devuelve un valor del rango de probabilidad de cada símbolo, de forma que RNGtoSymbol(valor) == symbol.
     */
    public static double symbolToValue(Symbol symbol)
    {
        switch (symbol)
        {
            case J:         return 0.10;
            case Q:         return 0.20;
            case K:         return 0.35;
            case A:         return 0.50;
            case CHERRY:    return 0.60;
            case LEMON:     return 0.70;
            case KIWI:      return 0.80;
            case BLUEBERRY: return 0.90;
            case MELON:     return 0.95;
            default:        return 1.00; // WILD
        }
    }

    private static double[] symbolsToValues(Symbol[][] grid)
    {
        assert(grid != null && grid.length > 0) : "the mockRNG grid cannot be null or empty";

        double[] valores = new double[grid.length * grid[0].length];
        int k = 0;
        for (Symbol[] fila : grid)
        {
            for (Symbol s : fila)
            {
                valores[k++] = symbolToValue(s);
            }
        }
        return valores;
    }
}

package slot.model;
import java.util.EnumMap;


/**
 * Relaciona cada {@link Symbol} con sus pesos (bonificaciones) mediante un Map&lt;Symbol, Double&gt;.
 * Se consulta únicamente para calcular el retorno del jugador en caso de victoria.
 */

class PayTable {
    private EnumMap<Symbol, double[]> symbolPayouts;

    public PayTable()
    {
        symbolPayouts = new EnumMap<>(Symbol.class);
        symbolPayouts.put(Symbol.J, new double[]{2.40, 1.20, 0.60});
        symbolPayouts.put(Symbol.Q, new double[]{2.80, 1.40, 0.70});
        symbolPayouts.put(Symbol.K, new double[]{3.60, 1.80, 0.90});
        symbolPayouts.put(Symbol.A, new double[]{5.20, 2.60, 1.30});
        symbolPayouts.put(Symbol.CHERRY, new double[]{9.20, 4.60, 2.30});
        symbolPayouts.put(Symbol.LEMON, new double[]{23.00, 7.00, 3.00});
        symbolPayouts.put(Symbol.KIWI, new double[]{47.00, 15.00, 7.00});
        symbolPayouts.put(Symbol.BLUEBERRY, new double[]{35.00, 11.00, 5.00});
        symbolPayouts.put(Symbol.MELON, new double[]{77.00, 21.00, 10.00});
        symbolPayouts.put(Symbol.WILD, new double[]{77.00, 77.00, 77.00});
    }
    
    public double[] getPayouts(Symbol symbol)
    {
        return symbolPayouts.get(symbol);
    }

}

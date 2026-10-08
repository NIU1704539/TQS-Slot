package slot.model;
import java.util.EnumMap;


/**
 * Relaciona cada {@link Symbol} con sus pesos (bonificaciones) mediante un Map&lt;Symbol, Double&gt;.
 * Se consulta únicamente para calcular el retorno del jugador en caso de victoria.
 */

class PayTable {
    private EnumMap<Symbol,Double> symbolWeights;
    private EnumMap<Symbol, double[]> symbolPayouts;

    public PayTable()
    {
        symbolWeights = new EnumMap<>(Symbol.class);
        symbolWeights.put(Symbol.J, 0.15);
        symbolWeights.put(Symbol.Q, 0.14);
        symbolWeights.put(Symbol.K, 0.13);
        symbolWeights.put(Symbol.A, 0.12);
        symbolWeights.put(Symbol.CHERRY, 0.11);
        symbolWeights.put(Symbol.LEMON, 0.10);
        symbolWeights.put(Symbol.KIWI, 0.10);
        symbolWeights.put(Symbol.BLUEBERRY, 0.08);
        symbolWeights.put(Symbol.MELON, 0.05);
        symbolWeights.put(Symbol.WILD, 0.02);

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


    public Double getWeight(Symbol symbol)
    {
        return symbolWeights.get(symbol);
    }
    
    public double[] getPayouts(Symbol symbol)
    {
        return symbolPayouts.get(symbol);
    }

}

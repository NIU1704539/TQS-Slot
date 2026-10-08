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
        symbolPayouts.put(Symbol.J, new double[]{2.40f, 1.20f, 0.60f});
        symbolPayouts.put(Symbol.Q, new double[]{2.80f, 1.40f, 0.70f});
        symbolPayouts.put(Symbol.K, new double[]{3.60f, 1.80f, 0.90f});
        symbolPayouts.put(Symbol.A, new double[]{5.20f, 2.60f, 1.30f});
        symbolPayouts.put(Symbol.CHERRY, new double[]{9.20f, 4.60f, 2.30f});
        symbolPayouts.put(Symbol.LEMON, new double[]{23.00f, 7.00f, 3.00f});
        symbolPayouts.put(Symbol.KIWI, new double[]{47.00f, 15.00f, 7.00f});
        symbolPayouts.put(Symbol.BLUEBERRY, new double[]{35.00f, 11.00f, 5.00f});
        symbolPayouts.put(Symbol.MELON, new double[]{77.00f, 21.00f, 10.00f});
        symbolPayouts.put(Symbol.WILD, new double[]{77.00f, 77.00f, 77.00f});
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

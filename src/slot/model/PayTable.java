package slot.model;
import java.util.EnumMap;


/**
 * Relaciona cada {@link Symbol} con sus pesos (bonificaciones) mediante un Map&lt;Symbol, Double&gt;.
 * Se consulta únicamente para calcular el retorno del jugador en caso de victoria.
 */

class PayTable {
    private EnumMap<Symbol,Double> symbolWeights;
    private EnumMap<Symbol, float[]> symbolPayouts;

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
    }


    public Double getWeight(Symbol symbol)
    {
        return symbolWeights.get(symbol);
    }
    
    public float[] getPayouts(Symbol symbol)
    {
        return new float[] {0f, 0f, 5f, 10f, 20f};    
    }

}

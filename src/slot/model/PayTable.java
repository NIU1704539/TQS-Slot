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

    }

    public Double getWeight(Symbol symbol)
    {
        return null;
    }


}

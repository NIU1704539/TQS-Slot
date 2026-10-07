package slot.model;
import java.util.Map;

/**
 * Relaciona cada {@link Symbol} con sus pesos (bonificaciones) mediante un Map&lt;Symbol, Double&gt;.
 * Se consulta únicamente para calcular el retorno del jugador en caso de victoria.
 */

class PayTable {
    private Map<Symbol,Double> symbolWeights;
    private Map<Symbol, float[]> symbolPayouts;



    public PayTable()
    {

    }


}

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
}

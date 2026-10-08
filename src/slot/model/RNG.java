package slot.model;

public class RNG {

    private double randNumber;

    public RNG()
    {

    }

    public double getrandNumber()
    {
        return 0.0;
    }

    public Symbol RNGtoSymbol(double number)
    {
        assert number >= 0.0 && number <= 1.0 : "El numero debe estar en el rango [0,1]: " + number;

        number = Math.round(number * 100) / 100.0;

        if (number <= 0.15) return Symbol.J;
        if (number <= 0.29) return Symbol.Q;
        if (number <= 0.42) return Symbol.K;
        if (number <= 0.54) return Symbol.A;
        if (number <= 0.65) return Symbol.CHERRY;
        if (number <= 0.75) return Symbol.LEMON;
        if (number <= 0.85) return Symbol.KIWI;
        if (number <= 0.93) return Symbol.BLUEBERRY;
        if (number <= 0.98) return Symbol.MELON;
        return Symbol.WILD;
    }

    
}

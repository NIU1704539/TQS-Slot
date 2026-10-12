package slot.model;

public class LineWin {
    int payLine;
    Symbol symbol;
    int amountSymbol;

    public LineWin(int payLine, Symbol symbol, int amountSymbol)
    {
        assert(payLine >= 0 && payLine <= 9) : "Error on the payLine, not possible";
        assert(symbol != null) : "Error on the symbol, not initialized";
        assert(amountSymbol >= 3 && amountSymbol <= 5) : "Error on the amountSymbol, not possible";

        this.payLine = payLine;
        this.symbol = symbol;
        this.amountSymbol = amountSymbol;
    }

    public int getPayline()
    {
        return payLine;   
    }

    public int getAmount()
    {
        return amountSymbol;
    }

    public Symbol getSymbol()
    {
        return symbol;
    }
    
}

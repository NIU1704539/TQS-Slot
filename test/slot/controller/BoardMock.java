package slot.controller;
import slot.model.Board;
import slot.model.Symbol;

public class BoardMock extends Board {

    private Symbol grid[][];

    public BoardMock(Symbol grid[][])
    {
        this.grid = new Symbol[ROWS][COLS];
        for(int i = 0; i < ROWS;i++)
            for (int j = 0; j < COLS; j++) 
                this.grid[i][j] = grid[i][j];

    }

    @Override 
    public void spin()
    {

    }

    @Override 
    public Symbol getSymbol(int row, int col)
    {
        return this.grid[row][col]; 
    }

    @Override 
    public Symbol[][] getGrid()
    {
        Symbol[][] copy = new Symbol[ROWS][];
        for (int i = 0; i < ROWS; i++)
        {
            copy[i] = this.grid[i].clone();
        }
        return copy;

    }

    @Override 
    public Symbol[] getLineSymbols(int line)
    {
        Symbol[] symbols = new Symbol[COLS];
        for (int col = 0; col < COLS; col++)
        {
            symbols[col] = this.grid[PAYLINES[line][col]][col];
        }
        return symbols;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                if (j > 0)
                    sb.append(" | ");
                sb.append(this.grid[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}

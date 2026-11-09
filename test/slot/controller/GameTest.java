package slot.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import slot.model.*;

class GameTest {

    static final double DELTA = 1e-9;

    Game game;
    BoardMock mockBoard;

    // no line can win: columns 0 and 1 never share a symbol and there are no WILDs
    static final Symbol[][] NO_WIN_GRID = {
        {Symbol.J, Symbol.A,      Symbol.J, Symbol.A,      Symbol.J},
        {Symbol.Q, Symbol.CHERRY, Symbol.Q, Symbol.CHERRY, Symbol.Q},
        {Symbol.K, Symbol.LEMON,  Symbol.K, Symbol.LEMON,  Symbol.K}
    };

    // only payline 0 wins: 3 MELON (x10.00)
    static final Symbol[][] ONE_WIN_GRID = {
        {Symbol.MELON, Symbol.MELON,  Symbol.MELON, Symbol.K,      Symbol.Q},
        {Symbol.Q,     Symbol.CHERRY, Symbol.Q,     Symbol.CHERRY, Symbol.Q},
        {Symbol.K,     Symbol.LEMON,  Symbol.K,     Symbol.LEMON,  Symbol.K}
    };

    @BeforeEach
    public void setUp()
    {
        mockBoard = new BoardMock(NO_WIN_GRID);
        game = new Game(mockBoard);
    }

    private Symbol[][] gridFilledWith(Symbol symbol)
    {
        Symbol[][] grid = new Symbol[Board.ROWS][Board.COLS];
        for (int i = 0; i < Board.ROWS; i++)
            for (int j = 0; j < Board.COLS; j++)
                grid[i][j] = symbol;
        return grid;
    }

    // ---------------------------------------------------------------- constructors

    @Test
    public void defaultConstructor()
    {
        game = new Game();
        assertNotNull(game.getBoard());
        assertNotNull(game.getGamer());
        assertNotNull(game.getPayTable());
        assertEquals(1000, game.getGamer().getCredit());
        assertFalse(game.isGameOver());
    }

    @Test
    public void parametredBoardContructor()
    {
        assertNotNull(game.getGamer());
        assertNotNull(game.getPayTable());
        assertEquals(1000, game.getGamer().getCredit());
        assertFalse(game.isGameOver());

        // the Board received must be the same object, not a copy
        assertSame(mockBoard, game.getBoard());
    }

    // ---------------------------------------------------------------- isValidBet

    @Test
    public void isValidBetLimits()
    {
        // invalid values
        assertFalse(game.isValidBet(-1));
        assertFalse(game.isValidBet(0));

        // boundaries just outside the limits
        assertFalse(game.isValidBet(Game.MIN_BET - 0.01));
        assertFalse(game.isValidBet(Game.MAX_BET + 0.01));

        // boundaries inside the limits
        assertTrue(game.isValidBet(Game.MIN_BET));
        assertTrue(game.isValidBet(Game.MAX_BET));

        // value in the middle
        assertTrue(game.isValidBet((Game.MIN_BET + Game.MAX_BET) / 2));
    }

    // ---------------------------------------------------------------- hasEnoughCredit

    @Test
    public void hasEnoughCredit()
    {
        game.getGamer().setCredit(50);

        assertTrue(game.hasEnoughCredit(10));
        // boundary: credit == bet is enough
        assertTrue(game.hasEnoughCredit(50));
        // boundary: credit + 0.01 is not enough
        assertFalse(game.hasEnoughCredit(50.01));

        // checking the credit does not modify it
        assertEquals(50, game.getGamer().getCredit());
    }

    // ---------------------------------------------------------------- calculatePrize

    @Test
    public void calculatePrizeWithoutWins()
    {
        assertEquals(0, game.calculatePrize(new ArrayList<>(), 10), DELTA);
    }

    @Test
    public void calculatePrizeThreeFourFive()
    {
        // CHERRY: x9.20 (5), x4.60 (4), x2.30 (3)
        assertEquals(23.0, game.calculatePrize(List.of(new LineWin(0, Symbol.CHERRY, 3)), 10), DELTA);
        assertEquals(46.0, game.calculatePrize(List.of(new LineWin(0, Symbol.CHERRY, 4)), 10), DELTA);
        assertEquals(92.0, game.calculatePrize(List.of(new LineWin(0, Symbol.CHERRY, 5)), 10), DELTA);
    }

    @Test
    public void calculatePrizeUsesTheBet()
    {
        // J with 3 symbols: x0.60
        assertEquals(0.6, game.calculatePrize(List.of(new LineWin(0, Symbol.J, 3)), 1), DELTA);
        assertEquals(60.0, game.calculatePrize(List.of(new LineWin(0, Symbol.J, 3)), 100), DELTA);
    }

    @Test
    public void calculatePrizeSeveralLinesAreAdded()
    {
        List<LineWin> wins = List.of(
            new LineWin(0, Symbol.MELON, 3),  // x10.00
            new LineWin(3, Symbol.A, 4),      // x2.60
            new LineWin(7, Symbol.WILD, 5)    // x77.00
        );

        assertEquals(10 * (10.00 + 2.60 + 77.00), game.calculatePrize(wins, 10), DELTA);
    }

    // ---------------------------------------------------------------- playRound

    @Test
    public void playRoundWithoutPrize()
    {
        double prize = game.playRound(10);

        assertEquals(0, prize, DELTA);
        assertEquals(990, game.getGamer().getCredit(), DELTA);
        assertEquals(1, mockBoard.getNumSpins());
        assertFalse(game.isGameOver());
    }

    @Test
    public void playRoundWithPrize()
    {
        mockBoard = new BoardMock(ONE_WIN_GRID);
        game = new Game(mockBoard);

        // 3 MELON: 10 * 10.00 = 100
        double prize = game.playRound(10);

        assertEquals(100, prize, DELTA);
        assertEquals(1000 - 10 + 100, game.getGamer().getCredit(), DELTA);
        assertEquals(1, mockBoard.getNumSpins());
    }

    @Test
    public void playRoundWithSeveralWinningLines()
    {
        mockBoard = new BoardMock(gridFilledWith(Symbol.CHERRY));
        game = new Game(mockBoard);

        // 10 lines of 5 CHERRY: 10 * 10 * 9.20 = 920
        double prize = game.playRound(10);

        assertEquals(920, prize, DELTA);
        assertEquals(1000 - 10 + 920, game.getGamer().getCredit(), DELTA);
    }

    @Test
    public void playRoundInvalidBet()
    {
        assertEquals(-1, game.playRound(0), DELTA);
        assertEquals(-1, game.playRound(Game.MIN_BET - 0.01), DELTA);
        assertEquals(-1, game.playRound(Game.MAX_BET + 0.01), DELTA);

        // the round is not played: no spin, credit unchanged, the game goes on
        assertEquals(0, mockBoard.getNumSpins());
        assertEquals(1000, game.getGamer().getCredit(), DELTA);
        assertFalse(game.isGameOver());
    }

    @Test
    public void playRoundInsufficientCredit()
    {
        game.getGamer().setCredit(5);

        assertEquals(-1, game.playRound(10), DELTA);

        // the round is not played and the game ends
        assertEquals(0, mockBoard.getNumSpins());
        assertEquals(5, game.getGamer().getCredit(), DELTA);
        assertTrue(game.isGameOver());
    }

    @Test
    public void playRoundExactCredit()
    {
        game.getGamer().setCredit(10);

        // boundary: credit == bet allows playing
        assertEquals(0, game.playRound(10), DELTA);
        assertEquals(0, game.getGamer().getCredit(), DELTA);
        assertEquals(1, mockBoard.getNumSpins());
    }

    @Test
    public void playRoundSeveralRounds()
    {
        game.playRound(10);
        game.playRound(20);
        game.playRound(Game.MAX_BET);

        assertEquals(1000 - 10 - 20 - Game.MAX_BET, game.getGamer().getCredit(), DELTA);
        assertEquals(3, mockBoard.getNumSpins());
    }

    // ---------------------------------------------------------------- end of the game

    @Test
    public void retireEndsTheGame()
    {
        assertFalse(game.isGameOver());
        game.retire();
        assertTrue(game.isGameOver());

        // the credit is kept after retiring
        assertEquals(1000, game.getGamer().getCredit(), DELTA);
    }

    @Test
    public void playRoundAfterGameOver()
    {
        game.retire();

        assertEquals(-1, game.playRound(10), DELTA);
        assertEquals(0, mockBoard.getNumSpins());
        assertEquals(1000, game.getGamer().getCredit(), DELTA);
    }
}

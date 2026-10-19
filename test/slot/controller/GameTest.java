package slot.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class GameTest {

    Game game;
    BoardMock mockBoard;

    @BeforeEach 
    public void setUp()
    {
        game = new Game();
    }

    @Test 
    public void parametredBoardContructor()
    {
        game = new Game(mockBoard);
        assertEquals(game.getBoard(), mockBoard);
    }


}

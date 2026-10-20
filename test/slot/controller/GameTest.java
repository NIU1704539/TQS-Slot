package slot.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import slot.model.*;

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
        
        assertThrows(AssertionError.class,()-> game.getGamer());
        assertThrows(AssertionError.class, ()-> game.getPayTable());
        
        assertEquals(game.getBoard(), mockBoard);
    }



}

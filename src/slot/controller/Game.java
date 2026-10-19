package slot.controller;

import slot.model.Board;
import slot.model.Player;
import slot.model.PayTable;


/**
 * Controlador y núcleo funcional del sistema.
 * Relaciona PayTable, Player y Board, interactúa con el usuario y gestiona el flujo del juego.
 */
public class Game 
{
    private Board gameboard;
    private Player gamer;
    private PayTable gamePayTable;

    public Game()
    {
        gameboard = new Board();
        gamer = new Player();
        gamePayTable = new PayTable();
    }

    public Game(Board gameboard)
    {

    }

    public Board getBoard()
    {
        return this.gameboard;
    }

    public Player getGamer()
    {
        return this.gamer;
    }
    
    public PayTable getPayTable()
    {
        return this.gamePayTable;
    }


}

package slot.controller;

import java.util.List;

import slot.model.Board;
import slot.model.LineWin;
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
    private boolean gameOver = false;

    public static final double MIN_BET = 1;
    public static final double MAX_BET = 100;

    public Game()
    {
        gameboard = new Board();
        gamer = new Player();
        gamePayTable = new PayTable();
    }

    public Game(Board gameboard)
    {
        this.gamer = new Player();
        this.gamePayTable = new PayTable();
        this.gameboard = gameboard;
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

    /**
     * Comprueba que la apuesta está dentro de los límites [MIN_BET, MAX_BET].
     */
    public boolean isValidBet(double bet)
    {
        return bet >= MIN_BET && bet <= MAX_BET;
    }

    /**
     * Comprueba que el jugador tiene saldo suficiente para la apuesta (saldo >= apuesta).
     */
    public boolean hasEnoughCredit(double bet)
    {
        return gamer.getCredit() >= bet;
    }

    /**
     * Calcula el premio total de las líneas ganadoras: suma de apuesta * multiplicador de cada línea.
     * El multiplicador sale de PayTable: índice 0 para 5 símbolos, 1 para 4 y 2 para 3.
     */
    public double calculatePrize(List<LineWin> wins, double bet)
    {
        double prize = 0;
        for (LineWin win : wins)
        {
            double[] payouts = gamePayTable.getPayouts(win.getSymbol());
            prize += bet * payouts[Board.COLS - win.getAmount()];
        }
        return prize;
    }

    /**
     * Juega una ronda: valida la apuesta, comprueba el saldo, descuenta la apuesta,
     * hace spin, evalúa las líneas y suma el premio al saldo.
     * Devuelve el premio obtenido (0 si no hay premio) o -1 si la ronda no se juega.
     * Si el saldo no llega para la apuesta, la partida termina.
     */
    public double playRound(double bet)
    {
        if (gameOver || !isValidBet(bet))
            return -1;

        if (!hasEnoughCredit(bet))
        {
            gameOver = true;
            return -1;
        }

        gamer.substractCredit(bet);
        gameboard.spin();

        double prize = calculatePrize(gameboard.checkWins(), bet);
        gamer.addCredit(prize);
        return prize;
    }

    /**
     * El jugador se retira y la partida termina.
     */
    public void retire()
    {
        gameOver = true;
    }

    /**
     * Indica si la partida ha terminado (por retirada o por saldo insuficiente).
     */
    public boolean isGameOver()
    {
        return gameOver;
    }

}

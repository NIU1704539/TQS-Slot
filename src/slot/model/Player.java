package slot.model;

/**
 * Datos del usuario: saldo disponible y otra información que se desee guardar.
 */
public class Player {

    private int credit;

    public Player()
    {
        credit = 1000;
    }

    public Player(int credit)
    {
        this.credit = credit;
    }

    public int getCredit()
    {
        return credit;
    }

    public void setCredit(int credit)
    {

    }

    public int substractCredit(int credit)
    {

    }

    public int addCredit(int credit)
    {
        
    }


}

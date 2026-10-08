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
        if(credit >= 0)
            this.credit = credit;
        else    
            this.credit = 1000;
    }

    public int getCredit()
    {
        return credit;
    }

    public void setCredit(int credit)
    {
        if(credit >= 0)
            this.credit = credit;
    }

    public int substractCredit(int credit)
    {
        if(credit < 0 || credit > this.credit)
            return -1;

        this.credit -= credit;
        return this.credit;
    }

    public int addCredit(int credit)
    {
        if(credit < 0)
            return -1;

        this.credit += credit;
        return this.credit;
    }

}

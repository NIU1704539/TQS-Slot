package slot.model;

/**
 * Datos del usuario: saldo disponible y otra información que se desee guardar.
 */
public class Player {

    private double credit;

    public Player()
    {
        credit = 1000;
    }

    public Player(double credit)
    {
        if(credit >= 0)
            this.credit = credit;
        else    
            this.credit = 1000;
    }

    public double getCredit()
    {
        return credit;
    }

    public void setCredit(double credit)
    {
        if(credit >= 0)
            this.credit = credit;
    }

    public double substractCredit(double credit)
    {
        if(credit < 0 || credit > this.credit)
            return -1;

        this.credit -= credit;
        return this.credit;
    }

    public double addCredit(double credit)
    {
        if(credit < 0)
            return -1;

        this.credit += credit;
        return this.credit;
    }

}

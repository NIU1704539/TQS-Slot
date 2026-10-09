package slot.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class RNGTest {

    RNG number;

    @BeforeEach 
    void setUp()
    {
        number = new RNG();
    }

    @Test 
    void getrandNumberTest()
    {
        double first = number.getrandNumber();
        boolean differentValue = false;

        for(int i=0; i < 200000;i++)
        {
            double value = number.getrandNumber();
            assertTrue(value >= 0.0 && value <= 1.0, "Fuera del rango [0,1]: " + value);

            if (value != first)
            {
                differentValue = true;
            }
        }

        assertTrue(differentValue, "getrandNumber() siempre devuelve el mismo valor: " + first);
    }


    @Test 
    void RNGtoSymbolTest()
    {
        assertEquals(Symbol.J, number.RNGtoSymbol(0.0));
        assertEquals(Symbol.J, number.RNGtoSymbol(0.14));
        assertEquals(Symbol.J, number.RNGtoSymbol(0.15));

        
        assertEquals(Symbol.Q, number.RNGtoSymbol(0.16));
        assertEquals(Symbol.Q, number.RNGtoSymbol(0.28));
        assertEquals(Symbol.Q, number.RNGtoSymbol(0.29));


        assertEquals(Symbol.K, number.RNGtoSymbol(0.30));
        assertEquals(Symbol.K, number.RNGtoSymbol(0.41));
        assertEquals(Symbol.K, number.RNGtoSymbol(0.42));


        assertEquals(Symbol.A, number.RNGtoSymbol(0.43));
        assertEquals(Symbol.A, number.RNGtoSymbol(0.53));
        assertEquals(Symbol.A, number.RNGtoSymbol(0.54));

        assertEquals(Symbol.CHERRY, number.RNGtoSymbol(0.55));
        assertEquals(Symbol.CHERRY, number.RNGtoSymbol(0.64));
        assertEquals(Symbol.CHERRY, number.RNGtoSymbol(0.65));

        assertEquals(Symbol.LEMON, number.RNGtoSymbol(0.66));
        assertEquals(Symbol.LEMON, number.RNGtoSymbol(0.74));
        assertEquals(Symbol.LEMON, number.RNGtoSymbol(0.75));

        assertEquals(Symbol.KIWI, number.RNGtoSymbol(0.76));
        assertEquals(Symbol.KIWI, number.RNGtoSymbol(0.84));
        assertEquals(Symbol.KIWI, number.RNGtoSymbol(0.85));

        assertEquals(Symbol.BLUEBERRY, number.RNGtoSymbol(0.86));
        assertEquals(Symbol.BLUEBERRY, number.RNGtoSymbol(0.92));
        assertEquals(Symbol.BLUEBERRY, number.RNGtoSymbol(0.93));

        assertEquals(Symbol.MELON, number.RNGtoSymbol(0.94));
        assertEquals(Symbol.MELON, number.RNGtoSymbol(0.97));
        assertEquals(Symbol.MELON, number.RNGtoSymbol(0.98));

        assertEquals(Symbol.WILD, number.RNGtoSymbol(0.99));
        assertEquals(Symbol.WILD, number.RNGtoSymbol(1));

    }
    
    
}

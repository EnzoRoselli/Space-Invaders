package Controller;

import java.util.Random;

public class Maths {

    private final int INTERVAL;
    private String random;
    
    public Maths() {
        INTERVAL=1000000;
        random="";
    }

    public String getLastRandom() {
        return random;
    }
    
    public String randomNumber() {
        Random number = new Random(System.currentTimeMillis()); // Producir nuevo int aleatorio entre 0 y 99
        int intRandom = number.nextInt(INTERVAL); //Agrando el intervalo
        random = Integer.toString(intRandom); //Casteo a String
        return random;
    }
}

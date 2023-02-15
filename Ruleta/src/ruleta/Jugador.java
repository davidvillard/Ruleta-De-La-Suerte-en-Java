package ruleta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Jugador {

    private String seleccionRuleta;
    private String nombre;
    private int comodines = 10;
    private double dineroTotal;
    private double dineroRonda = 100;


    public void dividirDinero() {
        this.dineroRonda *= 0.5;
    }

    public void multiplicarDinero() {
        this.dineroRonda *= 2;
    }

    public void restarDinero(int cantidad) {
        this.dineroRonda -= cantidad;
    }

    public void sumarDinero(int cantidad) {
        this.dineroRonda += cantidad;
    }

    public void reiniciarDinero() {
        this.dineroRonda = 0;
    }

    public static void incrementaDinero() {

    }

    public String getSeleccionRuleta() {
        return seleccionRuleta;
    }

    public String ruleta() {
        String[] ruleta = {"0", "25", "50", "100", "150", "Pierde Turno", "75", "50", "150", "75", "x2", "75", "100", "25", "Comodin", "50", "125", "50", "75", "1/2", "150", "75", "25", "50", "Quiebra"};
        ArrayList<String> ruletaList = new ArrayList<>(Arrays.asList(ruleta));
        Random random = new Random();
        seleccionRuleta = ruletaList.get(random.nextInt(ruletaList.size()));
        System.out.println("Has caido en la casilla: " + seleccionRuleta);
        System.out.println("\n");
        return seleccionRuleta;
    }

}

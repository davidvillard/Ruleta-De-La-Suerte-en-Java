package ruleta;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    /*
    ANSI
     */
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    public static final String RESET = "\033[0m";

    Panel panel;
    private int turno;
    private final ArrayList<String> nombresJugadores = new ArrayList<>();
    Jugador jugador = new Jugador();

    public void seleccionarNumeroJugadoresYOrden() {

        Scanner scan = new Scanner(System.in);

        System.out.println("¿Cuántos jugadores quieres agregar?");
        int numeroJugadores = scan.nextInt();

        for (int i = 0; i < numeroJugadores; i++) {
            System.out.println("Introduce el nombre del jugador " + (i + 1) + ":");
            nombresJugadores.add(scan.next());
        }
        System.out.println("\n");
        System.out.println("Los nombres de los jugadores son: " + nombresJugadores);
        System.out.println("\n");

        System.out.println(CYAN + "Con que panel quieres jugar?" + CYAN);
        System.out.print(RESET);
        int row = scan.nextInt();
        panel = new Panel(row);
        System.out.println("\n");

    }

    public void ordenAleatorio() {
        Random random = new Random();
        turno = random.nextInt(1, nombresJugadores.size());
        System.out.println("El primer Jugador es: " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("Gira la ruleta: " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("\n");
    }

    public void jugar() {

        Scanner sc = new Scanner(System.in);
        boolean seguirJugando = true;

        while (seguirJugando) {
            jugador.ruleta();
            seleccionarJugadaUsuario();
        }
    }

    public void incrementaTurno() {
        turno = (turno + 1) % nombresJugadores.size();
        System.out.println("Es el turno del jugador " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("Gira la ruleta: " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("\n");
    }

    public void seleccionarJugadaUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println(CYAN + "Elige que quieres hacer:\n\n" + CYAN + RESET
                + "1. Adivinar frase\n"
                + "2. Decir letra");
        System.out.println(RESET);
        String opcionElegida = sc.nextLine();

        switch (opcionElegida) {
            case "Adivinar frase":
                panel.adivinarFrase();
                break;
            case "Decir letra":
                if (!panel.decirLetra(jugador)) {
                    incrementaTurno();
                }

                break;
            default:
                System.out.println("La opcion es otra");
                break;
        }

    }

}

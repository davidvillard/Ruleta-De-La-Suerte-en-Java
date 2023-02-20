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
    public static int turno;
    private int ronda = 1;
    public static final ArrayList<String> nombresJugadores = new ArrayList<>();
    Jugador jugador = new Jugador();

    /**
     * Metodo en el que sacamos los nombres de jugadores, su orden y el panel con el que se va a jugar
     */
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

    /**
     * Metodo con el que sacamos el jugador que empieza primero
     */
    public void ordenAleatorio() {
        Random random = new Random();
        turno = random.nextInt(1, nombresJugadores.size());
        System.out.println("El primer Jugador es: " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("Gira la ruleta: " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("\n");
    }

    /**
     * Metodo jugar, contiene lo principal del juego
     */
    public void jugar() {

        Scanner sc = new Scanner(System.in);
        boolean seguirJugando = true;

        while (seguirJugando) {
            jugador.ruleta();
            seleccionarJugadaUsuario();
        }
    }

    /**
     * Metodo que incrementa el turno
     */
    public void incrementaTurno() {
        turno = (turno + 1) % nombresJugadores.size();
        System.out.println("Es el turno del jugador " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("Gira la ruleta: " + PURPLE + nombresJugadores.get(turno) + PURPLE);
        System.out.print(RESET);
        System.out.println("\n");
    }

    public void incrementaRonda() {
        Scanner scan = new Scanner(System.in);
        if (ronda <= 4) {
            ronda = (ronda + 1);
            System.out.println("");
            System.out.println(CYAN + "¡COMIENZA LA RONDA " + ronda + "!" + CYAN + RESET);
            System.out.println("\n");
            System.out.println(CYAN + "Con que panel quieres jugar?" + CYAN);
            System.out.print(RESET);
            int row = scan.nextInt();
            panel = new Panel(row);

        } else {
            System.out.println(CYAN + "¡HA TERMINADO EL JUEGO " + ronda + "!" + CYAN + RESET);
             System.exit(0); // Termina la ejecución del programa con éxito
        }

    }

    /**
     * Metodo que contiene las posibles selecciones que puede hacer el usuario
     */
    public void seleccionarJugadaUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println(CYAN + "Elige que quieres hacer:\n\n" + CYAN + RESET
                + "1. Adivinar frase\n"
                + "2. Decir letra");
        System.out.println(RESET);
        String opcionElegida = sc.nextLine();

        switch (opcionElegida) {
            case "Adivinar frase":
                if (!panel.adivinarFrase(jugador)) {
                    incrementaTurno();
                } else {
                    incrementaRonda();
                }
                break;
            case "Decir letra":
                int resultadoReturn = panel.decirLetra(jugador);
                switch (resultadoReturn) {
                    case 0:
                        incrementaTurno();
                        break;
                    case 2:
                        incrementaRonda();
                        break;
                }
                break;
            default:
                System.out.println("La opcion es otra");
                break;
        }

    }

}

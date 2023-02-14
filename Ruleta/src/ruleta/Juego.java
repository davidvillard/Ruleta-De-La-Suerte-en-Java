package ruleta;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    private static int turno;
    private static final ArrayList<String> nombresJugadores = new ArrayList<>();

    public static void jugar(){
        Scanner sc = new Scanner(System.in);
        boolean seguirJugando = true;
            
        while (seguirJugando) {            
            Jugador.ruleta();
            Juego.seleccionarJugadaUsuario();
        }
    }
    
    
    public static void seleccionarNumeroJugadoresYOrden() {

        Scanner scan = new Scanner(System.in);

        System.out.println("¿Cuántos jugadores quieres agregar?");
        int numeroJugadores = scan.nextInt();

        for (int i = 0; i < numeroJugadores; i++) {
            System.out.println("Introduce el nombre del jugador " + (i + 1) + ":");
            nombresJugadores.add(scan.next());
        }
        System.out.println("Los nombres de los jugadores son: " + nombresJugadores);
        System.out.println("\n");

    }

    public static void ordenAleatorio() {
        Random random = new Random();
        turno = random.nextInt(1, nombresJugadores.size());
        System.out.println("El primer Jugador es: " + nombresJugadores.get(turno));
        System.out.println("Gira la ruleta: " + nombresJugadores.get(turno));
        System.out.println("\n");
    }

    public static void incrementaTurno() {
        turno = (turno + 1) % nombresJugadores.size();
        System.out.println("Es el turno del jugador " + nombresJugadores.get(turno));
        System.out.println("Gira la ruleta: " + nombresJugadores.get(turno));
        System.out.println("\n");
    }
    
    public static void seleccionarJugadaUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige que quieres hacer:\n"
                + "Adivinar frase\n"
                + "Decir letra");
        String opcionElegida = sc.nextLine();
        System.out.println("Que panel quieres?");
        int row = sc.nextInt();
        Panel panel = new Panel(row);
        
        switch (opcionElegida) {
            case "Adivinar frase":               
                panel.adivinarFrase(row);
                break;
            case "Decir letra":
                panel.decirLetra(row);
                break;
            default:
                System.out.println("La opcion es otra");
                break;
        }

    }
    
}



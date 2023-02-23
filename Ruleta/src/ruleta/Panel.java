package ruleta;

import java.util.Scanner;

/**
 *
 * @author David
 */
public class Panel {

    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    public static final String RESET = "\033[0m";

    Juego juego = new Juego();
    private String fraseDescubierta;
    private int seleccionPanel;
    private String[][] panel = {
        {"pista", "ya no te preguntan si has sido bueno este ano"},
        {"pista", "en la que siempre habra barra libre de besos"},
        {"pista", "sin planes sin horario sin moviles"},
        {"pista", "y bailamos toda la noche, y bebemos sin parar"},
        {"pista", "como cocinas muy bien haz tu la cena de nochevieja"}
    };

    /**
     * Metodo panel que tapa el panel selccionado
     *
     * @param seleccionPanel panel que selecciona el usuario
     */
    public Panel(int seleccionPanel) {
        this.seleccionPanel = seleccionPanel;
        fraseDescubierta = panel[seleccionPanel - 1][1].replaceAll("[a-zA-Z]", "#");
    }

    /**
     * Metodo que comprueba si no es vocal
     *
     * @param letra que selecciona el usuario
     * @return
     */
    public boolean esConsonante(char letra) {
        final String VOCALES = "aeiouAEIOU";
        return !VOCALES.contains(Character.toString(letra));
    }

    /**
     *
     * @param jugador que decidio adivinar frase, cuando falla el turno cambia al otro jugador
     * @return devuelve true o false dependiendo de si acerto o fallo
     */
    public boolean adivinarFrase(Jugador jugador) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n");
        
        System.out.println(CYAN + "Que frase crees que es" + CYAN + RESET);
        String fraseAdivinada = sc.nextLine();

        String resultadoRuleta = jugador.getSeleccionRuleta();
        int valor = 0;
        int comodin = 0;

        if (resultadoRuleta.equals("Comodin")) {
            comodin++;
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else if (resultadoRuleta.equals("Pierde Turno")) {
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");

        } else if (resultadoRuleta.equals("Quiebra")) {
            jugador.reiniciarDinero();
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else if (resultadoRuleta.equals("1/2")) {
            jugador.dividirDinero();
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else if (resultadoRuleta.equals("x2")) {
            jugador.multiplicarDinero();
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else {
            valor = Integer.parseInt(resultadoRuleta);
        }

        if (panel[seleccionPanel - 1][1].equalsIgnoreCase(fraseAdivinada)) {
            System.out.println("\n");
            System.out.println(GREEN + "CORRECTO!" + GREEN + RESET);
            System.out.println("\n");
            jugador.sumarDinero(valor);
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
            return true;
        } else {
            System.out.println("\n");
            System.out.println(RED + "Lo siento! Has fallado" + RED + RESET);
            System.out.println("\n");
            return false;
        }
    }

    /**
     *
     * @param jugador que decidio adivinar frase, cuando falla el turno cambia al otro jugador
     * @return true si acerto y false si fallo
     */
    public int decirLetra(Jugador jugador) {
        Scanner sc = new Scanner(System.in);
        int contador = 0;
        char letraSeleccionada;
        do {
            System.out.println("\n");
            System.out.println(CYAN + "Que letra eliges?" + CYAN + RESET);
            letraSeleccionada = sc.next().charAt(0);
            System.out.println("\n");
        } while (contador != 0);

        String resultadoRuleta = jugador.getSeleccionRuleta();
        int valor = 0;
        int comodin = 0;

        if (resultadoRuleta.equals("Comodin")) {
            comodin++;
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else if (resultadoRuleta.equals("Pierde Turno")) {
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else if (resultadoRuleta.equals("Quiebra")) {
            jugador.reiniciarDinero();
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else if (resultadoRuleta.equals("1/2")) {
            jugador.dividirDinero();
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else if (resultadoRuleta.equals("x2")) {
            jugador.multiplicarDinero();
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
        } else {
            valor = Integer.parseInt(resultadoRuleta);
        }

        String solucion = panel[seleccionPanel - 1][1];
        String nuevaFraseDescubierta = "";
        for (int i = 0; i < solucion.length(); i++) {
            if (solucion.charAt(i) == letraSeleccionada) {
                contador++;
                nuevaFraseDescubierta += letraSeleccionada;
            } else {
                nuevaFraseDescubierta += fraseDescubierta.charAt(i);
            }
        }

        if (contador == 0) {
            System.out.println(RED + "Lo siento has fallado" + RED + RESET);
            return 0; // 0 si no aciertas la letra de la frase
        } else {
            fraseDescubierta = nuevaFraseDescubierta;
            jugador.sumarDinero(valor * contador);
            System.out.println("Enhorabuena!, has adivinado: " + contador);
            System.out.println("Dinero obtenido " + valor * contador);
            System.out.println(Juego.nombresJugadores.get(Juego.turno) + " tiene " + comodin + " comodines y " + jugador.getDinero() + "€");
            System.out.println("\n");
            System.out.println(CYAN + "PANEL:" + CYAN);
            System.out.println(fraseDescubierta);
            System.out.println("\n");
            if (fraseDescubierta.equalsIgnoreCase(solucion)) {
                System.out.println("\n");
                return 2; // 2 si adivinas la frase
            }
        }
        return 1; // 1 Si aciertas la letra de la frase

    }

}

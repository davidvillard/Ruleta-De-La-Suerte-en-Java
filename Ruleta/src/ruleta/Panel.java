package ruleta;

import java.util.Scanner;

/**
 *
 * @author David
 */
public class Panel {

    Jugador jugador = new Jugador(100);
    private String fraseDescubierta;

    private String[][] panel = {
        {"pista", "Ya no te preguntan si has sido bueno este ano"},
        {"pista", "En la que siempre habra barra libre de besos"},
        {"pista", "Sin planes sin horario sin moviles"},
        {"pista", "Y bailamos toda la noche, y bebemos sin parar"},
        {"pista", "Como cocinas muy bien haz tu la cena de nochevieja"}
    };

    public Panel(int row) {
        fraseDescubierta = panel[row - 1][1].replaceAll("[a-zA-Z]", "#");
    }

    public boolean esConsonante(char letra) {
        final String VOCALES = "aeiouAEIOU";
        return !VOCALES.contains(Character.toString(letra));
    }

    public void adivinarFrase(int seleccionPanel) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que frase crees que es");
        String fraseAdivinada = sc.nextLine();

        if (panel[seleccionPanel - 1][1].equalsIgnoreCase(fraseAdivinada)) {
            System.out.println("CORRECTO!");
        } else {
            System.out.println("Lo siento! Has fallado");
            Juego.incrementaTurno();
        }
    }

    public void decirLetra(int seleccionPanel) {
        Scanner sc = new Scanner(System.in);
        char letraSeleccionada;
        do {
            System.out.println("Que letra eliges?");
            letraSeleccionada = sc.next().charAt(0);
        } while (esConsonante(letraSeleccionada));

        int contador = 0;
        String resultadoRuleta = Jugador.ruleta();
        int valor = 0;
        int comodin = 0;

        if (resultadoRuleta.equals("Comodin")) {
            comodin++;
        } else if (resultadoRuleta.equals("Pierde Turno")) {
            Juego.incrementaTurno();
        }else if (resultadoRuleta.equals("Quiebra")) {
            jugador.reiniciarDinero();
            Juego.incrementaTurno();
        }else if (resultadoRuleta.equals("1/2")) {
           jugador.dividirDinero();
           Juego.incrementaTurno();
        } else if (resultadoRuleta.equals("x2")) {
            jugador.multiplicarDinero();
            Juego.incrementaTurno();
        }else{
            valor = Integer.parseInt(resultadoRuleta);
        }
        /*
        Capturar excepcion
        */
        String solucion = panel[seleccionPanel - 1][1];
        String nuevaFraseDescubierta = "";
        for (int i = 0; i < solucion.length(); i++) {
            if (solucion.charAt(i) == letraSeleccionada) {
                contador++;
                nuevaFraseDescubierta += letraSeleccionada;
            } else {
                nuevaFraseDescubierta += fraseDescubierta.charAt(i);
                /*
                Problemas con las consonantes
                */
            }
        }
        
        
        fraseDescubierta=nuevaFraseDescubierta;
        jugador.sumarDinero(valor * contador);
        System.out.println("Enhorabuena!, has adivinado: " + contador);
        System.out.println("Dinero obtenido " + valor * contador);
        System.out.println(fraseDescubierta);


    }

}

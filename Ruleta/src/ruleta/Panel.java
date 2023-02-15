package ruleta;

import java.util.Scanner;

/**
 *
 * @author David
 */
public class Panel {

    private String fraseDescubierta;
    private int seleccionPanel;
    private String[][] panel = {
        {"pista", "Ya no te preguntan si has sido bueno este ano"},
        {"pista", "En la que siempre habra barra libre de besos"},
        {"pista", "Sin planes sin horario sin moviles"},
        {"pista", "Y bailamos toda la noche, y bebemos sin parar"},
        {"pista", "Como cocinas muy bien haz tu la cena de nochevieja"}
    };

    public Panel(int seleccionPanel) {
        this.seleccionPanel = seleccionPanel;
        fraseDescubierta = panel[seleccionPanel - 1][1].replaceAll("[a-zA-Z]", "#");
    }

    public boolean esConsonante(char letra) {
        final String VOCALES = "aeiouAEIOU";
        return !VOCALES.contains(Character.toString(letra));
    }

    
    /*
    public boolean esVocal(char letra){
        final String Consonantes = "p, q, b, t, d, c, ch, j, k, g, f, v, s, z, m, n, ñ, l, x, r, w, e, y, B, C, D, F, G, H, J, K, L, M, N, Ñ, P, Q, R, S, T, V, X, Z, W, Y";
        return !Consonantes.contains(Character.toString(letra));
    }
    */
    
    /**
     * 
     * @return True si sigue jugando y false si incrementa turno
     */
    public boolean adivinarFrase() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que frase crees que es");
        String fraseAdivinada = sc.nextLine();

        if (panel[seleccionPanel - 1][1].equalsIgnoreCase(fraseAdivinada)) {
            System.out.println("CORRECTO!");
            return true;
        } else {
            System.out.println("Lo siento! Has fallado");
            return false;
        }
    }

    /**
     * 
     * @return True si sigue jugando y false si incrementa turno
     */
    public boolean decirLetra(Jugador jugador) {
        boolean resultado = true;
        Scanner sc = new Scanner(System.in);
        int contador = 0;
        char letraSeleccionada;
        do {
            System.out.println("Que letra eliges?");
            letraSeleccionada = sc.next().charAt(0);
        } while (contador!=0); //Problema de las consonantes

        String resultadoRuleta = jugador.getSeleccionRuleta();
        int valor = 0;
        int comodin = 0;

        if (resultadoRuleta.equals("Comodin")) {
            comodin++;
        } else if (resultadoRuleta.equals("Pierde Turno")) {
            resultado = false;
        } else if (resultadoRuleta.equals("Quiebra")) {
            jugador.reiniciarDinero();
            resultado = false;
        } else if (resultadoRuleta.equals("1/2")) {
            jugador.dividirDinero();
            resultado = false;
        } else if (resultadoRuleta.equals("x2")) {
            jugador.multiplicarDinero();
            resultado = false;
        } else {
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
            }
        }

        if (contador == 0) {
            System.out.println("Lo siento has fallado");
            fraseDescubierta = solucion;
            resultado = false;
        }else{
            fraseDescubierta = nuevaFraseDescubierta;
        jugador.sumarDinero(valor * contador);
        System.out.println("Enhorabuena!, has adivinado: " + contador);
        System.out.println("Dinero obtenido " + valor * contador);
        System.out.println(fraseDescubierta);

        }
        return resultado;
    }

}

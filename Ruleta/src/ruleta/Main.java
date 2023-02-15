package ruleta;

public class Main {

    public static void main(String[] args) {
        /*
        ANSI
        */
        String BLACK = "\033[0;30m";
        String RED = "\033[0;31m";
        String GREEN = "\033[0;32m";
        String YELLOW = "\033[0;33m";
        String BLUE = "\033[0;34m";
        String PURPLE = "\033[0;35m";
        String CYAN = "\033[0;36m";
        String WHITE = "\033[0;37m";
        String RESET = "\033[0m";
 
        
        System.out.print(RED);
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Imprimir el mensaje en blanco
        System.out.print(WHITE);
        System.out.println("           ¡BIENVENIDO A LA RULETA DE LA SUERTE!     ");

        // Imprimir bordes rojos
        System.out.print(RED);
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        
        // Restablecer el color y el tamaño de la salida de la consola
        System.out.print(RESET);
        
        
        Juego juego = new Juego();
        juego.seleccionarNumeroJugadoresYOrden();
        juego.ordenAleatorio();
        juego.jugar();
        
        /*
        No se guarda el panel, si digo la "a" y despues la "e" el panel no guarda la letra anterior que habias dicho
        */
        /*
        Necesito que si falla la frase o una letra, que se incremente turno y pasemos al siguiente jugador
        */
        /*
        Hay fallos con las consonantes y con las letras que no existen en la frase
        cada vez que ponemos una de estas opciones pregunta de nuevo qu letra eliges
        */
    }
}
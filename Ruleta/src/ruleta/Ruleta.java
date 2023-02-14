package ruleta;

public class Ruleta {

    public static void main(String[] args) {
        Juego.seleccionarNumeroJugadoresYOrden();
        Juego.ordenAleatorio();
        Juego.jugar();
        
        /*
        No se guarda el panel, si digo la "a" y despues la "e" el panel no guarda la letra anterior que habias dicho
        */
        /*
        Necesito que si falla la frase o una letra que se incremente turno y pasemos al siguiente jugador
        */
        /*
        Hay fallos con las consonantes y con las letras que no existen en la frase
        cada vez que ponemos una de estas opciones pregunta de nuevo qu letra eliges
        */
    }
}
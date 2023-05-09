package torneotenis2;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author gaspy es este!
 */
public class Main {

    public static void main(String[] args) {
        int cantidadSets=0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("****BIENVENIDO AL SIMULADOR DE TENIS*****");

        System.out.print("Ingrese el nombre del torneo: ");
        String nombreTorneo = scanner.nextLine();
        
        System.out.print("Ingrese el nombre del jugador 1: ");
        String nombreJugador1 = scanner.nextLine();
        
        System.out.print("Ingrese el nombre del jugador 2: ");
        String nombreJugador2 = scanner.nextLine();
        
        while (cantidadSets != 3 && cantidadSets != 5) {
            System.out.print("Ingrese la cantidad de sets (3 o 5): ");
            cantidadSets = scanner.nextInt();

            if (cantidadSets != 3 && cantidadSets != 5) {
                System.out.println("El valor ingresado no es válido. Debe ser 3 o 5.");
            }
        }
        
        /*Le pedimos al usuario que ingrese la probabilidad de ganar del jugador 1 
        y le avisamos que estara relacionado con la probabilidad del segundo jugador(100-probabilidad del 1).            
        */
        System.out.print("Ingrese la probabilidad de " + nombreJugador1 + " de ganar el partido \n (Esto afectara a la probabilidad del segundo jugador): ");
        int probabilidadJugador1 = scanner.nextInt();
        
        //La probabilidad del segundo jugador es el complemento del primero para llegar al 100%
        int probabilidadJugador2 = 100 - probabilidadJugador1;

        // Creamos los jugadores y el partido
        Jugador jugador1 = new Jugador(nombreJugador1, probabilidadJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2, probabilidadJugador2);
        Partido partido = new Partido(nombreTorneo,jugador1,jugador2,cantidadSets,probabilidadJugador1);
        
        partido.jugarPartido(jugador1, jugador2);

        // Preguntar si se quiere jugarPartido una revancha
        System.out.print("\n¿Desea jugar la revancha? (si/no): ");
        String respuesta = scanner.next();
        if (respuesta.equalsIgnoreCase("si")) {
            // Crear un nuevo partido y jugarlo
            Partido revancha = new Partido(nombreTorneo,jugador1,jugador2,cantidadSets,probabilidadJugador1);
            revancha.jugarPartido(jugador1, jugador2);
        }
    }
}
    

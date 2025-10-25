import java.util.Scanner;
public class PasaLaCalculadora{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // VARIABLES BÁSICAS
        int numeroJugadores;
        String jugador1 = "";
        String jugador2 = "";
        String jugador3 = "";
        int objetivoMaximo;
        int sumaActual;
        int numeroPrevio;
        int numeroIntroducido;
        boolean finPartida = false;
        boolean seguirJugando = true;

        // PRESENTACIÓN DEL JUEGO
        System.out.println("=====================================");
        System.out.println("      JUEGO: PASA LA CALCULADORA     ");
        System.out.println("=====================================");
        mostrarInstrucciones();

        // PEDIR NÚMERO DE JUGADORES
        numeroJugadores = obtenerNumeroJugadores(sc);

        // PEDIR NOMBRES
        if (numeroJugadores == 1) {
            System.out.print("Introduce el nombre del jugador 1: ");
            jugador1 = sc.nextLine();
        } else if (numeroJugadores == 2) {
            System.out.print("Introduce el nombre del jugador 1: ");
            jugador1 = sc.nextLine();
            System.out.print("Introduce el nombre del jugador 2: ");
            jugador2 = sc.nextLine();
        } else {
            System.out.print("Introduce el nombre del jugador 1: ");
            jugador1 = sc.nextLine();
            System.out.print("Introduce el nombre del jugador 2: ");
            jugador2 = sc.nextLine();
            System.out.print("Introduce el nombre del jugador 3: ");
            jugador3 = sc.nextLine();
        }

        // BUCLE PRINCIPAL
        while (seguirJugando) {

            objetivoMaximo = obtenerObjetivoMaximo(sc);
            sumaActual = 0;
            numeroPrevio = 0;
            finPartida = false;

            System.out.println("\nObjetivo máximo: " + objetivoMaximo);
            System.out.println("¡Comienza la partida!\n");

            // BUCLE DE JUEGO
            while (!finPartida) {

                // Turno del jugador 1
                System.out.println("Turno de " + jugador1);
                numeroIntroducido = validarNumero(sc, numeroPrevio);
                numeroPrevio = numeroIntroducido;
                sumaActual = sumaActual + numeroIntroducido;
                System.out.println("Suma actual: " + sumaActual);

                if (sumaActual >= objetivoMaximo) {
                    System.out.println("¡" + jugador1 + " ha perdido!");
                    finPartida = true;
                    break;
                }

                // Turno del jugador 2
                if (numeroJugadores >= 2 && !finPartida) {
                    System.out.println("Turno de " + jugador2);
                    numeroIntroducido = validarNumero(sc, numeroPrevio);
                    numeroPrevio = numeroIntroducido;
                    sumaActual = sumaActual + numeroIntroducido;
                    System.out.println("Suma actual: " + sumaActual);

                    if (sumaActual >= objetivoMaximo) {
                        System.out.println("¡" + jugador2 + " ha perdido!");
                        finPartida = true;
                        break;
                    }
                }

                // Turno del jugador 3
                if (numeroJugadores == 3 && !finPartida) {
                    System.out.println("Turno de " + jugador3);
                    numeroIntroducido = validarNumero(sc, numeroPrevio);
                    numeroPrevio = numeroIntroducido;
                    sumaActual = sumaActual + numeroIntroducido;
                    System.out.println("Suma actual: " + sumaActual);

                    if (sumaActual >= objetivoMaximo) {
                        System.out.println("¡" + jugador3 + " ha perdido!");
                        finPartida = true;
                        break;
                    }
                }

                System.out.println("----------------------------------");
            }

            // Preguntar si quieren jugar otra partida
            System.out.print("¿Quieres jugar otra partida? (s/n): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("n")) {
                seguirJugando = false;
                System.out.println("Gracias por jugar. ¡Hasta pronto!");
            } else {
                System.out.println("\n--- Nueva partida ---\n");
            }
        }

        // No cerramos el Scanner (forma básica)
    }

    // ===== FUNCIONES =====

    public static void mostrarInstrucciones() {
        System.out.println("Reglas del juego:");
        System.out.println("1. Cada jugador elige un número del 1 al 9.");
        System.out.println("2. El nuevo número debe estar en la misma fila o columna que el anterior.");
        System.out.println("3. Si la suma total alcanza o supera el objetivo, el jugador que lo hace pierde.\n");
    }

    public static int obtenerNumeroJugadores(Scanner sc) {
        int jugadores = 0;
        while (jugadores < 1 || jugadores > 3) {
            System.out.print("¿Cuántos jugadores participarán? (1-3): ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada no válida. Escribe un número.");
                sc.next();
            }
            jugadores = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea
            if (jugadores < 1 || jugadores > 3) {
                System.out.println("Debe estar entre 1 y 3.");
            }
        }
        return jugadores;
    }

    public static int obtenerObjetivoMaximo(Scanner sc) {
        int objetivo = 0;
        while (objetivo < 10 || objetivo > 99) {
            System.out.print("Introduce el número objetivo (10-99): ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada no válida.");
                sc.next();
            }
            objetivo = sc.nextInt();
            sc.nextLine();
            if (objetivo < 11 || objetivo > 98) {
                System.out.println("Debe ser entre 11 y 98.");
            }
        }
        return objetivo;
    }

    public static int validarNumero(Scanner sc, int numeroPrevio) {
        int num = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Introduce un número del 1 al 9: ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada no válida.");
                sc.next();
            }
            num = sc.nextInt();
            sc.nextLine();

            if (num < 1 || num > 9) {
                System.out.println("Número fuera de rango.");
            } else if (numeroPrevio == 0) {
                valido = true;
            } else if (num == numeroPrevio) {
                System.out.println("No puedes repetir el mismo número.");
            } else if (mismaFila(numeroPrevio, num) || mismaColumna(numeroPrevio, num)) {
                valido = true;
            } else {
                System.out.println("Debe estar en la misma fila o columna que el número anterior.");
            }
        }
        return num;
    }

    public static boolean mismaFila(int a, int b) {
        // Sin arrays, usando comparaciones simples
        if ((a >= 1 && a <= 3 && b >= 1 && b <= 3) ||
                (a >= 4 && a <= 6 && b >= 4 && b <= 6) ||
                (a >= 7 && a <= 9 && b >= 7 && b <= 9)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mismaColumna(int a, int b) {

        if ((a == 1 || a == 4 || a == 7) && (b == 1 || b == 4 || b == 7)) return true;
        if ((a == 2 || a == 5 || a == 8) && (b == 2 || b == 5 || b == 8)) return true;
        if ((a == 3 || a == 6 || a == 9) && (b == 3 || b == 6 || b == 9)) return true;
        return false;
    }
}

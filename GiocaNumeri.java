package giocanumeri;

import java.util.Scanner;

/**
 * Avvia il gioco con due giocatori e aspetta la loro conclusione.
 */
public class GiocaNumeri {

    /**
     * Legge un numero positivo, ripetendo la richiesta se non è valido.
     *
     * @param input scanner per leggere dalla tastiera
     * @return numero positivo inserito
     */
    private static int leggiNumero(Scanner input) {
        while (true) {
            System.out.print("Inserisci un numero intero positivo: ");

            try {
                int numero = Integer.parseInt(input.nextLine().trim());

                if (numero > 0) {
                    return numero;
                }

                System.err.println("Errore: il numero deve essere maggiore di zero.");

            } catch (NumberFormatException e) {
                System.err.println("Errore: inserisci un numero intero valido.");
            }
        }
    }

    /**
     * Legge una parola senza spazi.
     *
     * @param input scanner per leggere dalla tastiera
     * @return parola inserita
     */
    private static String leggiParola(Scanner input) {
        while (true) {
            System.out.print("Inserisci una parola: ");
            String parola = input.nextLine().trim();

            if (!parola.isEmpty() && !parola.matches(".*\\s+.*")) {
                return parola;
            }

            System.err.println("Errore: inserisci una sola parola non vuota.");
        }
    }

    /**
     * Raccoglie i dati e avvia i thread dei giocatori.
     *
     * @param args argomenti della riga di comando
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("INIZIO GIOCO");

        System.out.println("\nGiocatore Monica");
        int numero1 = leggiNumero(input);
        String parola1 = leggiParola(input);

        System.out.println("\nGiocatore Paolo");
        int numero2 = leggiNumero(input);
        String parola2 = leggiParola(input);

        input.close();

        Giocatore g1 = new Giocatore("Monica", numero1, parola1);
        Giocatore g2 = new Giocatore("Paolo", numero2, parola2);

        try {
            g1.start();

            // Sospensione del main dopo l'avvio del primo thread.
            Thread.sleep(5000);

            g2.start();

            // Il main aspetta che entrambi i giocatori finiscano.
            g1.join();
            g2.join();

            System.out.println("\nRISULTATI");
            System.out.println("Monica: " + g1.getPunteggio() + " punti");
            System.out.println("Paolo: " + g2.getPunteggio() + " punti");

            if (g1.getPunteggio() > g2.getPunteggio()) {
                System.out.println("Vince Monica!");
            } else if (g2.getPunteggio() > g1.getPunteggio()) {
                System.out.println("Vince Paolo!");
            } else {
                System.out.println("Pareggio!");
            }

            System.out.println("FINE GIOCO");

        } catch (InterruptedException e) {
            System.err.println("Errore: il thread principale è stato interrotto.");

            g1.interrupt();
            g2.interrupt();

            Thread.currentThread().interrupt();
        }
    }
}

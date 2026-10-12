package giocanumeri;

import java.util.Locale;

/**
 * Rappresenta un giocatore che esegue il conteggio in un thread.
 */
public class Giocatore extends Thread {

    private final String nome;
    private final int numero;
    private final String parola;
    private long punteggio;

    /**
     * Crea un giocatore.
     *
     * @param nome nome del giocatore
     * @param numero numero fino al quale contare
     * @param parola parola da mostrare in maiuscolo
     */
    public Giocatore(String nome, int numero, String parola) {
        super(nome);
        this.nome = nome;
        this.numero = numero;
        this.parola = parola;
        this.punteggio = 0;
    }

    /**
     * Restituisce il punteggio ottenuto.
     *
     * @return punteggio del giocatore
     */
    public long getPunteggio() {
        return punteggio;
    }

    /**
     * Esegue il conteggio e calcola il punteggio.
     *
     * @throws InterruptedException se il thread viene interrotto
     */
    public void gioca() throws InterruptedException {
        for (long i = 1; i <= numero; i++) {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }

            System.out.println("Giocatore: " + nome + " - conteggio: " + i);

            // Pausa di 2 secondi dopo ogni stampa del conteggio.
            Thread.sleep(2000);

            // Suggerisce allo scheduler di far eseguire un altro thread.
            Thread.yield();
        }

        punteggio = (long) numero * parola.length();
        comunica();
    }

    /**
     * Mostra parola in maiuscolo, numero scelto e punteggio.
     */
    public void comunica() {
        System.out.println(
            "Giocatore: " + nome
            + " - parola: " + parola.toUpperCase(Locale.ROOT)
            + " - numero: " + numero
            + " - punteggio: " + punteggio
        );
    }

    /**
     * Metodo eseguito quando viene chiamato start().
     */
    @Override
    public void run() {
        try {
            gioca();

        } catch (InterruptedException e) {
            System.err.println("Errore: il gioco di " + nome + " è stato interrotto.");
            Thread.currentThread().interrupt();
        }
    }
}

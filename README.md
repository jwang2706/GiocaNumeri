# GiocaNumeri

## Comportamento
Il programma richiede un numero intero positivo e una parola per ciascuno
dei due giocatori, Monica e Paolo.

Ogni giocatore conta da 1 al numero scelto, facendo una pausa di 2 secondi
dopo ogni stampa. Alla fine mostra la parola in maiuscolo, il numero
scelto e il punteggio.

Il punteggio è: numero scelto × lunghezza della parola.
Vince chi ottiene più punti. In caso di punteggi uguali si ha un pareggio.

## Classi
- GiocaNumeri: legge i dati, avvia i thread e mostra il vincitore.
- Giocatore: estende Thread ed esegue il gioco nel metodo run().

## Metodi di Thread utilizzati
- start(): avvia un nuovo thread, che esegue run().
- sleep(): sospende il thread corrente per il tempo indicato.
- join(): permette al main di aspettare la conclusione dei giocatori.
- yield(): suggerisce di lasciare eseguire un altro thread.
  Non garantisce un'alternanza precisa.
- interrupt(): richiede l'interruzione di un thread.
- isInterrupted(): controlla se è stata richiesta un'interruzione.

Il main aspetta 5 secondi dopo l'avvio di Monica e poi avvia Paolo.
Se Monica sta ancora giocando, i due thread procedono in concorrenza.
Il main mostra i risultati dopo la conclusione di entrambi.

## Eccezioni
- NumberFormatException: gestisce un numero non valido o fuori
  dall'intervallo dei valori int.
- InterruptedException: gestisce l'interruzione durante sleep() e join().

I messaggi di errore utilizzano System.err.

## Thread safety
Ogni giocatore possiede i propri dati.
Lo Scanner viene usato soltanto dal main prima dell'avvio dei thread.
Il main legge i punteggi dopo join(), quando i giocatori hanno terminato.
Non vengono usate variabili statiche modificabili condivise.

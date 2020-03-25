/* ---------------------------
Laboratoire : 03
Fichier :     Controleur.java
Auteur(s) :   Alexis Allemann, Alexandre Mottier
Date :        12.03.2020 - 25.03.2020

But : Classe de gestion des accès aux documents

Compilateur : javac 11.0.4
--------------------------- */
package ch.heigvd.aalamo.lecteursredacteurs;

import java.util.HashSet;

public class Controleur {
    // Etats du document
    public enum State {READ, WRITE, AVAILABLE}

    private State currentState = State.AVAILABLE;
    public HashSet<Lecteur> readers = new HashSet<>();
    public HashSet<Lecteur> waitingReaders = new HashSet<>();
    public HashSet<Redacteur> waitingWriters = new HashSet<>();
    public Redacteur currentWriter;

    /**
     * Le lecteur donné souhaite commencer de lire le document
     * @param reader qui commence de lire
     */
    public void readDocument(Lecteur reader) {
        synchronized (this) {
            while (!waitingWriters.isEmpty() || currentState.equals(State.WRITE)) {
                try {
                    if (!readers.contains(reader))
                        waitingReaders.add(reader);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            waitingReaders.remove(reader);
            readers.add(reader);

            if (currentState != State.READ)
                currentState = State.READ;
        }
    }

    /**
     * Le rédacteur donné souhaite commencer l'écriture du document
     * @param writer qui arrête d'écrire
     */
    public void writeDocument(Redacteur writer) {
        synchronized (this) {
            while (!currentState.equals(State.AVAILABLE)) {
                try {
                    if (!(currentWriter == writer))
                        waitingWriters.add(writer);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            waitingWriters.remove(writer);
            currentWriter = writer;

            currentState = State.WRITE;
        }
    }

    /**
     * Le lecteur donné arrête de lire le document
     * @param reader qui arrête de lire
     */
    public void stopRead(Lecteur reader) {
        synchronized (this) {
            readers.remove(reader);
            if (readers.isEmpty()) {
                currentState = State.AVAILABLE;
                notifyAll();
            }
        }
    }

    /**
     * Le rédacteur donné arrête l'écriture du document
     * @param writer qui arrête d'écrire
     */
    public void stopWrite(Redacteur writer) {
        synchronized (this) {
            currentWriter = null;
            currentState = State.AVAILABLE;
            notifyAll();
        }
    }

    /**
     * Savoir si la personne donnée en paramètre attend de lire/écrire le document
     * @param person à analyser
     * @return si la personne donnée en paramètre attend de lire/écrire le document
     */
    public boolean isWaiting(Object person) {
        synchronized (this) {
            if (person instanceof Lecteur)
                return waitingReaders.contains(person);
            return waitingWriters.contains(person);
        }
    }
}

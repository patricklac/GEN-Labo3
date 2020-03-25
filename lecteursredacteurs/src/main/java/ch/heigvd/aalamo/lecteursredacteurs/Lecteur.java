/* ---------------------------
Laboratoire : 03
Fichier :     Lecteur.java
Auteur(s) :   Alexis Allemann, Alexandre Mottier
Date :        12.03.2020 - 25.03.2020

But : Classe représentant un lecteur

Compilateur : javac 11.0.4
--------------------------- */
package ch.heigvd.aalamo.lecteursredacteurs;

public class Lecteur implements Runnable {
    private Controleur controleur;

    /**
     * Instanciation d'un lecteur
     *
     * @param controleur de gestion des accès
     */
    public Lecteur(Controleur controleur) {
        if (controleur == null)
            throw new RuntimeException("Contrôleur nul");
        this.controleur = controleur;
    }

    /**
     * Débuter la lecture d'un document
     */
    public void startRead() throws InterruptedException {
        synchronized (this){
            new Thread(this).start();
            Thread.sleep(1); // Nécessaire pour éviter que isWaiting soit appelé avant le run du thread
        }
    }

    /**
     * Savoir si le lecteur est en file d'attente pour lire le document
     *
     * @return si le lecteur est en file d'attente pour lire le document
     */
    public boolean isWaiting() {
        return controleur.isWaiting(this);
    }

    /**
     * Arrêter la lecture d'un document
     */
    public void stopRead() throws InterruptedException {
        synchronized (this) {
            controleur.stopRead(this);
            Thread.sleep(1); // Nécessaire pour éviter que isWaiting soit appelé avant le run du thread
        }
    }

    /**
     * Méthode d'exécution du thread
     */
    @Override
    public void run() {
        controleur.readDocument(this);
    }
}

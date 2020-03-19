/* ---------------------------
Laboratoire : 03
Fichier :     Lecteur.java
Auteur(s) :   Alexis Allemann, Alexandre Mottier
Date :        12.03.2020 - 25.03.2020

But : Classe représentant un lecteur

Compilateur : javac 11.0.4
--------------------------- */
package ch.heigvd.aalamo.lecteursredacteurs;

public class Lecteur implements Runnable{
    private Controleur controleur;

    /**
     * Instanciation d'un lecteur
     * @param controleur de gestion des accès
     */
    public Lecteur(Controleur controleur) {
        if(controleur == null)
            throw new RuntimeException("Contrôleur nul");
        this.controleur = controleur;
    }

    /**
     * Débuter la lecture d'un document
     */
    public void startRead() {
    }

    /**
     * Savoir si le lecteur est en file d'attente pour lire le document
     * @return si le lecteur est en file d'attente pour lire le document
     */
    public boolean isWaiting() {
    }

    /**
     * Arrêter la lecture d'un document
     */
    public void stopRead() {
    }

    /**
     * Méthode d'exécution du thread
     */
    @Override
    public void run() {

    }
}

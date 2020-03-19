/* ---------------------------
Laboratoire : 03
Fichier :     Redacteur.java
Auteur(s) :   Alexis Allemann, Alexandre Mottier
Date :        12.03.2020 - 25.03.2020

But : Classe représentant un rédacteur

Compilateur : javac 11.0.4
--------------------------- */
package ch.heigvd.aalamo.lecteursredacteurs;

public class Redacteur implements Runnable{
    private Controleur controleur;

    /**
     * Instanciation d'un rédacteur
     * @param controleur de gestion des accès
     */
    public Redacteur(Controleur controleur) {
        if(controleur == null)
            throw new RuntimeException("Contrôleur nul");
        this.controleur = controleur;
    }

    /**
     * Débuter d'écrir un document
     */
    public void startWrite() {
    }

    /**
     * Savoir si le rédacteur est en file d'attente pour écrire le document
     * @return si le rédacteur est en file d'attente pour écrire le document
     */
    public boolean isWaiting() {
    }

    /**
     * Dire au rédacteur d'arrêter l'écriture du document
     */
    public void stopWrite() {
    }

    /**
     * Méthode d'exécution du thread
     */
    @Override
    public void run() {

    }
}

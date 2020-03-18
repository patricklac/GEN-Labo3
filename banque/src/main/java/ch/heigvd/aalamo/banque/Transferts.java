/* ---------------------------
Laboratoire : 03
Fichier :     Transferts.java
Auteur(s) :   Alexis Allemann, Alexandre Mottier
Date :        12.03.2020 - 25.03.2020

But : Classe repésentant des transferts entre comptes d'une banque

Compilateur : javac 11.0.4
--------------------------- */
package ch.heigvd.aalamo.banque;

import java.util.Random;

public class Transferts extends Thread {
    private Banque banque;

    /**
     * Instanciation de transferts
     * @param banque sur laquelle les transferts ont lieu
     */
    public Transferts(Banque banque) {
        // Validation des paramètres
        if(banque == null)
            throw new RuntimeException("La banque passée est nulle !");

        this.banque = banque;
    }

    /**
     * Exécution de 1000 transferts aléatoires entre les compes de la banque
     */
    @Override
    public void run() {
        Random random = new Random();
        for(int i = 0; i < 1000; ++i)
            banque.transfert(random.nextInt(banque.getNbComptes()), random.nextInt(banque.getNbComptes()), random.nextInt(5));
    }
}

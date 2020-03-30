/* ---------------------------
Laboratoire : 03
Fichier :     TransfertTest.java
Auteur(s) :   Alexis Allemann, Alexandre Mottier
Date :        12.03.2020 - 25.03.2020

But : Tests unitaires de la classe Transfert

Compilateur : javac 11.0.4
--------------------------- */
package ch.heigvd.aalamo.banque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransfertsTest {
    private Banque banque;
    private Random random;

    /**
     * Préparation de la banque et des comptes avant les tests unitaires
     */
    @BeforeEach
    public void setup() {
        banque = new Banque(10);
        random = new Random();
    }

    /**
     * Test de la bonne exécution des threads afin de ne pas créer ni perdre de l'argent
     * @throws InterruptedException si une interruption à lieu
     */
    @Test
    public void threadTest() throws InterruptedException {
        // Création de la liste de transfertss
        List<Transferts> transferts = new ArrayList<>();

        // Ajout des 1000 transferts et démarrage de leurs threads
        for(int i = 0; i < 1000; ++i){
            transferts.add(new Transferts(banque));
            transferts.get(i).start();
        }

        // Attente de la fin des threads (obligatoire avant de calculer le solde)
        for(Transferts t : transferts)
            t.join();

        // Vérification que le montant total des comptes = montant de base
        assertTrue(banque.consistent());
    }
}
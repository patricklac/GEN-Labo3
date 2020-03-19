/* ---------------------------
Laboratoire : 03
Fichier :     LecteursRedacteursTest.java
Auteur(s) :   Alexis Allemann, Alexandre Mottier
Date :        12.03.2020 - 25.03.2020

But : Tests unitaires de la classe LecteursRedacteurs

Compilateur : javac 11.0.4
--------------------------- */
package ch.heigvd.aalamo.lecteursredacteurs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LecteursRedacteursTest {
    private Controleur controleur;
    private Lecteur lecteur1;
    private Lecteur lecteur2;
    private Lecteur lecteur3;
    private Redacteur redacteur1;
    private Redacteur redacteur2;

    /**
     * Méthode exécutée avant toute exécution de tests pour préparer les données
     * @throws Exception exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        controleur = new Controleur();
        lecteur1 = new Lecteur(controleur);
        lecteur2 = new Lecteur(controleur);
        lecteur3 = new Lecteur(controleur);
        redacteur1 = new Redacteur(controleur);
        redacteur2 = new Redacteur(controleur);
    }

    /**
     * Méthode de tests des classes lecteurs et redacteurs avec accès parallèle aux documents
     * @throws InterruptedException
     */
    @Test
    public void lecteursRedacteurs() throws InterruptedException {
        lecteur1.startRead();
        lecteur2.startRead();
        redacteur1.startWrite();
        lecteur3.startRead();

        // lecteurs 1 et 2 passent
        // puis redacteur1 attends et donc lecteur3 aussi
        assertTrue(redacteur1.isWaiting());
        assertTrue(lecteur3.isWaiting());
        assertFalse(lecteur1.isWaiting());
        lecteur1.stopRead();
        assertFalse(lecteur2.isWaiting());
        lecteur2.stopRead();

        // Après lecteurs 1 et 2, c'est à redacteur1
        assertTrue(lecteur3.isWaiting());
        assertFalse(redacteur1.isWaiting());
        redacteur2.startWrite();
        redacteur1.stopWrite();

        // redacteur 1 libère mais redacteur 2 passe avant lecteur 3
        assertTrue(lecteur3.isWaiting());
        assertFalse(redacteur2.isWaiting());
        redacteur2.stopWrite();

        // après les redacteurs , lecteur3 est libéré
        assertFalse(lecteur3.isWaiting());
        lecteur3.stopRead();
    }
}
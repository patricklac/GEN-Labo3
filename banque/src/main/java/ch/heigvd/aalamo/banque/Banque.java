/*
 * ---------------------------
 * Laboratoire : 03
 * Fichier :     Banque.java
 * Auteur(s) :   Alexis Allemann, Alexandre Mottier
 * Date :        12.03.2020 - 25.03.2020
 * <p>
 * But : Classe représentant une banque
 * <p>
 * Compilateur : javac 11.0.4
 * ---------------------------
 */
package ch.heigvd.aalamo.banque;

import java.util.ArrayList;
import java.util.List;

public class Banque {
    private List<Compte> comptes;
    private final int creditInitial = 5;

    /**
     * Instanciation d'une banque
     * @param nbComptes nombre de comptes contenus dans la banque
     */
    public Banque(int nbComptes) {
        comptes = new ArrayList<>();
        for (int i = 0; i < nbComptes; i++) {
            comptes.add(new Compte(i, creditInitial));
        }
    }

    /**
     * Obtenir le nombre de comptes de la banque
     * @return le nombre de comptes de la banque
     */
    public int getNbComptes() {
        return comptes.size();
    }

    /**
     * Test de la cohérance de la somme totale de l'argent des comptes
     * @return si la sommes des montants des comptes est cohérante ou non
     */
    public boolean consistent() {
        int total = 0;
        for (int i = 0; i < getNbComptes(); i++) {
            total += comptes.get(i).getMontant();
        }
        return total == creditInitial * getNbComptes();
    }

    /**
     * Réalisation d'un transfert d'argent entre comptes de la banque
     * @param debiteur qui effectue le transfert
     * @param crediteur qui reçoit l'argent du transfert
     * @param montant valeur du transfert
     */
    public void transfert(int debiteur, int crediteur, int montant) {
        if (comptes.get(debiteur).debit(montant)) {
            comptes.get(crediteur).credit(montant);
        }
    }
}

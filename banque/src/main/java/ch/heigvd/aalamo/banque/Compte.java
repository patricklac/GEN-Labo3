/*
 * ---------------------------
 * Laboratoire : 03
 * Fichier :     Compte.java
 * Auteur(s) :   Alexis Allemann, Alexandre Mottier
 * Date :        12.03.2020 - 25.03.2020
 * <p>
 * But : Classe représentant un compte d'une banque
 * <p>
 * Compilateur : javac 11.0.4
 * ---------------------------
 */
package ch.heigvd.aalamo.banque;

public class Compte {
    private int numero;
    private int montant;

    /**
     * Instanciation d'un compte bancaire
     * @param numero du compte
     * @param montant initial contenu
     */
    public Compte(int numero, int montant) {
        this.numero = numero;
        this.montant = montant;
    }

    /**
     * Obtenir le numéro du compte
     * @return le numéro du compte
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtenir le montant du compte
     * @return le montant du compte
     */
    public int getMontant() {
        return montant;
    }

    /**
     * Effectuer un débit sur le compte
     * @param valeur montant du débit
     * @return si le débit est possible ou non
     */
    public boolean debit(int valeur) {
        synchronized (this) {
            if (montant - valeur >= 0) {
                this.montant -= valeur;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Effectuer un crédit sur le compte
     * @param valeur montant du crédit
     */
    public void credit(int valeur) {
        synchronized (this) {
            this.montant += valeur;
        }
    }
}
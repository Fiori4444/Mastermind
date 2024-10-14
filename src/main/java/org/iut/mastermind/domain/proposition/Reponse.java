package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return this.resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        for (position = 0; position < essai.length(); position++) {
            char lettreEssai = essai.charAt(position);
            Lettre statut = evaluationCaractere(lettreEssai);
            resultat.add(statut);
        }
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        boolean res = true;
        for (Lettre lettre : resultat) {
            if (lettre != Lettre.PLACEE) {
                res = false;
                break;
            }
        }
        return res;
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant) {
        Lettre lettre;
        if (estPlace(carCourant)) {
            lettre = Lettre.PLACEE;
        } else if (estPresent(carCourant)) {
            lettre = Lettre.NON_PLACEE;
        } else {
            lettre = Lettre.INCORRECTE;
        }
        return lettre;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        int res = this.motSecret.indexOf(carCourant);
        return res != -1;
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant) {
        return motSecret.charAt(position) == carCourant;
    }
}

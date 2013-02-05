package element.joueur;

import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Joueur_Bleu.java<br>
 	Bonhomme bomber bleu
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

/** Création d'un joueur bleu à une position donnée en cases */
public class Joueur_Bleu extends Joueur {	

    	public Joueur_Bleu(int case_x, int case_y) {
    		super(case_x, case_y, ZoneJeu.I_JOUEURS+4+16);
        }//Joueur_Bleu()

};//classe Joueur_Bleu
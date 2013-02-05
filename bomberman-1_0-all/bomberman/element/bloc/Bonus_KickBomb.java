package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_KickBomb.java<br>
 	Bloc Bonus "KickBomb"<br>
 	Effet: permet de pousser (faire glisser) une bombe
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000	
 */

public class Bonus_KickBomb extends Bloc {
	
	/** Bloc Bonus "KickBomb" */
	public Bonus_KickBomb(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+1, autres_blocs);
	}//Bonus_KickBomb()

	/** Surcharge: Bloc Bonus "KickBomb" */
	public Bonus_KickBomb(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+1, autre_bloc);
	}//Bonus_KickBomb()

	/** Effet: permet de pousser (faire glisser) une bombe */
	public void action(Graphics g, Joueur joueur) {
		joueur.setPousser(true);
		detruire(g);
	}//action()

};//classe Bonus_KickBomb
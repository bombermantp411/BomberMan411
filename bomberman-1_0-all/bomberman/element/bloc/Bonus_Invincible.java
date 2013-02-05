package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Invincible.java<br>
 	Bloc Bonus "Invincible"<br>
 	Effet: rend le joueur temporairement invincible
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Invincible extends Bloc {
	
	/** Bloc Bonus "Invincible" */
	public Bonus_Invincible(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+7, autres_blocs);
	}//Bonus_Invincible()

	/** Surcharge: Bloc Bonus "Invincible" */
	public Bonus_Invincible(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+7, autre_bloc);
	}//Bonus_Invincible()

	/** Effet: rend le joueur temporairement invincible */
	public void action(Graphics g, Joueur joueur) {
		joueur.addInvincibilite(200);
		detruire(g);
	}//action()

};//classe Bonus_Invincible
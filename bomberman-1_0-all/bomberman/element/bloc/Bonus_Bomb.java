package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Bomb.java<br>
 	Bloc Bonus "Bomb"<br>
 	Effet: augmente le nombre de bombes pouvant être posées simultanément
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Bomb extends Bloc {
	/** Bloc Bonus "Bomb" */
	public Bonus_Bomb(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+0, autres_blocs);
	}//Bonus_Bomb()

	/** Surcharge: Bloc Bonus "Bomb" */
	public Bonus_Bomb(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+0, autre_bloc);
	}//Bonus_Bomb()

	/** Effet: augmente le nombre de bombes pouvant être posées simultanément */
	public void action(Graphics g, Joueur joueur) {
		joueur.addMaxNbBombes(1);
		detruire(g);
	}//action()

};//classe Bonus_Bomb
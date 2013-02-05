package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Range.java<br>
 	Bloc Bonus "Range"<br>
 	Effet: augmente la longueur des explosions
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Range extends Bloc {
	/** Bloc Bonus "Range" */
	public Bonus_Range(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+2, autres_blocs);
	}//Bonus_Range()

	/** Surcharge: Bloc Bonus "Range" */
	public Bonus_Range(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+2, autre_bloc);
	}//Bonus_Range()
	
	/** Effet: augmente la longueur des explosions */
	public void action(Graphics g, Joueur joueur) {
		joueur.addLongueurExlosion(1);
		detruire(g);
	}//action()

};//classe Bonus_Range
package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Slow.java<br>
 	Bloc Bonus "Slow"<br>
 	Effet: réduit la vitesse de déplacement du joueur
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Slow extends Bloc {
	
	/** Bloc Bonus "Slow" */
	public Bonus_Slow(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+5, autres_blocs);
	}//Bonus_Slow()

	/** Surcharge: Bloc Bonus "Slow" */
	public Bonus_Slow(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+5, autre_bloc);
	}//Bonus_Slow()

	/** Effet: réduit la vitesse de déplacement du joueur */
	public void action(Graphics g, Joueur joueur) {
		joueur.reduireVitesseDeplacement();
		detruire(g);
	}//action()

};//classe Bonus_Slow
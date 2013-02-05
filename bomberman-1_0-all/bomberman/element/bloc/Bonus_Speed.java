package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Speed.java<br>
 	Bloc Bonus "Speed"<br>
 	Effet: augmente la vitesse de déplacement du joueur
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Speed extends Bloc {
	
	/** Bloc Bonus "Speed" */
	public Bonus_Speed(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+6, autres_blocs);
	}//Bonus_Speed()

	/** Surcharge: Bloc Bonus "Speed" */
	public Bonus_Speed(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+6, autre_bloc);
	}//Bonus_Speed()

	/** Effet: augmente la vitesse de déplacement du joueur */
	public void action(Graphics g, Joueur joueur) {
		joueur.augmenterVitesseDeplacement();
		detruire(g);
	}//action()

};//classe Bonus_Speed
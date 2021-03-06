package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import general.Information;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Poison.java<br>
 	Bloc Bonus "Poison"<br>
 	Effet: retire une vie au joueur
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Poison extends Bloc {

	/** Bloc Bonus "Poison" */
	public Bonus_Poison(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+9, autres_blocs);
	}//Bonus_Poison()

	/** Surcharge: Bloc Bonus "Poison" */
	public Bonus_Poison(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+9, autre_bloc);
	}//Bonus_Poison()

	/** Effet: retire une vie au joueur */
	public void action(Graphics g, Joueur joueur) {
		Information.addVies(g, joueur, -1);
		detruire(g);
	}//action()

};//classe Bonus_Poison
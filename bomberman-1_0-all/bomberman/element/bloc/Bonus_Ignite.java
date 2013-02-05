package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import element.Bombe;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Ignite.java<br>
 	Bloc Bonus "Ignite"<br>
 	Effet: fait exploser toutes les bombes
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Ignite extends Bloc {

	/** Bloc Bonus "Ignite" */
	public Bonus_Ignite(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+4, autres_blocs);
	}//Bonus_Ignite()

	/** Surcharge: Bloc Bonus "Ignite" */
	public Bonus_Ignite(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+4, autre_bloc);
	}//Bonus_Ignite()

	/** Effet: fait exploser toutes les bombes */
	public void action(Graphics g, Joueur joueur) {
		Bombe.exploserOll();
		detruire(g);
	}//action()

};//classe Bonus_Ignite
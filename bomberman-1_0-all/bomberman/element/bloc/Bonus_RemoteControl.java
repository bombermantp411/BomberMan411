package element.bloc;

import java.awt.*;
import element.Bloc;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_RemoteControl.java<br>
 	Bloc Bonus "RemoteControl"<br>
 	Effet: possession du détonateur
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_RemoteControl extends Bloc {
	/** Bloc Bonus "RemoteControl" */
	public Bonus_RemoteControl(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+3, autres_blocs);
	}//Bonus_RemoteControl()

	/** Surcharge: Bloc Bonus "RemoteControl" */
	public Bonus_RemoteControl(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+3, autre_bloc);
	}//Bonus_RemoteControl()

	/** Effet: possession du détonateur */
	public void action(Graphics g, Joueur joueur) {
		joueur.setDetonateur(true);
		detruire(g);
	}//action()

};//classe Bonus_RemoteControl
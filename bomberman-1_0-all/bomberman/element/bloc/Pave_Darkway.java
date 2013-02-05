package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Darkway.java<br>
 	Bloc Pave "Darkway"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Darkway extends Bloc {
   	/** Bloc Pave "Darkway" */
	public Pave_Darkway(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+6);
	}//Pave_Darkway()

};//classe Pave_Darkway
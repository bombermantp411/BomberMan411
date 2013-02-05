package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Ghost.java<br>
 	Bloc Pave "Ghost"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Ghost extends Bloc {
   	/** Bloc Pave "Ghost" */
	public Pave_Ghost(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+7);
	}//Pave_Ghost()

};//classe Pave_Ghost
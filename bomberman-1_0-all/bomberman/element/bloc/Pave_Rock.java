package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Rock.java<br>
 	Bloc Pave "Rock"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Rock extends Bloc {
   	/** Bloc Pave "Rock" */
	public Pave_Rock(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+15);
	}//Pave_Rock()

};//classe Pave_Rock
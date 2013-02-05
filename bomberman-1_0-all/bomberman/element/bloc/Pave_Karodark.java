package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Karodark.java<br>
 	Bloc Pave "Karodark"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Karodark extends Bloc {
   	/** Bloc Pave "Karodark" */
	public Pave_Karodark(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+11);
	}//Pave_Karodark()

};//classe Pave_Karodark
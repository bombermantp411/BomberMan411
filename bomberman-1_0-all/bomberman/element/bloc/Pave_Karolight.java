package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Karolight.java<br>
 	Bloc Pave "Karolight"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Karolight extends Bloc {
   	/** Bloc Pave "Karolight" */
	public Pave_Karolight(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+12);
	}//Pave_Karolight()

};//classe Pave_Karolight
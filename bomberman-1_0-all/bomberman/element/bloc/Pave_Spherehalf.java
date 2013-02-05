package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Spherehalf.java<br>
 	Bloc Pave "Spherehalf"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Spherehalf extends Bloc {
   	/** Bloc Pave "Spherehalf" */
	public Pave_Spherehalf(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+16);
	}//Pave_Spherehalf()

};//classe Pave_Spherehalf
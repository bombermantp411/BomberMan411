package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Hex.java<br>
 	Bloc Pave "Hex"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Hex extends Bloc {
   	/** Bloc Pave "Hex" */
	public Pave_Hex(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+8);
	}//Pave_Hex()

};//classe Pave_Hex
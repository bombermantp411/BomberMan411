package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Controlnum.java<br>
 	Bloc Pave "Controlnum"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Controlnum extends Bloc {
   	/** Bloc Pave "Controlnum" */
	public Pave_Controlnum(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+5);
	}//Pave_Controlnum()

};//classe Pave_Controlnum
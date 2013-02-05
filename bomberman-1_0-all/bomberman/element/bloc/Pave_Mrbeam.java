package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Mrbeam.java<br>
 	Bloc Pave "Mrbeam"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Mrbeam extends Bloc {
   	/** Bloc Pave "Mrbeam" */
	public Pave_Mrbeam(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+14);
	}//Pave_Mrbeam()

};//classe Pave_Mrbeam
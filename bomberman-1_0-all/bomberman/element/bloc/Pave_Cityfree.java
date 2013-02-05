package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Cityfree.java<br>
 	Bloc Pave "Cityfree"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Cityfree extends Bloc {
   	/** Bloc Pave "Cityfree" */
	public Pave_Cityfree(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+3);
	}//Pave_Cityfree()

};//classe Pave_Cityfree
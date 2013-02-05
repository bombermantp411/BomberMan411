package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Controlalpha.java<br>
 	Bloc Pave "Controlalpha"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Controlalpha extends Bloc {
   	/** Bloc Pave "Controlalpha" */
	public Pave_Controlalpha(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+4);
	}//Pave_Controlalpha()

};//classe Pave_Controlalpha
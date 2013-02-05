package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Pave_Lego.java<br>
 	Bloc Pave "Lego"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Pave_Lego extends Bloc {
   	/** Bloc Pave "Lego" */
	public Pave_Lego(int case_x, int case_y) {
		initPave(case_x, case_y, ZoneJeu.I_BLOCS_PAVES+13);
	}//Pave_Lego()

};//classe Pave_Lego
package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Ghostcircle.java<br>
 	Bloc Brique "Ghostcircle"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Ghostcircle extends Bloc {
	/** Bloc Brique "Ghostcircle" */
	public Brique_Ghostcircle(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+14, autres_blocs);
	}//Brique_Ghostcircle()
	
	/** Surcharge: Bloc Brique "Ghostcircle" */
	public Brique_Ghostcircle(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+14, autre_bloc);
	}//Brique_Ghostcircle()
	
};//classe Brique_Ghostcircle
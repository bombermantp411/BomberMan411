package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Uparrow.java<br>
 	Bloc Brique "Uparrow"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Uparrow extends Bloc {
	/** Bloc Brique "Uparrow" */
	public Brique_Uparrow(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+31, autres_blocs);
	}//Brique_Uparrow()
	
	/** Surcharge: Bloc Brique "Uparrow" */
	public Brique_Uparrow(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+31, autre_bloc);
	}//Brique_Uparrow()
	
};//classe Brique_Uparrow
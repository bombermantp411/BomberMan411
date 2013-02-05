package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Downarrow.java<br>
 	Bloc Brique "Downarrow"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Downarrow extends Bloc {
	/** Bloc Brique "Downarrow" */
	public Brique_Downarrow(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+11, autres_blocs);
	}//Brique_Downarrow()
	
	/** Surcharge: Bloc Brique "Downarrow" */
	public Brique_Downarrow(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+11, autre_bloc);
	}//Brique_Downarrow()
	
};//classe Brique_Downarrow
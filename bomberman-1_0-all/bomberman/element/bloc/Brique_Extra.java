package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Extra.java<br>
 	Bloc Brique "Extra"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Extra extends Bloc {
	/** Bloc Brique "Extra" */
	public Brique_Extra(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+12, autres_blocs);
	}//Brique_Extra()
	
	/** Surcharge: Bloc Brique "Extra" */
	public Brique_Extra(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+12, autre_bloc);
	}//Brique_Extra()
	
};//classe Brique_Extra
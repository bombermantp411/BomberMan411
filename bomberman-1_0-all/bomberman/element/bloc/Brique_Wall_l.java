package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Wall_l.java<br>
 	Bloc Brique "Wall_l"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Wall_l extends Bloc {
	/** Bloc Brique "Wall_l" */
	public Brique_Wall_l(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+33, autres_blocs);
	}//Brique_Wall_l()
	
	/** Surcharge: Bloc Brique "Wall_l" */
	public Brique_Wall_l(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+33, autre_bloc);
	}//Brique_Wall_l()
	
};//classe Brique_Wall_l
package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Dark_l.java<br>
 	Bloc Brique "Dark_l"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Dark_l extends Bloc {
	/** Bloc Brique "Dark_l" */
	public Brique_Dark_l(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+9, autres_blocs);
	}//Brique_Dark_l()
	
	/** Surcharge: Bloc Brique "Dark_l" */
	public Brique_Dark_l(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+9, autre_bloc);
	}//Brique_Dark_l()
	
};//classe Brique_Dark_l
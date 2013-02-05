package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Pyramid_l.java<br>
 	Bloc Brique "Pyramid_l"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Pyramid_l extends Bloc {
	/** Bloc Brique "Pyramid_l" */
	public Brique_Pyramid_l(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+26, autres_blocs);
	}//Brique_Pyramid_l()
	
	/** Surcharge: Bloc Brique "Pyramid_l" */
	public Brique_Pyramid_l(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+26, autre_bloc);
	}//Brique_Pyramid_l()
	
};//classe Brique_Pyramid_l
package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Pyramid.java<br>
 	Bloc Brique "Pyramid"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Pyramid extends Bloc {
	/** Bloc Brique "Pyramid" */
	public Brique_Pyramid(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+25, autres_blocs);
	}//Brique_Pyramid()
	
	/** Surcharge: Bloc Brique "Pyramid" */
	public Brique_Pyramid(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+25, autre_bloc);
	}//Brique_Pyramid()
	
};//classe Brique_Pyramid
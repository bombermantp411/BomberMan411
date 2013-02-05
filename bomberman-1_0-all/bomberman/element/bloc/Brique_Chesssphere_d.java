package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Chesssphere_d.java<br>
 	Bloc Brique "Chesssphere_d"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Chesssphere_d extends Bloc {
	/** Bloc Brique "Chesssphere_d" */
	public Brique_Chesssphere_d(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+5, autres_blocs);
	}//Brique_Chesssphere_d()
	
	/** Surcharge: Bloc Brique "Chesssphere_d" */
	public Brique_Chesssphere_d(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+5, autre_bloc);
	}//Brique_Chesssphere_d()
	
};//classe Brique_Chesssphere_d
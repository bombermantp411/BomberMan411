package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Legoblack_d.java<br>
 	Bloc Brique "Legoblack_d"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Legoblack_d extends Bloc {
	/** Bloc Brique "Legoblack_d" */
	public Brique_Legoblack_d(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+20, autres_blocs);
	}//Brique_Legoblack_d()
	
	/** Surcharge: Bloc Brique "Legoblack_d" */
	public Brique_Legoblack_d(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+20, autre_bloc);
	}//Brique_Legoblack_d()
	
};//classe Brique_Legoblack_d
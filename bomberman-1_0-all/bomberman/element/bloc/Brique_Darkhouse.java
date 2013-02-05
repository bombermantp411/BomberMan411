package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Darkhouse.java<br>
 	Bloc Brique "Darkhouse"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Darkhouse extends Bloc {
	/** Bloc Brique "Darkhouse" */
	public Brique_Darkhouse(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+10, autres_blocs);
	}//Brique_Darkhouse()
	
	/** Surcharge: Bloc Brique "Darkhouse" */
	public Brique_Darkhouse(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+10, autre_bloc);
	}//Brique_Darkhouse()
	
};//classe Brique_Darkhouse
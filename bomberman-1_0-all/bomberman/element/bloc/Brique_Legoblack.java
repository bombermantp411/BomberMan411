package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Legoblack.java<br>
 	Bloc Brique "Legoblack"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Legoblack extends Bloc {
	/** Bloc Brique "Legoblack" */
	public Brique_Legoblack(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+19, autres_blocs);
	}//Brique_Legoblack()
	
	/** Surcharge: Bloc Brique "Legoblack" */
	public Brique_Legoblack(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+19, autre_bloc);
	}//Brique_Legoblack()
	
};//classe Brique_Legoblack
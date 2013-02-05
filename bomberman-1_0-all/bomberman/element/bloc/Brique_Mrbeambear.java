package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Mrbeambear.java<br>
 	Bloc Brique "Mrbeambear"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Mrbeambear extends Bloc {
	/** Bloc Brique "Mrbeambear" */
	public Brique_Mrbeambear(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+22, autres_blocs);
	}//Brique_Mrbeambear()
	
	/** Surcharge: Bloc Brique "Mrbeambear" */
	public Brique_Mrbeambear(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+22, autre_bloc);
	}//Brique_Mrbeambear()
	
};//classe Brique_Mrbeambear
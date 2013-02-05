package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Chest_d.java<br>
 	Bloc Brique "Chest_d"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Chest_d extends Bloc {
	/** Bloc Brique "Chest_d" */
	public Brique_Chest_d(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+7, autres_blocs);
	}//Brique_Chest_d()
	
	/** Surcharge: Bloc Brique "Chest_d" */
	public Brique_Chest_d(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+7, autre_bloc);
	}//Brique_Chest_d()
	
};//classe Brique_Chest_d
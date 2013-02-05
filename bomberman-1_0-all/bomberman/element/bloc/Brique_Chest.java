package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Chest.java<br>
 	Bloc Brique "Chest"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Chest extends Bloc {
	/** Bloc Brique "Chest" */
	public Brique_Chest(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+6, autres_blocs);
	}//Brique_Chest()
	
	/** Surcharge: Bloc Brique "Chest" */
	public Brique_Chest(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+6, autre_bloc);
	}//Brique_Chest()
	
};//classe Brique_Chest
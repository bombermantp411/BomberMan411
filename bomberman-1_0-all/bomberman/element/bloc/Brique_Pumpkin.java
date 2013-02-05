package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Pumpkin.java<br>
 	Bloc Brique "Pumpkin"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Pumpkin extends Bloc {
	/** Bloc Brique "Pumpkin" */
	public Brique_Pumpkin(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+24, autres_blocs);
	}//Brique_Pumpkin()
	
	/** Surcharge: Bloc Brique "Pumpkin" */
	public Brique_Pumpkin(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+24, autre_bloc);
	}//Brique_Pumpkin()
	
};//classe Brique_Pumpkin
package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Dark.java<br>
 	Bloc Brique "Dark"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Dark extends Bloc {
	/** Bloc Brique "Dark" */
	public Brique_Dark(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+8, autres_blocs);
	}//Brique_Dark()
	
	/** Surcharge: Bloc Brique "Dark" */
	public Brique_Dark(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+8, autre_bloc);
	}//Brique_Dark()
	
};//classe Brique_Dark
package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Weight_l.java<br>
 	Bloc Brique "Weight_l"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Weight_l extends Bloc {
	/** Bloc Brique "Weight_l" */
	public Brique_Weight_l(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+35, autres_blocs);
	}//Brique_Weight_l()
	
	/** Surcharge: Bloc Brique "Weight_l" */
	public Brique_Weight_l(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+35, autre_bloc);
	}//Brique_Weight_l()
	
};//classe Brique_Weight_l
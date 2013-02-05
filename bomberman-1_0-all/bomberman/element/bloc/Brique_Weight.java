package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Weight.java<br>
 	Bloc Brique "Weight"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Weight extends Bloc {
	/** Bloc Brique "Weight" */
	public Brique_Weight(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+34, autres_blocs);
	}//Brique_Weight()
	
	/** Surcharge: Bloc Brique "Weight" */
	public Brique_Weight(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+34, autre_bloc);
	}//Brique_Weight()
	
};//classe Brique_Weight
package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Chesssphere.java<br>
 	Bloc Brique "Chesssphere"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Chesssphere extends Bloc {
	/** Bloc Brique "Chesssphere" */
	public Brique_Chesssphere(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+4, autres_blocs);
	}//Brique_Chesssphere()
	
	/** Surcharge: Bloc Brique "Chesssphere" */
	public Brique_Chesssphere(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+4, autre_bloc);
	}//Brique_Chesssphere()
	
};//classe Brique_Chesssphere
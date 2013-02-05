package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Jizz.java<br>
 	Bloc Brique "Jizz"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Jizz extends Bloc {
	/** Bloc Brique "Jizz" */
	public Brique_Jizz(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+18, autres_blocs);
	}//Brique_Jizz()
	
	/** Surcharge: Bloc Brique "Jizz" */
	public Brique_Jizz(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+18, autre_bloc);
	}//Brique_Jizz()
	
};//classe Brique_Jizz
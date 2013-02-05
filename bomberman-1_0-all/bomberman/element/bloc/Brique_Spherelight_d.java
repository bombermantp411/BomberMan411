package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Spherelight_d.java<br>
 	Bloc Brique "Spherelight_d"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Spherelight_d extends Bloc {
	/** Bloc Brique "Spherelight_d" */
	public Brique_Spherelight_d(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+29, autres_blocs);
	}//Brique_Spherelight_d()
	
	/** Surcharge: Bloc Brique "Spherelight_d" */
	public Brique_Spherelight_d(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+29, autre_bloc);
	}//Brique_Spherelight_d()
	
};//classe Brique_Spherelight_d
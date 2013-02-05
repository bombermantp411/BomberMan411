package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Hexawall.java<br>
 	Bloc Brique "Hexawall"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Hexawall extends Bloc {
	/** Bloc Brique "Hexawall" */
	public Brique_Hexawall(int case_x, int case_y, Bloc[] autres_blocs) {	
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+17, autres_blocs);
	}//Brique_Hexawall()
	
	/** Surcharge: Bloc Brique "Hexawall" */
	public Brique_Hexawall(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+17, autre_bloc);
	}//Brique_Hexawall()
	
};//classe Brique_Hexawall
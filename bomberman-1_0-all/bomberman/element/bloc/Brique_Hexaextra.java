package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Hexaextra.java<br>
 	Bloc Brique "Hexaextra"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Hexaextra extends Bloc {
	/** Bloc Brique "Hexaextra" */
	public Brique_Hexaextra(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+16, autres_blocs);
	}//Brique_Hexaextra()
	
	/** Surcharge: Bloc Brique "Hexaextra" */
	public Brique_Hexaextra(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+16, autre_bloc);
	}//Brique_Hexaextra()
	
};//classe Brique_Hexaextra
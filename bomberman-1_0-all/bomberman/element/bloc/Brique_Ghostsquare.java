package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Ghostsquare.java<br>
 	Bloc Brique "Ghostsquare"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Ghostsquare extends Bloc {
	/** Bloc Brique "Ghostsquare" */
	public Brique_Ghostsquare(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+15, autres_blocs);
	}//Brique_Ghostsquare()
	
	/** Surcharge: Bloc Brique "Ghostsquare" */
	public Brique_Ghostsquare(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+15, autre_bloc);
	}//Brique_Ghostsquare()
	
};//classe Brique_Ghostsquare
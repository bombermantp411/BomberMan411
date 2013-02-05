package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Spheredark.java<br>
 	Bloc Brique "Spheredark"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Spheredark extends Bloc {
	/** Bloc Brique "Spheredark" */
	public Brique_Spheredark(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+27, autres_blocs);
	}//Brique_Spheredark()
	
	/** Surcharge: Bloc Brique "Spheredark" */
	public Brique_Spheredark(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+27, autre_bloc);
	}//Brique_Spheredark()
	
};//classe Brique_Spheredark
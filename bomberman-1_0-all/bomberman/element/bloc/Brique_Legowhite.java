package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Legowhite.java<br>
 	Bloc Brique "Legowhite"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Legowhite extends Bloc {
	/** Bloc Brique "Legowhite" */
	public Brique_Legowhite(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+21, autres_blocs);
	}//Brique_Legowhite()
	
	/** Surcharge: Bloc Brique "Legowhite" */
	public Brique_Legowhite(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+21, autre_bloc);
	}//Brique_Legowhite()
	
};//classe Brique_Legowhite
package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Extra_d.java<br>
 	Bloc Brique "Extra_d"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Extra_d extends Bloc {
	/** Bloc Brique "Extra_d" */
	public Brique_Extra_d(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+13, autres_blocs);
	}//Brique_Extra_d()
	
	/** Surcharge: Bloc Brique "Extra_d" */
	public Brique_Extra_d(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+13, autre_bloc);
	}//Brique_Extra_d()
	
};//classe Brique_Extra_d
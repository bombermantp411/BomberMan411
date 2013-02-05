package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Box.java<br>
 	Bloc Brique "Box"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Box extends Bloc {
	/** Bloc Brique "Box" */
	public Brique_Box(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+1, autres_blocs);
	}//Brique_Box()
	
	/** Surcharge: Bloc Brique "Box" */
	public Brique_Box(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+1, autre_bloc);
	}//Brique_Box()
	
};//classe Brique_Box
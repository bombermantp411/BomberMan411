package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Wall.java<br>
 	Bloc Brique "Wall"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Wall extends Bloc {
	/** Bloc Brique "Wall" */
	public Brique_Wall(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+32, autres_blocs);
	}//Brique_Wall()
	
	/** Surcharge: Bloc Brique "Wall" */
	public Brique_Wall(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+32, autre_bloc);
	}//Brique_Wall()
	
};//classe Brique_Wall
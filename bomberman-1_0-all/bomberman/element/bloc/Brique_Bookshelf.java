package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Bookshelf.java<br>
 	Bloc Brique "Bookshelf"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Bookshelf extends Bloc {
	/** Bloc Brique "Bookshelf" */
	public Brique_Bookshelf(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+0, autres_blocs);
	}//Brique_Bookshelf()
	
	/** Surcharge: Bloc Brique "Bookshelf" */
	public Brique_Bookshelf(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+0, autre_bloc);
	}//Brique_Bookshelf()
	
};//classe Brique_Bookshelf
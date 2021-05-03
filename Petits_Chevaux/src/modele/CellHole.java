package modele;

/**
 * CellHole class, special event 
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellHole extends CellPlayable{

	//////Constructor //////

	/**
	 * Constructor of CellFinish
	 * The symbol is the same for all cells of same type
	 * 
	 * @param p The position of the cell
	 */
	public CellHole(Position p) {
		super(p,'@');
	}
	
	
	//////Methods //////

	/**
	 * 	Function used to process the effect of the cell on the player
	 * 	and explains this effect
	 * 	Increments or reset the counter of turns spend in a hole of the player
	 * 
	 * @param r The rider entering the cell
	 * @return A String explaining what the cell does
	 */
	public String process (Rider r) {
		r.incrementHole();
		if(r.inHole()==3) {
			r.resetHole();
		}
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		if(r.inHole()<0) {
			str+="est libéré d'un trou **";
		}else {
			str+="est dans un trou **";
		}
		return str;
	}
}
package modele;

/**
 * CellRiver class, special event
 * @author Marie-Almina
 * @author Tayeb
 */

public class CellRiver extends CellPlayable{

	//////Constructor //////

	/**
	 * Constructor of CellFinish
	 * The symbol is the same for all cells of same type
	 * 
	 * @param p The position of the cell
	 */
	public CellRiver(Position p) {
		super(p,'~');
	}	
	
	//////Methods //////

	/**
	 * 	Function used to process the effect of the cell on the player
	 * 	and explains this effect
	 * 	Sets the river boolean of the rider to true
	 * 
	 * @param r The rider entering the cell
	 * @return A String explaining what the cell does
	 */	public String process (Rider r) {
		r.setRiver(true);
		String str="** Le cavalier ";
		if(r.getColor()==Couleur.RED) {
			str+="ROUGE ";
		}else {
			str+="BLEU ";
		}
		str+="est tombé dans une rivière **";
		return str;
	}
}
package Model;
/* Une classe qui représente une composante
 * d'un jeu.
 * Les personnes, le jeu, le joueur sont des components
 * */

public abstract class Component {

	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package Model;
import java.util.ArrayList;
import java.util.List;

public class Game extends Component {

	private Player player;
	private Place startPlace;
	private List<Component> components;
	private List<Place> places;
	
	
	public Game(String name) {
		this.components=new ArrayList<Component>();
		this.name=name;
	}
	
	public void setPlayer(Player p){
		this.player=p;
	}
	
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	
	public void getPlaces(List<Place> places) {
		this.places = places;
	}

	public void addComponent(Component c){
		this.components.add(c);
	}

	public Player getPlayer() {
		return player;
	}

	public List<Component> getComponents() {
		return components;
	}
	
	public void setStartPlace(Place start) {
		this.startPlace = start;
	}
	
	public Place getStartPlace() {
		return this.startPlace;
	}
	
}

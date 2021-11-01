package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Place extends Component {

	private PlacementType type;
	private List<Png> pngs;
	private Condition depositItemConditions;
	private HashMap<Item, Integer> items;
	private HashMap<Knowledge, Integer> knowledges;
	private List<Arc> arcs_in;
	private List<Arc> arcs_out;
	
	public Place(String nom, PlacementType type) {
		this.name=nom;
		this.type= type;
		this.depositItemConditions = new Condition("depotlibre");
		this.knowledges = new HashMap<Knowledge, Integer>();
		this.items = new HashMap<Item, Integer>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setType(PlacementType type){
		this.type=type;
	}
	
	public PlacementType getType(){
		return this.type;
	}
	
	public List<Arc> getArcOut() {
		return this.arcs_out;
	}

	public List<Png> getPng(){
		return this.pngs;
	}
	
	public void setPng(List<Png> ps){
		this.pngs = ps;
	}
	
	public HashMap<Item, Integer> getItems() {
		return this.items;
	}
	
	public void setItems(HashMap<Item, Integer> its) {
		this.items = its;
	}
	
	public HashMap<Knowledge, Integer> getKnowledges() {
		return this.knowledges;
	}
	
	public void setKnowledges(HashMap<Knowledge, Integer> knows) {
		this.knowledges = knows;
	}
	
	public void setArcsIn(List<Arc> arcs_in) {
		this.arcs_in = arcs_in;
	}
	
	public void setArcsOut(List<Arc> arcs_out) {
		this.arcs_out = arcs_out;
	}
	
	public List<Png> getPossiblePngs() {
		List<Png> possiblepngs = new ArrayList<Png>();
		for (Png png : this.pngs) {
			if (png.getVisibility() == Visibility.visible) {
				possiblepngs.add(png);
			}
		}
		return possiblepngs;
	}
	
	public Condition getConditionsDepotObjet(){
		return this.depositItemConditions;
	}
	
	public HashMap<Item, Integer> getVisibleItems() {
		HashMap<Item, Integer> visibleitems = new HashMap<Item, Integer>();
		for (Entry<Item, Integer> set : this.items.entrySet()) {
			if (set.getKey().getVisibility() == Visibility.visible) {
				visibleitems.put(set.getKey(), set.getValue());
			}
		}
		return visibleitems;
	}
	
	public HashMap<Knowledge, Integer> getVisibleKnowledges() {
		HashMap<Knowledge, Integer> visibleknowledges = new HashMap<Knowledge, Integer>();
		if (this.knowledges.size() > 0) {
			for (Entry<Knowledge, Integer> set : this.knowledges.entrySet()) {
				if (set.getKey().getVisibility() == Visibility.visible) {
					visibleknowledges.put(set.getKey(), set.getValue());
				}
			}
		}
		if (visibleknowledges.size() == 0) {
			return null;
		}
		return visibleknowledges;
	}
}

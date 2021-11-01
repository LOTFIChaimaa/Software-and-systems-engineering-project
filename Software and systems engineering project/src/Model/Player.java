package Model;
import java.util.HashMap;
import java.util.Map.Entry;

public class Player extends Component {
	
	private String name;
	private Place place;
	private int inventoryLoad;
	private int inventorySize;
	private HashMap<Item, Integer> items;
	private HashMap<Knowledge, Integer> knowledges;

	public Player(String name, Place place){
		this.name=name;
		this.inventorySize=10;
		this.items = new HashMap<Item, Integer>();
		this.knowledges = new HashMap<Knowledge, Integer>();
		this.place = place;
		this.inventoryLoad = 0;
	}
	
	public boolean hasItem(Item item, int quantity) {
		for (Entry<Item, Integer> set : this.items.entrySet()) {
		    if (set.getKey() == item && set.getValue() >= quantity) {
		    	return true;
		    }
		}
		return false;
	}

	public boolean addItem(Item item, int quantity) {
		// On ajoute un objet si le joueur a encore de la place dans son inventaire
		if (this.inventorySize - this.inventoryLoad >= item.getPoids()*quantity) {
			boolean itemexist = this.items.containsKey(item);
			if (itemexist) {
				for (Entry<Item, Integer> set : this.items.entrySet()) {
				    if (set.getKey() == item) {
				    	set.setValue(set.getValue()+quantity);
				    	return true;
				    }
				}
			} else {
				this.items.put(item, quantity);
			}
			this.inventoryLoad += item.getPoids()*quantity;
		}
		return false;
	}
	
	public int getQuantityItem(Item item) {
		int quantity = 0;
		for (Entry<Item, Integer> set : this.items.entrySet()) {
			    if (set.getKey() == item) {
			    	quantity = set.getValue();
			    }
		}
		return quantity;
	}

	public void addKnowledge(Knowledge knowledge, int quantity) {
		boolean knowledgeexist = this.knowledges.containsKey(knowledge);
		if (knowledgeexist) {
			for (Entry<Knowledge, Integer> set : this.knowledges.entrySet()) {
			    if (set.getKey() == knowledge) {
			    	int r = set.getValue();
			    	set.setValue(r+quantity);
			    }
			}
		} else {
			this.knowledges.put(knowledge, quantity);
		}
	}
	
	public int getQuantityKnowledge(Knowledge knowledge) {
		int quantity = 0;
		for (Entry<Knowledge, Integer> set : this.knowledges.entrySet()) {
			    if (set.getKey() == knowledge) {
			    	quantity = set.getValue();
			    }
		}
		return quantity;
	}
	
	public void setInventorySize(int p){
		this.inventorySize=p;
	}

	public int getInventorySize() {
		return this.inventorySize;
	}
	
	public void setInventoryLoad(int l){
		this.inventorySize=l;
	}

	public int getInventoryLoad() {
		return this.inventorySize;
	}

	public HashMap<Item, Integer> getItems(){
		return this.items;
	}
	
	public HashMap<Knowledge, Integer> getKnowledges(){
		return this.knowledges;
	}
	
	public void setPlace(Place newplace) {
		this.place = newplace;
	}
	
	public Place getPlace() {
		return this.place;
	}
	
	public void ConsumeItem(Item item, int quantity){
		// On consomme un objet si le joueur l'a en quantité suffisante dans son inventaire
		for (Entry<Item, Integer> set : this.items.entrySet()) {
		    if (set.getKey() == item && set.getValue() >= quantity) {
		    	set.setValue(set.getValue()-quantity);
		    	this.inventoryLoad -= quantity*set.getKey().getPoids();
		    }
		}
	}
}

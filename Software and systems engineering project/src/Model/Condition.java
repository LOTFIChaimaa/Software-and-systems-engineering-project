package Model;
import java.util.HashMap;
import java.util.Map.Entry;

public class Condition extends Component {

	private Condition condition;
	private HashMap<Item,Integer> requiredItems;
	private HashMap<Knowledge,Integer> requiredKnowledge;
	
	public Condition(String name){
		this.name=name;
		this.requiredItems = new HashMap<Item,Integer>();
		this.requiredKnowledge = new HashMap<Knowledge,Integer>();
	}
	
	public Condition(String name, HashMap<Item,Integer> requiredItems, HashMap<Knowledge,Integer> requiredKnowledge){
		this.name=name;
		this.requiredItems = requiredItems;
		this.requiredKnowledge = requiredKnowledge;
	}
	
	private void addRequiredItem(Item item, int multiplicity){
		boolean itemexist = this.requiredItems.containsKey(item);
		if (itemexist) {
			for (Entry<Item, Integer> set : requiredItems.entrySet()) {
			    if (set.getKey() == item) {
			    	set.setValue(set.getValue()+multiplicity);
			    }	
			}
			
		} else {
			requiredItems.put(item, multiplicity);
		}
	}
	
	private void addRequiredKnowledge(Knowledge knowledge, int multiplicity){
		boolean knoweldgeExist = this.requiredKnowledge.containsKey(knowledge);
		if (knoweldgeExist) {
			for (Entry<Knowledge, Integer> set : requiredKnowledge.entrySet()) {
			    if (set.getKey() == knowledge) {
			    	set.setValue(set.getValue()+multiplicity);
			    }	
			}
			
		} else {
			requiredKnowledge.put(knowledge, multiplicity);
		}
	}
	
	public void addCondition(Condition newCondition) {
		HashMap<Item, Integer> itemsToAdd = newCondition.getRequiredItems();
		HashMap<Knowledge, Integer> knowledgeToAdd = newCondition.getRequiredKnowledges();
		for (Entry<Item, Integer> set : itemsToAdd.entrySet()) {
			this.condition.addRequiredItem(set.getKey(), set.getValue());
		}
		for (Entry<Knowledge, Integer> set : knowledgeToAdd.entrySet()) {
			this.condition.addRequiredKnowledge(set.getKey(), set.getValue());
		}
	}

	public Condition getCondition() {
		return this.condition;
	}
	
	public HashMap<Item,Integer> getRequiredItems() {
		return this.requiredItems;
	}
	
	public HashMap<Knowledge,Integer> getRequiredKnowledges() {
		return this.requiredKnowledge;
	}
	
	public void setRequiredItems(HashMap<Item,Integer> requitems) {
		this.requiredItems = requitems;
	}
	
	public void setRequiredKnowledges(HashMap<Knowledge,Integer> requiknowledges) {
		this.requiredKnowledge = requiknowledges;
	}
	
	public static boolean verifyCondition(Player p, Condition condition) {
		HashMap<Item,Integer> requireditems = condition.getRequiredItems();
		HashMap<Knowledge,Integer> requiredknowledges = condition.getRequiredKnowledges();
		boolean conditionVerified = true;
		for (Entry<Item, Integer> set : requireditems.entrySet()) {
			int itemquantityplayer = p.getQuantityItem(set.getKey());
			if (itemquantityplayer < set.getValue()) {
				conditionVerified = false;
			}
		}
		for (Entry<Knowledge, Integer> set : requiredknowledges.entrySet()) {
			int knowledgequantityplayer = p.getQuantityKnowledge(set.getKey());
			if (knowledgequantityplayer < set.getValue()) {
				conditionVerified = false;
			}
		}
		return conditionVerified;
	}
	
}

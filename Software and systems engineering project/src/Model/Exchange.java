package Model;
import java.util.HashMap;

public class Exchange {

	private Condition conditionExchange;
	private HashMap<Item, Integer> gaineditems;
	private HashMap<Knowledge, Integer> gainedknowledge;
	private HashMap<Item, Integer> consumeditems;
	
	public Exchange(){
		this.conditionExchange = new Condition("");
		this.gaineditems = new HashMap<Item, Integer>();
		this.gainedknowledge = new HashMap<Knowledge, Integer>();
		this.consumeditems = new HashMap<Item, Integer>();
	}
	
	public Exchange(Condition condition){
		this.conditionExchange = condition;
		this.gaineditems = new HashMap<Item, Integer>();
		this.gainedknowledge = new HashMap<Knowledge, Integer>();
		this.consumeditems = new HashMap<Item, Integer>();
	}
	
	public void addConditionExchange(Condition c){
		this.conditionExchange.addCondition(c);
	}

	public Condition getConditionExchange() {
		return this.conditionExchange;
	}
	
	public HashMap<Knowledge, Integer> getGainedKnowledge(){
		return this.gainedknowledge;
	}
	
	public HashMap<Item, Integer> getConsumedItems(){
		return this.consumeditems;
	}
	
	public HashMap<Item, Integer> getGainedItems(){
		return this.gaineditems;
	}
	
	public void setConditionExchange(Condition c) {
		this.conditionExchange = c;
	}
	
	public void setGainedKnowledge(HashMap<Knowledge, Integer> gainedknow){
		 this.gainedknowledge = gainedknow;
	}
	
	public void setConsumedItems(HashMap<Item, Integer> consumitems){
		this.consumeditems = consumitems;
	}
	
	public void setGainedItems(HashMap<Item, Integer> gainitems){
		this.gaineditems = gainitems;
	}
}

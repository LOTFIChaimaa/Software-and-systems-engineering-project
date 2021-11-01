package Model;
import java.util.ArrayList;
import java.util.List;

public class Interaction {
	
	private Condition conditionInteraction;
	private String name;
	private List<Choice> choices;
	
	public Interaction(String name) {
		this.name = name;
		this.choices = new ArrayList<Choice>();
	}
	
	public Interaction(String name, Condition conditionInteraction, List<Choice> choices) {
		this.name = name;
		this.conditionInteraction = conditionInteraction;
		this.choices = choices;
	}
	
	public void setConditionInteraction(Condition c) {
		this.conditionInteraction = c;
	}
	
	public Condition getConditionInteraction() {
		return this.conditionInteraction;
	}
	
	public List<Choice> getChoices() {
		return this.choices;
	}
	
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
}

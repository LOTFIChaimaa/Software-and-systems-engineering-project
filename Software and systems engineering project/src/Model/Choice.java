package Model;

public class Choice extends Component {
	
	private Action action;
	private Condition startconditions;
	private Condition finishconditions;
	private PlacementType type;
	private String name;
	private String description;
	
	public Choice(String name, PlacementType type, Action action){
		this.name=name;
		this.action = action;
		this.type = type;
	}
	
	public Choice(String name, PlacementType type, Condition startconditions, Condition finishconditions, Action action){
		this.name=name;
		this.action = action;
		this.startconditions = startconditions;
		this.finishconditions = finishconditions;
		this.type = type;
	}
	
	public Condition getstartconditions() {
		return this.startconditions;
	}
	
	public Condition getfinishconditions() {
		return this.finishconditions;
	}
	
	public void setstartconditions(Condition startconditions) {
		this.startconditions = startconditions;
	}
	
	public void setfinishconditionsfinishconditions(Condition finishconditions) {
		this.finishconditions = finishconditions;
	}
	
	public Action getAction() {
		return this.action;
	}
	
	public PlacementType getPlacementType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}

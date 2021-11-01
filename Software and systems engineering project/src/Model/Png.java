package Model;

import java.util.List;

public  class Png extends Component {
	
	private List<Interaction> interactions;
	private Condition visibilityConditions;
	private Visibility visibility;
	private Place place;
	private String name;
	
	public Png(String name, Place place,  List<Interaction> interactions){
		this.name=name;
		this.visibility = Visibility.invisible;
		this.interactions = interactions;
		this.place = place;
	}
	
	public Png(String name, Place place, Visibility visibility, Condition visibilityConditions,  List<Interaction> interaction){
		this.name=name;
		this.visibility = visibility;
		this.interactions = interactions;
		this.place = place;
		this.visibilityConditions = visibilityConditions;
	}
	
	public void addConditionVisibilite(Condition c){
		this.visibilityConditions = c;
	}
	
	public void setConditionVisibilite(Condition c){
		this.visibilityConditions = new Condition(c.name);
		this.visibilityConditions.addCondition(c);
	}
	
	public Condition getConditionVisibilite(){
		return this.visibilityConditions;
	}
	
	public void setVisibility(Visibility v){
		this.visibility=v;
	}
	
	public Visibility getVisibility(){
		return this.visibility;
	}
	
	public List<Interaction> getInteractions(){
		return this.interactions;
	}
	
	public void setInteractions(List<Interaction> interactions){
		this.interactions = interactions;
	}
	
	public String getName() {
		return this.name;
	}

}

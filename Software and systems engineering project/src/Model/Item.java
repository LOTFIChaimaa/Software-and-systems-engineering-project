package Model;
public class Item extends Component{

	private int size;
	private Visibility visibility;
	private Condition conditionVisibility;
	
	public Item(String name, int size){
		this.name=name;
		this.size=size;
		this.visibility=Visibility.visible;
	}

	public Item(String name, Condition conditionVisibility, Visibility visibility, int size){
		this.name=name;
		this.size=size;
		this.visibility=visibility;
		this.conditionVisibility = conditionVisibility;
	}
	
	public void addConditionVisibilite(Condition c){
		this.conditionVisibility.addCondition(c);
	}
	
	public Condition getConditionVisibilite(){
		return this.conditionVisibility;
	}
	
	public void setVisibility(Visibility v){
		this.visibility=v;
	}
	
	public Visibility getVisibility(){
		return this.visibility;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setPoids(int p){
		this.size=p;
	}
	
	public int getPoids() {
		return size;
	}
}


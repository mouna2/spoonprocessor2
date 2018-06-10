package mypackage;

public class RequirementGold {
	Requirement2 Requirement=new Requirement2(); 
	String gold="";
	public Requirement2 getRequirement() {
		return Requirement;
	}
	public void setRequirement(Requirement2 requirement) {
		Requirement = requirement;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public RequirementGold(Requirement2 requirement, String gold) {
		super();
		Requirement = requirement;
		this.gold = gold;
	}
	public RequirementGold() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RequirementGold [Requirement=" + Requirement + ", gold=" + gold + "]           ";
	} 
	
	
}

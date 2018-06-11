package mainPackage;

public class Requirement {
	String ID; 
	public String RequirementName;
	public Requirement(String iD, String requirementName) {
		super();
		ID = iD;
		RequirementName = requirementName;
	}
	public Requirement() {
		// TODO Auto-generated constructor stub
	}
	public String getID() {
		return ID;
	}
	public void setID(String string) {
		ID = string;
	}
	public String getRequirementName() {
		return RequirementName;
	}
	public void setRequirementName(String requirementName) {
		RequirementName = requirementName;
	}

	
	
	
	
}

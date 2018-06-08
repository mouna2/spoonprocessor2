package mypackage;

import java.util.List;

public class Method2Representation {
	String methodid; 
	String methodname;
	List<Requirement2> requirements; 
	public Method2Representation(String methodid, String methodname) {
		super();
		this.methodid = methodid;
		this.methodname = methodname;
	}
	public Method2Representation() {
		// TODO Auto-generated constructor stub
	}
	public String getMethodid() {
		return methodid;
	}
	public void setMethodid(String methodid) {
		this.methodid = methodid;
	}
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	@Override
	public String toString() {
		return "Method2Representation [methodid=" + methodid + ", methodname=" + methodname + "]";
	}
	public List<Requirement2> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<Requirement2> requirements) {
		this.requirements = requirements;
	}
	public Method2Representation(String methodid, String methodname, List<Requirement2> requirements) {
		super();
		this.methodid = methodid;
		this.methodname = methodname;
		this.requirements = requirements;
	}
	
	
	
	
}

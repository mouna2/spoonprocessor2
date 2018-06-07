package mypackage;

public class MethodTrace2 {
	
	public Method2Representation MethodRepresentation; 
	public Requirement2 Requirement; 
	public ClassRepresentation2 ClassRepresentation; 
	public String gold; 
	public String subject;
	
	public MethodTrace2() {
		super();
	}

	public Method2Representation getMethodRepresentation() {
		return MethodRepresentation;
	}

	public void setMethodRepresentation(Method2Representation methodRepresentation) {
		MethodRepresentation = methodRepresentation;
	}

	public Requirement2 getRequirement() {
		return Requirement;
	}

	public void setRequirement(Requirement2 requirement) {
		Requirement = requirement;
	}

	public ClassRepresentation2 getClassRepresentation() {
		return ClassRepresentation;
	}

	public void setClassRepresentation(ClassRepresentation2 classRepresentation) {
		ClassRepresentation = classRepresentation;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public MethodTrace2(Method2Representation methodRepresentation, Requirement2 requirement,
			ClassRepresentation2 classRepresentation, String gold, String subject) {
		super();
		MethodRepresentation = methodRepresentation;
		Requirement = requirement;
		ClassRepresentation = classRepresentation;
		this.gold = gold;
		this.subject = subject;
	}
	
	
	
}

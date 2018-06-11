package mainPackage;

public class ClassTrace {
	String ID; 
		Requirement requirement; 
		ClassRepresentation myclass; 
		String gold; 
		String subject;
		public ClassTrace(String iD, Requirement requirement, ClassRepresentation myclass, String gold, String subject) {
			super();
			ID = iD;
			this.requirement = requirement;
			this.myclass = myclass;
			this.gold = gold;
			this.subject = subject;
		}
		
		public ClassTrace() {
			// TODO Auto-generated constructor stub
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public Requirement getRequirement() {
			return requirement;
		}
		public void setRequirement(Requirement requirement) {
			this.requirement = requirement;
		}
		public ClassRepresentation getMyclass() {
			return myclass;
		}
		public void setMyclass(ClassRepresentation myclass) {
			this.myclass = myclass;
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
	
		
}

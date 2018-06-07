package mainPackage;

public class Interface {
	String ID; 
	public ClassRepresentation InterfaceClass; 
	public ClassRepresentation OwnerClass;
	public Interface(String iD, ClassRepresentation interfaceClass, ClassRepresentation ownerClass) {
		super();
		ID = iD;
		InterfaceClass = interfaceClass;
		OwnerClass = ownerClass;
	}
	public Interface() {
		// TODO Auto-generated constructor stub
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public ClassRepresentation getInterfaceClass() {
		return InterfaceClass;
	}
	public void setInterfaceClass(ClassRepresentation interfaceClass) {
		InterfaceClass = interfaceClass;
	}
	public ClassRepresentation getOwnerClass() {
		return OwnerClass;
	}
	public void setOwnerClass(ClassRepresentation ownerClass) {
		OwnerClass = ownerClass;
	}
	 
	
	
	
}

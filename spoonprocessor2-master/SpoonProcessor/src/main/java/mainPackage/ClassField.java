package mainPackage;

public class ClassField {
	
	String ID; 
	 String FieldName;
	 ClassRepresentation FieldType; 
	 ClassRepresentation OwnerClass;
	
	public ClassField(String iD, String fieldName, ClassRepresentation fieldType, ClassRepresentation ownerClass) {
		super();
		ID = iD;
		FieldName = fieldName;
		FieldType = fieldType;
		OwnerClass = ownerClass;
	
	}

	public ClassField() {
		// TODO Auto-generated constructor stub
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public ClassRepresentation getFieldType() {
		return FieldType;
	}

	public void setFieldType(ClassRepresentation fieldType) {
		FieldType = fieldType;
	}

	public ClassRepresentation getOwnerClass() {
		return OwnerClass;
	}

	public void setOwnerClass(ClassRepresentation ownerClass) {
		OwnerClass = ownerClass;
	} 

	 
	 
	
	
	 
	 
	
	 
	 
	
	 
	 
	 
}

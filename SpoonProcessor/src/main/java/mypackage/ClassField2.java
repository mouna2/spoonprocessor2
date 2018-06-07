package mypackage;

public class ClassField2 {
	
	String ID; 
	 String FieldName;
	 ClassRepresentation2 FieldType; 
	 ClassRepresentation2 OwnerClass;
	
	public ClassField2(String iD, String fieldName, ClassRepresentation2 fieldType, ClassRepresentation2 ownerClass) {
		super();
		ID = iD;
		FieldName = fieldName;
		FieldType = fieldType;
		OwnerClass = ownerClass;
	
	}

	public ClassField2() {
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

	public ClassRepresentation2 getFieldType() {
		return FieldType;
	}

	public void setFieldType(ClassRepresentation2 fieldType) {
		FieldType = fieldType;
	}

	public ClassRepresentation2 getOwnerClass() {
		return OwnerClass;
	}

	public void setOwnerClass(ClassRepresentation2 ownerClass) {
		OwnerClass = ownerClass;
	} 

	 
	 
	
	
	 
	 
	
	 
	 
	
	 
	 
	 
}

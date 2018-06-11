package mypackage;

public class MethodField2 {
	String ID; 
	 String FieldName;
	 ClassRepresentation2 MethodFieldType; 
	 ClassRepresentation2 OwnerClass;
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
	public ClassRepresentation2 getMethodFieldType() {
		return MethodFieldType;
	}
	public void setMethodFieldType(ClassRepresentation2 methodFieldType) {
		MethodFieldType = methodFieldType;
	}
	public ClassRepresentation2 getOwnerClass() {
		return OwnerClass;
	}
	public void setOwnerClass(ClassRepresentation2 ownerClass) {
		OwnerClass = ownerClass;
	}
	public MethodField2(String iD, String fieldName, ClassRepresentation2 methodFieldType,
			ClassRepresentation2 ownerClass) {
		super();
		ID = iD;
		FieldName = fieldName;
		MethodFieldType = methodFieldType;
		OwnerClass = ownerClass;
	}
	public MethodField2() {
		// TODO Auto-generated constructor stub
	}
	 
	 
	 
	 
}

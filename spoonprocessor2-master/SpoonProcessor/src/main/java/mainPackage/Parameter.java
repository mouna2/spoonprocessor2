package mainPackage;

public class Parameter {
	int ID; 
	String ParameterName; 
	ClassRepresentation ParameterType; 	
	Method OwnerMethod; 
	int IsReturn;
	public Parameter(int iD, String parameterName, ClassRepresentation parameterType, Method ownerMethod, int isReturn) {
		super();
		ID = iD;
		ParameterName = parameterName;
		ParameterType = parameterType;
		OwnerMethod = ownerMethod;
		IsReturn = isReturn;
	}
	
	
	
	
	
}

package mainPackage;

public class Parameter {
	int ID; 
	String ParameterName; 
	Class ParameterType; 	
	Method OwnerMethod; 
	int IsReturn;
	public Parameter(int iD, String parameterName, Class parameterType, Method ownerMethod, int isReturn) {
		super();
		ID = iD;
		ParameterName = parameterName;
		ParameterType = parameterType;
		OwnerMethod = ownerMethod;
		IsReturn = isReturn;
	}
	
	
	
	
	
}

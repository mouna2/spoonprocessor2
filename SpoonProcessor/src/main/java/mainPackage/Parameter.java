package mainPackage;

public class Parameter {
	int ID; 
	String ParameterName; 
	Class ParameterClass; 	
	Method Method; 
	int IsReturn;
	public Parameter(int iD, String parameterName, Class parameterClass, 
			mainPackage.Method method, int isReturn) {
		super();
		ID = iD;
		ParameterName = parameterName;
		ParameterClass = parameterClass;
	
		Method = method;
		IsReturn = isReturn;
	}
	
	
	
	
	
}

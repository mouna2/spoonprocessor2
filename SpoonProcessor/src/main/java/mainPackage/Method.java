package mainPackage;

import java.util.HashMap;
import java.util.List;

public class Method {
	int ID; 
	public String methodName; 
	public ClassRepresentation OwnerClass;
	List<Method> callees; 
	List<Method> callers; 
	List<Parameter> parameters;
	List<MethodField> MethodFields; 
	HashMap<Requirement, MethodTrace> MethodTraces; 
	
	
	
	public Method(int iD, String methodName, ClassRepresentation ownerClass, List<Method> callees, List<Method> callers,
			List<Parameter> parameters, List<MethodField> methodFields,
			HashMap<Requirement, MethodTrace> methodTraces) {
		super();
		ID = iD;
		this.methodName = methodName;
		OwnerClass = ownerClass;
		this.callees = callees;
		this.callers = callers;
		this.parameters = parameters;
		MethodFields = methodFields;
		MethodTraces = methodTraces;
	}



	public Method(int iD, String methodName, ClassRepresentation ownerClass) {
		super();
		ID = iD;
		this.methodName = methodName;
		OwnerClass = ownerClass;
		
	} 
	
	
	
	
	
	
	
	
}

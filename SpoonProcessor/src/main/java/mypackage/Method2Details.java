package mypackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Method2Details {
	Method2Representation methodrep; 
	ClassRepresentation2 OwnerClass; 
	List<Method2Representation> callersList= new ArrayList<Method2Representation>(); 
	List<Method2Representation> calleesList= new ArrayList<Method2Representation>(); 
	List<Parameter2> parametersList= new ArrayList<Parameter2>(); 
	List<MethodField2> methodfieldsList= new ArrayList<MethodField2>();  
	HashMap<Requirement2, MethodTrace2> methodtraces= new HashMap <Requirement2, MethodTrace2>();
	
	
	
	HashMap<Integer, Method2Details> MethodDetailsHashMap= new HashMap<Integer, Method2Details>(); 
	
	public Method2Representation getMethodrep() {
		return methodrep;
	}
	public void setMethodrep(Method2Representation methodrep) {
		this.methodrep = methodrep;
	}
	public ClassRepresentation2 getOwnerClass() {
		return OwnerClass;
	}
	public void setOwnerClass(ClassRepresentation2 ownerClass) {
		OwnerClass = ownerClass;
	}
	public List<Method2Representation> getCallers() {
		return callersList;
	}
	public void setCallers(List<Method2Representation> callers) {
		this.callersList = callers;
	}
	public List<Method2Representation> getCallees() {
		return calleesList;
	}
	public void setCallees(List<Method2Representation> callees) {
		this.calleesList = callees;
	}
	public List<Parameter2> getParameters() {
		return parametersList;
	}
	public void setParameters(List<Parameter2> parameters) {
		this.parametersList = parameters;
	}
	public List<MethodField2> getMethodfields() {
		return methodfieldsList;
	}
	public void setMethodfields(List<MethodField2> methodfields) {
		this.methodfieldsList = methodfields;
	}
	public HashMap<Requirement2, MethodTrace2> getMethodtraces() {
		return methodtraces;
	}
	public void setMethodtraces(HashMap<Requirement2, MethodTrace2> methodtraces) {
		this.methodtraces = methodtraces;
	}
	public Method2Details(Method2Representation methodrep, ClassRepresentation2 ownerClass,
			List<Method2Representation> callers, List<Method2Representation> callees, List<Parameter2> parameters,
			List<MethodField2> methodfields, HashMap<Requirement2, MethodTrace2> methodtraces) {
		super();
		this.methodrep = methodrep;
		OwnerClass = ownerClass;
		this.callersList = callers;
		this.calleesList = callees;
		this.parametersList = parameters;
		this.methodfieldsList = methodfields;
		this.methodtraces = methodtraces;
	} 
	
	
	public Method2Details() {
		// TODO Auto-generated constructor stub
	}
	public  HashMap<Integer, Method2Details> ReadClassesRepresentations(Connection conn) throws SQLException {
		// Rule: if method A calls method B and method A implements requirement X, then I can just assume that method B implements requirement X as well 
		// Retrieving the calleeid
		DatabaseReading2 db = new DatabaseReading2(); 
		Method2Details methoddet2= new Method2Details(); 
		//CLASSESHASHMAP
		String rowcount = null; 
		Statement st = conn.createStatement();
		ResultSet var = st.executeQuery("select count(*) from methods"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
		}
		System.out.println("ROW COUNT::::::"+rowcount); 
		int rowcountint= Integer.parseInt(rowcount); 
	
		int index=1; 
		
		 ResultSet myresults = st.executeQuery("SELECT methods.* from methods where id='"+ index +"'"); 
		 while(myresults.next()) {
			 	methoddet2= new Method2Details(); 
			     String id = myresults.getString("id"); 			
				 String methodname = myresults.getString("methodname"); 
				
				 Method2Representation methodrep= new Method2Representation(id, methodname); 
				 methoddet2.setMethodrep(methodrep);
				 
				 
				 ClassRepresentation2 classrep= new ClassRepresentation2(); 
				 classrep.setClassid(myresults.getString("classid"));
				 classrep.setClassname(myresults.getString("classname"));
				 methoddet2.setOwnerClass(classrep);
				 
				 
				 
				 ResultSet callers=st.executeQuery("select methodcalls.* from methodcalls where calleemethodid='" + id+"'"); 
				 this.callersList= new  ArrayList<Method2Representation>(); 
				 while(callers.next()) {
					 
					 Method2Representation meth= new Method2Representation(); 	
					 meth.setMethodid(callers.getString("callermethodid"));
					 meth.setMethodname(callers.getString("callername"));
					 this.callersList.add(meth); 					 
					 methoddet2.setCallers(callersList);
				 }
				 
				 ResultSet callees=st.executeQuery("select methodcalls.* from methodcalls where callermethodid='" + id+"'"); 
				 this.calleesList= new  ArrayList<Method2Representation>(); 
				 while(callees.next()) {
					
					 Method2Representation meth= new Method2Representation(); 	
					 meth.setMethodid(callees.getString("calleemethodid"));
					 meth.setMethodname(callees.getString("calleename"));
					 this.calleesList.add(meth); 					 
					 methoddet2.setCallees(calleesList);
				 }
				 
				 
				 ResultSet parameters=st.executeQuery("select parameters.* from parameters where methodid='" + id+"'"); 
				 this.parametersList= new ArrayList<Parameter2>(); 
				 while(parameters.next()) {
					 
					 Parameter2 param= new Parameter2(); 	
					 param.setParameterName(parameters.getString("parametername"));
					 
					 ClassRepresentation2 ParamType= new ClassRepresentation2(); 
					 ParamType.setClassid(parameters.getString("parameterclass"));
					 ParamType.setClassname(parameters.getString("parametertype"));
					 param.setParameterType(ParamType);
					 
					 ClassRepresentation2 OwnerType= new ClassRepresentation2(); 
					 OwnerType.setClassid(parameters.getString("classid"));
					 OwnerType.setClassname(parameters.getString("classname"));
					 param.setOwnerClass(OwnerType);
					 
					 param.setIsReturn(parameters.getString("isreturn"));
					 parametersList.add(param); 
					 methoddet2.setParameters(parametersList);
				 }
				 
				 
				 ResultSet methodFields=st.executeQuery("select fieldmethods.* from fieldmethods where ownermethodid='" + id+"'"); 
				 this.methodfieldsList= new ArrayList<MethodField2>(); 
				 while(methodFields.next()) {
					
					 MethodField2 methfield= new MethodField2(); 	
					 methfield.setFieldName(methodFields.getString("fieldaccess"));
					 
					 ClassRepresentation2 FieldType= new ClassRepresentation2(); 
					 FieldType.setClassid(methodFields.getString("fieldtypeclassid"));
					 FieldType.setClassname(methodFields.getString("fieldtype"));
					 methfield.setMethodFieldType(FieldType);
					 
					 ClassRepresentation2 OwnerType= new ClassRepresentation2(); 
					 OwnerType.setClassid(methodFields.getString("ownerclassid"));
					 OwnerType.setClassname(methodFields.getString("classname"));
					 methfield.setOwnerClass(OwnerType);
					 
					
					 methodfieldsList.add(methfield); 
					 methoddet2.setMethodfields(methodfieldsList);
				 }
				 
				 
				 ResultSet methodtracesres = st.executeQuery("SELECT traces.* from traces where methodid ='"+id+"'"); 
					//populateTables(classtraces, conn);
				 this.methodtraces= new  HashMap <Requirement2, MethodTrace2>();	
				 while(methodtracesres.next()) {
						
						MethodTrace2 MethodTrace= new MethodTrace2();
						//classtrace.setID(classtraces.getString("id"));
						MethodTrace.setGold(methodtracesres.getString("gold"));
						
						MethodTrace.setSubject(methodtracesres.getString("subject"));
						
						Requirement2 requirement= new Requirement2();
						requirement.setID(methodtracesres.getString("requirementid"));
						requirement.setRequirementName(methodtracesres.getString("requirement"));
						MethodTrace.setRequirement(requirement);

						ClassRepresentation2 myclass= new ClassRepresentation2();
						myclass.setClassname(methodtracesres.getString("classname"));
						myclass.setClassid(methodtracesres.getString("classid"));
						MethodTrace.setClassRepresentation(myclass);
						
						Method2Representation methodrepres = new Method2Representation(); 
						methodrepres.setMethodid(methodtracesres.getString("methodid"));
						methodrepres.setMethodname(methodtracesres.getString("method"));
						MethodTrace.setMethodRepresentation(methodrepres);
						methodtraces.put(requirement, MethodTrace);
						methoddet2.setMethodtraces(methodtraces);
					}
				 
				 MethodDetailsHashMap.put(index, methoddet2); 
				 index=index+1; 
				 myresults = st.executeQuery("SELECT methods.* from methods where id='"+ index +"'"); 
				 
		 }
		
		 
		 
		 
		 
		 return MethodDetailsHashMap; 
	}
	
	
}

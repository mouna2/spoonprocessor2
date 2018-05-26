package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import Tables.CallerIDName;
import Tables.tracesmethods;
import Tables.tracesmethodscallees;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.CtModel;
import spoon.reflect.factory.ClassFactory;

public class DatabaseReading {
	public static  HashMap<Integer, String> classesHashMap = new HashMap<Integer, String>();
	public static  HashMap<Integer, FieldTypeClass> FieldClassesHashMap = new HashMap<Integer, FieldTypeClass>();
	public static  HashMap<Integer, Method> MethodsHashMap = new HashMap<Integer, Method>();
	public static  HashMap<Integer, Requirement> RequirementsHashMap = new HashMap<Integer, Requirement>();
	public static  HashMap<Integer, Interfaces> InterfaceHashMap = new HashMap<Integer, Interfaces>();
	public static  HashMap<Integer, FieldTypeClass> FieldTypeClasses = new HashMap<Integer, FieldTypeClass>();
	public static  HashMap<Integer, FieldMethods> FieldMethodsHashMap = new HashMap<Integer, FieldMethods>();
	public static  HashMap<Integer, MethodCalls> MethodCallsHashMap = new HashMap<Integer, MethodCalls>();
	public static  HashMap<Integer, MethodCalls> MethodCallsEXECUTEDHashMap= new HashMap<Integer, MethodCalls>();
	public static  HashMap<Integer, SuperClasses> SuperClassesHashMap= new HashMap<Integer, SuperClasses>();
	public static  HashMap<Integer, Parameter> ParameterHashMap= new HashMap<Integer, Parameter>();
	public static  HashMap<Integer, TraceClass> TraceClassHashMap= new HashMap<Integer, TraceClass>();
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "root";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "databasechess";

	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("root", this.userName);
		connectionProps.put("123456", this.password);
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasechess","root","123456");
		SpoonAPI spoon = new Launcher();
		spoon.addInputResource("C:\\Users\\mouna\\Downloads\\chessgantcode\\workspace_codeBase\\Chess");
		spoon.getEnvironment().setAutoImports(true);
		spoon.getEnvironment().setNoClasspath(true);
	

		// Interact with model
		
		return conn;
	}

	
	public static void Read(java.sql.Statement st, java.sql.Statement st2) throws SQLException {
		// Rule: if method A calls method B and method A implements requirement X, then I can just assume that method B implements requirement X as well 
		// Retrieving the calleeid
		DatabaseReading db = new DatabaseReading(); 
		
	
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//CLASSESHASHMAP
		String rowcount = null; 
		ResultSet var = st.executeQuery("select count(*) from classes"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
			}
		System.out.println("ROW COUNT::::::"+rowcount); 
		int rowcountint= Integer.parseInt(rowcount); 
		int row=0; 
		String id=null;
		String classname=null; 
		String fieldname=null; 
		
		while(row<=rowcountint) {
			
			
			
			
			ResultSet classes = st.executeQuery("SELECT classes.* from classes where id='"+row+"'"); 
			while(classes.next()){
				id = classes.getString("id"); 
				int ID2 = Integer.parseInt(id); 
				classname = classes.getString("classname"); 
				
				classesHashMap.put(ID2, classname); 
			}
			row++; 
		}
		Set<Integer> keys = classesHashMap.keySet();
	HashMap<Integer, String> result = classesHashMap.entrySet().stream()
	                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	
		
        for(Integer key: keys){
            System.out.println("Value of "+key+" is: "+ result.get(key));
        }

		
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //FIELDCLASSESHASHMAP
         rowcount = null; 
		 var = st.executeQuery("select count(*) from fieldclasses"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
			}
		System.out.println("ROW COUNT::::::"+rowcount); 
		 rowcountint= Integer.parseInt(rowcount); 
		
		 id=null;
		 classname=null; 
		 String fieldtypeclassid = null; 
		 String ownerclassid = null; 
		 Class FieldTypeClass=null; 
		 Class OwnerClass=null;
				
			 row=1; 
				
				while(row<rowcountint) {
					ResultSet fieldclasses = st.executeQuery("SELECT fieldclasses.* from fieldclasses where id='"+row+"'"); 
					
					
					while(fieldclasses.next() ){
						id = fieldclasses.getString("id"); 
						int ID2 = Integer.parseInt(id); 
						fieldname = fieldclasses.getString("fieldname"); 
						
						
						fieldtypeclassid = fieldclasses.getString("fieldtypeclassid"); 
						ownerclassid = fieldclasses.getString("ownerclassid"); 
						 System.out.println("field=========> "+ID2); 
						 System.out.println("fieldname=========> "+fieldname); 
						
						 ResultSet myresults = st.executeQuery("SELECT classes.* from classes where id='"+fieldtypeclassid+"'"); 
						 while(myresults.next()) {
							     id = myresults.getString("id"); 
								 ID2 = Integer.parseInt(id); 
								 classname = myresults.getString("classname"); 
								 System.out.println("HERE IS AN ID FOR THE CLASS=========> "+ID2); 
								 System.out.println("CLASSNAME=========> "+classname); 
								  FieldTypeClass= new Class(ID2, classname); 
						 }
						 
						 

						
							
							  myresults = st.executeQuery("SELECT classes.* from classes where id='"+ownerclassid+"'"); 
							 while(myresults.next()) {
								     id = myresults.getString("id"); 
									 ID2 = Integer.parseInt(id); 
									 classname = myresults.getString("classname"); 
									 System.out.println("HERE IS AN ID FOR THE CLASS OWNER =========> "+ID2); 
									 System.out.println("CLASSNAME OWNER =========> "+classname); 
									  OwnerClass= new Class(ID2, classname); 
									 
							 }
							 
							 FieldTypeClass Fieldtype= new FieldTypeClass(row, fieldname, FieldTypeClass, OwnerClass); 
							 FieldClassesHashMap.put(row, Fieldtype); 
						fieldclasses = st.executeQuery("SELECT fieldclasses.* from fieldclasses where id='"+row+"'"); 
						row++; 
					}
					//fieldclasses.close();
				}
				
			
		 keys = FieldClassesHashMap.keySet();
		Map<Integer, FieldTypeClass> resultFieldClasses = FieldClassesHashMap.entrySet().stream()
		                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		System.out.println("FIELD TYPE CLASSES");
		 for(Integer key: keys){
	            System.out.println("Value of "+key+" is: "+ resultFieldClasses.get(key).VariableName+ "   "+resultFieldClasses.get(key).OwnerClass.toString()+ "   "+resultFieldClasses.get(key).fieldType.toString());
	        }
	
		 	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        //METHODSHASHMAP
		 rowcount = null; 
		 var = st.executeQuery("select count(*) from methods"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
			}
		System.out.println("ROW COUNT::::::"+rowcount); 
		 rowcountint= Integer.parseInt(rowcount); 
		
		 id=null;
		 classname=null; 
		 String methodname = null; 
			 row=1; 
				
				while(row<=rowcountint) {
					ResultSet methods = st.executeQuery("SELECT methods.* from methods where id='"+row+"'"); 
					
					
					while(methods.next() ){
						id = methods.getString("id"); 
						int ID2 = Integer.parseInt(id); 
						methodname = methods.getString("methodname"); 
						String classid = methods.getString("classid"); 
						
						
						
						 ResultSet myresults = st.executeQuery("SELECT classes.* from classes where id='"+classid+"'"); 
						 while(myresults.next()) {
							     id = myresults.getString("id"); 
								 ID2 = Integer.parseInt(id); 
								 classname = myresults.getString("classname"); 
								 System.out.println("HERE IS AN ID FOR THE CLASS=========> "+ID2); 
								 System.out.println("CLASSNAME=========> "+classname); 
								 Class myclass = new Class(ID2, classname); 
								 Method Method = new Method(row, methodname, myclass); 
								 MethodsHashMap.put(row, Method); 
								
									row++; 
						 }
						 
						 
						 	
						 methods = st.executeQuery("SELECT methods.* from methods where id='"+row+"'"); 
							
							 
							 
						
					}
					//fieldclasses.close();
				}
				
			
		 keys = MethodsHashMap.keySet();
		Map<Integer, Method> resultMethods = MethodsHashMap.entrySet().stream()
		                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		 for(Integer key: keys){
	            System.out.println("Value of "+key+" is: "+ resultMethods.get(key).methodName+ "   "+resultMethods.get(key).Class.ID+ "   "+resultMethods.get(key).Class.ClassName);
	        }
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        //REQUIREMENTSHASHMAP
		 rowcount = null; 
		 String requirementname=null; 
		 var = st.executeQuery("select count(*) from requirements"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
			}
		System.out.println("ROW COUNT::::::"+rowcount); 
		 rowcountint= Integer.parseInt(rowcount); 
		
		 id=null;
		 classname=null; 
		
			 row=1; 
				
				while(row<=rowcountint) {
					ResultSet requirements = st.executeQuery("SELECT requirements.* from requirements where id='"+row+"'"); 
					
					
					while(requirements.next() ){
						id = requirements.getString("id"); 
						int ID2 = Integer.parseInt(id); 
						requirementname = requirements.getString("requirementname"); 
						
						
						
						
						
						 
						 	
						
						Requirement requirement = new Requirement(row, requirementname); 	
						RequirementsHashMap.put(row, requirement);  
						row++;
						requirements = st.executeQuery("SELECT requirements.* from requirements where id='"+row+"'"); 	 
						 
					}
					//fieldclasses.close();
				}
				
			
		 keys = RequirementsHashMap.keySet();
		Map<Integer, Requirement> resultRequirements = RequirementsHashMap.entrySet().stream()
		                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		 for(Integer key: keys){
	            System.out.println("============================>Value of "+key+" is: "+ resultRequirements.get(key).RequirementName);
	        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//INTERFACES
		 rowcount = null; 
		 String interfacename=null; 
		 
		 var = st.executeQuery("select count(*) from interfaces"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
			}
		System.out.println("ROW COUNT::::::"+rowcount); 
		 rowcountint= Integer.parseInt(rowcount); 
		
		 id=null;
		 classname=null; 
		 String interfaceclassid=null; 
			 row=1; 
				
				while(row<=rowcountint) {
					ResultSet interfaces = st.executeQuery("SELECT interfaces.* from interfaces where id='"+row+"'"); 
					
					
					while(interfaces.next() ){
						id = interfaces.getString("id"); 
						int InterfaceID = Integer.parseInt(id); 
						interfacename = interfaces.getString("interfacename"); 
						 interfaceclassid = interfaces.getString("interfaceclassid"); 
						 int interfaceclassid2 = Integer.parseInt(interfaceclassid); 
						Class InterfaceClass = new Class(interfaceclassid2, interfacename); 
						
						ownerclassid = interfaces.getString("ownerclassid"); 
						int  OwnerClassID = Integer.parseInt(ownerclassid); 
						String Ownername = interfaces.getString("classname"); 
						Class OwnerClass1 = new Class(OwnerClassID, Ownername); 
						
							Interfaces myinterface = new Interfaces(row, InterfaceClass, OwnerClass1); 
						 
						 	
						
						InterfaceHashMap.put(row, myinterface);  
						row++;
						interfaces = st.executeQuery("SELECT interfaces.* from interfaces where id='"+row+"'"); 
						 
					}
					//fieldclasses.close();
				}
				
			
		 keys = InterfaceHashMap.keySet();
		Map<Integer, Interfaces> resultInterfaces = InterfaceHashMap.entrySet().stream()
		                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		 for(Integer key: keys){
	            System.out.println("============================>Value of "+key+" is: "+ resultInterfaces.get(key).InterfaceClass.ClassName+ "  "+
	            		resultInterfaces.get(key).InterfaceClass.ID+ "  "+resultInterfaces.get(key).OwnerClass.ID+ "   "+resultInterfaces.get(key).OwnerClass.ClassName);
	        }	 
		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//FIELD METHODS 		 
		 rowcount = null; 
		 String fieldmethod=null; 
		 String fieldtype=null; 
		  classname=null; 
		 var = st.executeQuery("select count(*) from fieldmethods"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
			}
		System.out.println("ROW COUNT::::::"+rowcount); 
		 rowcountint= Integer.parseInt(rowcount); 
		
		 id=null;
		 classname=null; 
		 String fieldmethodid=null; 
		 String ownermethodid;
		 int ownermethodid2; 
		 int ownerclassid2; 
		String fieldaccess=null; 
		
			 row=1; 
				
				while(row<=rowcountint) {
					ResultSet fieldmethods = st.executeQuery("SELECT fieldmethods.* from fieldmethods where id='"+row+"'"); 
					
					
					while(fieldmethods.next() ){
						id = fieldmethods.getString("id"); 
						int fieldmethodID = Integer.parseInt(id); 
						
						fieldtypeclassid = fieldmethods.getString("fieldtypeclassid"); 
						int fieldtypeclassid2=Integer.parseInt(fieldtypeclassid); 
						
						
						fieldtype = fieldmethods.getString("fieldtype");
						
						
						 fieldaccess = fieldmethods.getString("fieldaccess"); 
						 
						 
					
						 
						
						classname = fieldmethods.getString("classname");
						
						ownerclassid = fieldmethods.getString("ownerclassid");
						ownerclassid2= Integer.parseInt(ownerclassid); 
						
						methodname = fieldmethods.getString("methodname");
						
						ownermethodid = fieldmethods.getString("ownermethodid");
						ownermethodid2= Integer.parseInt(ownermethodid); 
						Class variable= new Class(fieldtypeclassid2, fieldtype); 
						Class myclass= new Class(ownerclassid2, classname); 
						Method method = new Method(ownermethodid2, methodname, myclass); 
						FieldMethods FieldMethods= new FieldMethods(row, fieldaccess, variable,  myclass, method); 
						 	
						
						FieldMethodsHashMap.put(row, FieldMethods);  
						row++;
						fieldmethods = st.executeQuery("SELECT fieldmethods.* from fieldmethods where id='"+row+"'"); 
						 
					}
					//fieldclasses.close();
				}
				
			
		 keys = FieldMethodsHashMap.keySet();
		Map<Integer, FieldMethods> resultFieldMethods = FieldMethodsHashMap.entrySet().stream()
		                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		 for(Integer key: keys){
	            System.out.println("============================>Value of "+resultFieldMethods.get(key).ID+" is: " + "  fieldaccess  "  +resultFieldMethods.get(key).VaribaleName +"   " +resultFieldMethods.get(key).Variable.ID+ "  "+resultFieldMethods.get(key).Variable.ClassName+ "  "+
	            		resultFieldMethods.get(key).fieldType.ID+ "  "+resultFieldMethods.get(key).fieldType.ClassName+ "   "+resultFieldMethods.get(key).method.ID+ "   "+
	            		resultFieldMethods.get(key).method.methodName+ " ");
	        }	 
		
		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//METHOD CALLS 
		 rowcount = null; 
		 String CallerMethodID=null; 
			String callername=null; 
			String callerclass=null; 
		// String CalleeMethodID=null; 
				//String calleename=null; 
				//String calleeclass=null; 
				int CallerMethodID2; 
				//int CalleeMethodID2; 
				MethodCalls METHODCALLS=null; 
		  classname=null; 
		 var = st.executeQuery("select count(*) from methodcalls"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
			}
		System.out.println("ROW COUNT::::::"+rowcount); 
		 rowcountint= Integer.parseInt(rowcount); 
		
		 id=null;
		 classname=null; 
		
		
			 row=1; 
			 Class calleeCLASS = null; 
			 Class callerCLASS = null; 
			 while(row<=rowcountint) {
				 ResultSet methodcalls = st.executeQuery("SELECT methodcalls.* from methodcalls where id='"+row+"'"); 


				 while(methodcalls.next() ){
					 id = methodcalls.getString("id"); 
					 int methodcallsID = Integer.parseInt(id); 

					 CallerMethodID= methodcalls.getString("callermethodid"); 
					 CallerMethodID2= Integer.parseInt(CallerMethodID); 
					 callername= methodcalls.getString("callername"); 
					 callerclass= methodcalls.getString("callerclass"); 
					 ResultSet classes = st2.executeQuery("SELECT classes.* from classes where classname='"+callerclass+"'"); 
					 while(classes.next()) {
						 String classid= classes.getString("id"); 
						 int classid2= Integer.parseInt(classid); 
						 callerCLASS= new Class(classid2, callerclass);							
					 }
					 
					 String	 CalleeMethodID= methodcalls.getString("calleemethodid"); 
					 int CalleeMethodID2= Integer.parseInt(CalleeMethodID); 
					 String	 calleename= methodcalls.getString("calleename"); 
					 String	 calleeclass= methodcalls.getString("calleeclass"); 
					 classes = st.executeQuery("SELECT classes.* from classes where classname='"+calleeclass+"'"); 
					 while(classes.next()) {
						 String classid= classes.getString("id"); 
						 int classid2= Integer.parseInt(classid); 
						 calleeCLASS= new Class(classid2, calleeclass); 

					 }
					 Method MethodCaller= new Method(CallerMethodID2,callername, callerCLASS ); 
					 Method MethodCallee= new Method(CalleeMethodID2,calleename, calleeCLASS ); 


					 METHODCALLS= new MethodCalls(row, MethodCaller, MethodCallee); 


					 MethodCallsHashMap.put(row, METHODCALLS);  
					 row++;
					 methodcalls = st.executeQuery("SELECT methodcalls.* from methodcalls where id='"+row+"'"); 

				 }
				 //fieldclasses.close();
			 }
				
			
		 keys = MethodCallsHashMap.keySet();
		Map<Integer, MethodCalls> MethodCallsMap = MethodCallsHashMap.entrySet().stream()
		                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		 for(Integer key: keys){
	            System.out.println("============================>Value of "+MethodCallsMap.get(key).ID+" is: " + "  fieldaccess  "  +MethodCallsMap.get(key).CalleeMethod.ID +"   " +MethodCallsMap.get(key).CalleeMethod.methodName+ 
	            		"  "+MethodCallsMap.get(key).CalleeMethod.Class.ClassName+ "  "+		"  "+MethodCallsMap.get(key).CallerMethod.ID+ "  "+
	            		MethodCallsMap.get(key).CallerMethod.methodName+ 
	            		"  "+MethodCallsMap.get(key).CallerMethod.Class.ClassName);
	        }	 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//METHOD CALLS EXECUTED
		  rowcount = null; 
		 String CallerMethodIDEXECUTED=null; 
			String callernameEXECUTED=null; 
			String callerclassEXECUTED=null; 
		// String CalleeMethodID=null; 
				//String calleename=null; 
				//String calleeclass=null; 
				int CallerMethodID2EXECUTED; 
				//int CalleeMethodID2; 
				MethodCalls METHODCALLSEXECUTED=null; 
		  classname=null; 
		
		// int CalleeMethodID2;
		
		  
		  
		  
		  
		var = st.executeQuery("select count(*) from methodcallsexecuted");
		while (var.next()) {
			rowcount = var.getString("count(*)");
		}
		System.out.println("ROW COUNT::::::" + rowcount);
		rowcountint = Integer.parseInt(rowcount);

		id = null;
		classname = null;

		row = 1;
		Class calleeCLASSEXECUTED = null;
		Class callerCLASSEXECUTED = null;
	
		while (row <= rowcountint) {
			System.out.println("THIS ROW: =====================>"+row);
			ResultSet methodcallsEXECUTED = st.executeQuery("SELECT methodcallsexecuted.* from methodcallsexecuted where id='" + row + "'");

			while (methodcallsEXECUTED.next()) {
				id = methodcallsEXECUTED.getString("id");
				int methodcallsIDEXECUTED = Integer.parseInt(id);

				CallerMethodID = methodcallsEXECUTED.getString("callermethodid");
				CallerMethodID2 = Integer.parseInt(CallerMethodID);
				callername = methodcallsEXECUTED.getString("callername");
				callerclass = methodcallsEXECUTED.getString("callerclass");
				ResultSet classes = st2
						.executeQuery("SELECT classes.* from classes where classname='" + callerclass + "'");
				while (classes.next()) {
					String classid = classes.getString("id");
					int classid2 = Integer.parseInt(classid);
					callerCLASS = new Class(classid2, callerclass);
				}

				String CalleeMethodID = methodcallsEXECUTED.getString("calleemethodid");
				int CalleeMethodID2 = Integer.parseInt(CalleeMethodID);
				String calleename = methodcallsEXECUTED.getString("calleename");
				String calleeclass = methodcallsEXECUTED.getString("calleeclass");
				classes = st2.executeQuery("SELECT classes.* from classes where classname='" + calleeclass + "'");
				while (classes.next()) {
					String classid = classes.getString("id");
					int classid2 = Integer.parseInt(classid);
					calleeCLASS = new Class(classid2, calleeclass);

				}
				Method MethodCaller = new Method(CallerMethodID2, callername, callerCLASS);
				Method MethodCallee = new Method(CalleeMethodID2, calleename, calleeCLASS);

				METHODCALLS = new MethodCalls(row, MethodCaller, MethodCallee);

				MethodCallsEXECUTEDHashMap.put(row, METHODCALLS);
				row++;
				//methodcallsEXECUTED = st.executeQuery("SELECT methodcallsexecuted.* from methodcallsexecuted where id='" + row + "'");

			}
			// fieldclasses.close();
		}

		keys = MethodCallsEXECUTEDHashMap.keySet();
		Map<Integer, MethodCalls> MethodCallsEXECUTEDMap = MethodCallsEXECUTEDHashMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		for (Integer key : keys) {
			System.out.println("============================>Value of " + MethodCallsEXECUTEDMap.get(key).ID + " is: "
					+ "  fieldaccess  " + MethodCallsEXECUTEDMap.get(key).CalleeMethod.ID + "   "
					+ MethodCallsEXECUTEDMap.get(key).CalleeMethod.methodName + "  "
					+ MethodCallsEXECUTEDMap.get(key).CalleeMethod.Class.ClassName + "  " + "  "
					+ MethodCallsEXECUTEDMap.get(key).CallerMethod.ID + "  " + MethodCallsEXECUTEDMap.get(key).CallerMethod.methodName
					+ "  " + MethodCallsEXECUTEDMap.get(key).CallerMethod.Class.ClassName);
		}	 
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SUPERCLASSES
		rowcount = null; 
		
		classname=null; 

		// int CalleeMethodID2;
		String SuperClassID=null; 
		int SuperClassIDINT; 
		String SuperClassName=null; 
		String ChildClassName=null; 
		String ChildClassID; 
		int ChildClassIDINT; 
		var = st.executeQuery("select count(*) from superclasses");
		while (var.next()) {
			rowcount = var.getString("count(*)");
		}
		System.out.println("ROW COUNT::::::" + rowcount);
		rowcountint = Integer.parseInt(rowcount);

		id = null;
		classname = null;

		row = 1;
	

		while (row <= rowcountint) {
			System.out.println("THIS ROW: =====================>"+row);
			ResultSet superclasses = st.executeQuery("SELECT superclasses.* from superclasses where id='" + row + "'");

			while (superclasses.next()) {
				SuperClassID = superclasses.getString("superclassid");
				SuperClassIDINT = Integer.parseInt(SuperClassID);

				
				SuperClassName = superclasses.getString("superclassname");
				
				
				ChildClassID = superclasses.getString("ownerclassid");
				ChildClassIDINT = Integer.parseInt(ChildClassID);

				
				ChildClassName = superclasses.getString("childclassname");
				
				
				
			
				
				Class SuperClass= new Class(SuperClassIDINT, SuperClassName); 
				Class ChildClass= new Class(ChildClassIDINT, ChildClassName); 
				SuperClasses SUPERCLASSES = new SuperClasses(row, SuperClass, ChildClass); 

				SuperClassesHashMap.put(row, SUPERCLASSES);
				row++;
				//methodcallsEXECUTED = st.executeQuery("SELECT methodcallsexecuted.* from methodcallsexecuted where id='" + row + "'");

			}
			// fieldclasses.close();
		}

		keys = SuperClassesHashMap.keySet();
		Map<Integer, SuperClasses> SuperClassesMap = SuperClassesHashMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		for (Integer key : keys) {
			System.out.println("============================>Value of " + SuperClassesMap.get(key).ID + " is: "
					+ "  fieldaccess  " + SuperClassesMap.get(key).ParentClass.ID + "   "
					+ SuperClassesMap.get(key).ParentClass.ClassName + "  "
					+ SuperClassesMap.get(key).ChildClass.ID + "  " + "  "
					+ SuperClassesMap.get(key).ChildClass.ClassName + "  " );
		}	
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// PARAMETERS
		rowcount = null;

		classname = null;
		int ParameterIDINT; 
		int parameterclassidINT;  
		// int CalleeMethodID2;
		String parameterclassid; 
		String parameterName ;
		String parameterType; 
		String classid; 
	
		String isReturn; 
		int isReturnINT; 
		int classidINT; 
		String methodid; 
		int methodidINT; 
		var = st.executeQuery("select count(*) from parameters");
		while (var.next()) {
			rowcount = var.getString("count(*)");
		}
		System.out.println("ROW COUNT::::::" + rowcount);
		rowcountint = Integer.parseInt(rowcount);

		id = null;
		classname = null;

		row = 1;

		while (row <= rowcountint) {
			System.out.println("THIS ROW: =====================>" + row);
			ResultSet parameters = st.executeQuery("SELECT parameters.* from parameters where id='" + row + "'");

			while (parameters.next()) {
				String parameterID = parameters.getString("id");
				ParameterIDINT = Integer.parseInt(parameterID);

				 parameterName = parameters.getString("parametername");

				
				 parameterType = parameters.getString("parametertype");
				 
				 parameterclassid = parameters.getString("parameterclass");
				parameterclassidINT = Integer.parseInt(parameterclassid);

				 classid = parameters.getString("classid");
				 classidINT = Integer.parseInt(classid); 
				 
				 classname = parameters.getString("classname");
				 
				 
				 methodid = parameters.getString("methodid");
				 methodidINT = Integer.parseInt(methodid); 
				 
				 
				 methodname = parameters.getString("methodname");
				
				 isReturn = parameters.getString("isreturn");
				 isReturnINT= Integer.parseInt(isReturn); 
				 
				 Class ParameterClass= new Class(parameterclassidINT, parameterType); 
				 Class OwnerClassParam= new Class(classidINT, classname); 
				 Method parameterMethod= new Method(methodidINT, methodname, OwnerClassParam); 

				 
				 Parameter param= new Parameter(row, parameterName, ParameterClass, parameterMethod, isReturnINT); 
				ParameterHashMap.put(row, param);
				row++;
				// methodcallsEXECUTED = st.executeQuery("SELECT methodcallsexecuted.* from
				// methodcallsexecuted where id='" + row + "'");

			}
			// fieldclasses.close();
		}

		keys = ParameterHashMap.keySet();
		Map<Integer, Parameter> ParametersMap = ParameterHashMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		for (Integer key : keys) {
			System.out.println("============================>ID:  " + ParametersMap.get(key).ID + " is: "
					+ " ----- parameter name  " + ParametersMap.get(key).ParameterName + "  parameterclassID "
					+ ParametersMap.get(key).ParameterClass.ID + " parameter classname " + ParametersMap.get(key).ParameterClass.ClassName 
				
					+
					" ----- Parameter METHOD ID  "+ ParametersMap.get(key).Method.ID +
					" ------ Parameter METHOD Name  "+ ParametersMap.get(key).Method.methodName+
					"  ------Parameter METHOD Name Class id  "+ ParametersMap.get(key).Method.Class.ID
					+
					" ------ Parameter METHOD Name Class name  "+ ParametersMap.get(key).Method.Class.ClassName+
					"-------- Parameter isreturn: "+ ParametersMap.get(key).IsReturn);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SUPERCLASSES
		rowcount = null;
		int classidINT1; 
		classname = null;
		String requirementid; 
		int requirementidINT; 
		// int CalleeMethodID2;
		String goldTraceClass; 
		String subjectTraceClass; 
		String classnameTraceClass; 
		var = st.executeQuery("select count(*) from tracesclasses");
		while (var.next()) {
			rowcount = var.getString("count(*)");
		}
		System.out.println("ROW COUNT::::::" + rowcount);
		rowcountint = Integer.parseInt(rowcount);

		id = null;
		String classnameTrace = null;

		row = 1;

		while (row <= rowcountint) {
			System.out.println("THIS ROW: =====================>" + row);
			ResultSet tracesclasses = st.executeQuery("SELECT tracesclasses.* from tracesclasses where id='" + row + "'");

			while (tracesclasses.next()) {
				requirementid = tracesclasses.getString("requirementid");
				requirementidINT = Integer.parseInt(requirementid);

				requirementname = tracesclasses.getString("requirement");

				classid = tracesclasses.getString("classid");
				classidINT1 = Integer.parseInt(classid);

				classnameTraceClass = tracesclasses.getString("classname");
				
				goldTraceClass = tracesclasses.getString("gold");
				
				subjectTraceClass = tracesclasses.getString("subject");

				Requirement req = new Requirement(requirementidINT, requirementname);
				Class myclass = new Class(classidINT1, classnameTraceClass);
				TraceClass mytraceclass= new TraceClass(row, req, myclass, goldTraceClass, subjectTraceClass); 

				TraceClassHashMap.put(row, mytraceclass);
				row++;
				// methodcallsEXECUTED = st.executeQuery("SELECT methodcallsexecuted.* from
				// methodcallsexecuted where id='" + row + "'");

			}
			// fieldclasses.close();
		}

		keys = TraceClassHashMap.keySet();
		Map<Integer, TraceClass> TracesClassMap = TraceClassHashMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		for (Integer key : keys) {
			System.out.println("============================>Value of " + TracesClassMap.get(key).ID + " is: "
					+ "  fieldaccess  " + TracesClassMap.get(key).myclass.ID+ "   "
					+ TracesClassMap.get(key).myclass.ClassName + "  " + TracesClassMap.get(key).requirement.ID
					+ "  " + "  " + TracesClassMap.get(key).requirement.RequirementName + "  " + TracesClassMap.get(key).gold+ "  " + TracesClassMap.get(key).subject+"  ");
		}
	}
	public static void main(String[] args) throws SQLException {
	
		// Connect to MySQL
		Connection conn = null;
		DatabaseReading DatabaseReading= new DatabaseReading(); 
		conn = DatabaseReading.getConnection();
		Statement st= conn.createStatement();
		Statement st2= conn.createStatement();
		// TODO Auto-generated method stub
		Read(st, st2);
	}

	
	
}

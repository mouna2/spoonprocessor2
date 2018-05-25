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

	
	public static void Read(java.sql.Statement st) throws SQLException {
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
							 
							 FieldTypeClass Fieldtype= new FieldTypeClass(fieldname, FieldTypeClass, OwnerClass); 
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
		
		 for(Integer key: keys){
	            System.out.println("Value of "+key+" is: "+ resultFieldClasses.get(key).VariableName+ "   "+resultFieldClasses.get(key).ClassLocation+ "   "+resultFieldClasses.get(key).fieldType);
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
								 Method Method = new Method(methodname, myclass); 
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
						
						
						
						
						
						 
						 	
						
						Requirement requirement = new Requirement(requirementname); 	
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
		 
		
	}
	public static void main(String[] args) throws SQLException {
	
		// Connect to MySQL
		Connection conn = null;
		DatabaseReading DatabaseReading= new DatabaseReading(); 
		conn = DatabaseReading.getConnection();
		Statement st= conn.createStatement();
		// TODO Auto-generated method stub
		Read(st);
	}

}

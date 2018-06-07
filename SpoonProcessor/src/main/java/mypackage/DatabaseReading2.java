package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import Tables.CallerIDName;
import Tables.tracesmethods;
import Tables.tracesmethodscallees;
import mainPackage.ClassRepresentation;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.CtModel;
import spoon.reflect.factory.ClassFactory;

public class DatabaseReading2 {
	public static  HashMap<Integer, String> classesHashMap = new HashMap<Integer, String>();
	
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

	public static void main (String [] args) throws SQLException {
		Connection conn = null;
		DatabaseReading2 DatabaseReading= new DatabaseReading2(); 
		conn = DatabaseReading.getConnection();
		Statement st= conn.createStatement();
		Statement st2= conn.createStatement();
		int index=0;
		mypackage.ClassRepresentation2 classrep= new mypackage.ClassRepresentation2(); 
		
		HashMap<Integer, mypackage.ClassRepresentation2> ClassRepresentationHashMap = classrep.ReadClassesRepresentations(conn); 
		Set<Integer> keys = ClassRepresentationHashMap.keySet();
		 for(Integer key: keys){
	            System.out.println("Value of "+key+" is: "+ ClassRepresentationHashMap.get(key).classid+ "   "+ClassRepresentationHashMap.get(key).classname+ "   ");
	        }
		 
		 
		 
		 ///////////////////////////////////////////////////////////////////////////////////////
			mypackage.ClassDetails2 classdet= new mypackage.ClassDetails2(); 
			
			HashMap<Integer, mypackage.ClassDetails2> ClassDetailsHashMap = classdet.ReadClassesRepresentations(conn); 
			 keys = ClassRepresentationHashMap.keySet();
			 for(Integer key: keys){
		            System.out.println("Value of "+key+" is: "+ ClassRepresentationHashMap.get(key).classid+ "   "+ClassRepresentationHashMap.get(key).classname+ "   ");
		        }
			 
			
			 
		///////////////////////////////////////////////////////////////////////////////////////
				Requirement2 req= new Requirement2(); 
				
				 HashMap<String, Requirement2> RequirementHashMap = req.ReadClassesRepresentations(conn); 
				 Set<String> keys2 = RequirementHashMap.keySet();
				 for(String key: keys2){
			            System.out.println("Value of "+key+" is: "+ RequirementHashMap.get(key).ID+ "   "+RequirementHashMap.get(key).RequirementName+ "   ");
			        }
		///////////////////////////////////////////////////////////////////////////////////////
			Method2Details methoddet2= new Method2Details(); 
			HashMap<Integer, Method2Details> methodhashmap = methoddet2.ReadClassesRepresentations(conn); 
				 
			ClassDetails2 classdet2= new ClassDetails2(); 
			HashMap<Integer, ClassDetails2> classhashmap = classdet2.ReadClassesRepresentations(conn); 	
			
			///////////////////////////////////////////////////////////////////////////////////////
			
			ClassTrace2 myclasstrace2= new ClassTrace2(); 
			HashMap<Integer, ClassTrace2> classtracehashmap = myclasstrace2.ReadClassesRepresentations(conn); 
			
			
			///////////////////////////////////////////////////////////////////////////////////////
			
			MethodTrace2 methodtrace2= new MethodTrace2(); 
			HashMap<Integer, MethodTrace2> methodtracehashmap = methodtrace2.ReadClassesRepresentations(conn); 
			
			System.out.println("MOUNA");		
	}
/********************************************************************************************************************************/
/********************************************************************************************************************************/
/********************************************************************************************************************************/

	

/********************************************************************************************************************************/
/********************************************************************************************************************************/
/********************************************************************************************************************************/	
	
	
	
	
	
	
}



package mypackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassRepresentation2 {

	public String classid; 
	public String classname;
	 HashMap<Integer, ClassRepresentation2> ClassRepresentationHashMap= new HashMap<Integer, ClassRepresentation2>(); 

	
	public ClassRepresentation2() {
		super();
	}


	public ClassRepresentation2(String classid, String classname) {
		super();
		this.classid = classid;
		this.classname = classname;
	}


	public String getClassid() {
		return classid;
	}


	public void setClassid(String classid) {
		this.classid = classid;
	}


	public String getClassname() {
		return classname;
	}


	public void setClassname(String classname) {
		this.classname = classname;
	} 
	public  HashMap<Integer, ClassRepresentation2> ReadClassesRepresentations(Connection conn) throws SQLException {
		// Rule: if method A calls method B and method A implements requirement X, then I can just assume that method B implements requirement X as well 
		// Retrieving the calleeid
		DatabaseReading2 db = new DatabaseReading2(); 
	
		//CLASSESHASHMAP
		String rowcount = null; 
		Statement st = conn.createStatement();
		ResultSet var = st.executeQuery("select count(*) from classes"); 
		while(var.next()){
			rowcount = var.getString("count(*)");
		}
		System.out.println("ROW COUNT::::::"+rowcount); 
		int rowcountint= Integer.parseInt(rowcount); 
	
		int index=1; 
		 ResultSet myresults = st.executeQuery("SELECT classes.* from classes "); 
		 while(myresults.next()) {
			     String id = myresults.getString("id"); 			
				 String classname = myresults.getString("classname"); 
				
				 ClassRepresentation2 classdetails= new ClassRepresentation2(id, classname); 
				 ClassRepresentationHashMap.put(index, classdetails); 
				 index++; 
		 }
		 Set<Integer> keys = ClassRepresentationHashMap.keySet();
			Map<Integer, ClassRepresentation2> resultFieldClasses = ClassRepresentationHashMap.entrySet().stream()
			                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
			                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
			                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			System.out.println("FIELD TYPE CLASSES");
			/* for(Integer key: keys){
		            System.out.println("Value of "+key+" is: "+ resultFieldClasses.get(key).classid+ "   "+resultFieldClasses.get(key).classname+ "   ");
		        }*/
		
		 return ClassRepresentationHashMap; 
	}
	
}

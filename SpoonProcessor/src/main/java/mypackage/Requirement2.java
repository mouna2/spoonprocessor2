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

public class Requirement2 {
	String ID; 
	public String RequirementName;
	public HashMap<String, Requirement2> RequirementsHashMap= new HashMap<String, Requirement2>(); 
	
	public Requirement2(String iD, String requirementName) {
		super();
		ID = iD;
		RequirementName = requirementName;
	}
	public Requirement2() {
		// TODO Auto-generated constructor stub
	}
	public String getID() {
		return ID;
	}
	public void setID(String string) {
		ID = string;
	}
	public String getRequirementName() {
		return RequirementName;
	}
	public void setRequirementName(String requirementName) {
		RequirementName = requirementName;
	}

	public  HashMap<String, Requirement2> ReadClassesRepresentations(Connection conn) throws SQLException {
		DatabaseReading2 db = new DatabaseReading2(); 
		
		//CLASSESHASHMAP
		String rowcount = null; 
		Statement st = conn.createStatement();
		ResultSet var  = st.executeQuery("select count(*) from requirements"); 
	while(var.next()){
		rowcount = var.getString("count(*)");
		}
	System.out.println("ROW COUNT::::::"+rowcount); 
	String index="1"; 
	ResultSet requirements = st.executeQuery("SELECT requirements.* from requirements"); 
	 String id=null;
	 String classname=null; 

		

		


				while(requirements.next() ){
					id = requirements.getString("id"); 
					
					String requirementname = requirements.getString("requirementname"); 








					Requirement2 requirement = new Requirement2(id, requirementname); 	
					RequirementsHashMap.put(id, requirement);  
						 

				}
				//fieldclasses.close();
		


	 Set<String> keys = RequirementsHashMap.keySet();
	Map<String, Requirement2> resultRequirements = RequirementsHashMap.entrySet().stream()
	                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

	 for(String key: keys){
            System.out.println("============================>Value of "+key+" is: "+ resultRequirements.get(key).RequirementName);
        }
	 return RequirementsHashMap; 
	}
	
}

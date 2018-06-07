package mypackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MethodTrace2 {
	
	public Method2Representation MethodRepresentation; 
	public Requirement2 Requirement; 
	public ClassRepresentation2 ClassRepresentation; 
	public String gold; 
	public String subject;
	public String goldprediction; 
	
	public ArrayList<MethodTrace2> methodtraces; 
	
	public String getGoldprediction() {
		return goldprediction;
	}

	public void setGoldprediction(String goldprediction) {
		this.goldprediction = goldprediction;
	}

	HashMap<Integer, MethodTrace2> methodtraceHashMap= new HashMap<Integer, MethodTrace2> (); 
	
	public MethodTrace2() {
		super();
	}

	public Method2Representation getMethodRepresentation() {
		return MethodRepresentation;
	}

	public void setMethodRepresentation(Method2Representation methodRepresentation) {
		MethodRepresentation = methodRepresentation;
	}

	public Requirement2 getRequirement() {
		return Requirement;
	}

	public void setRequirement(Requirement2 requirement) {
		Requirement = requirement;
	}

	public ClassRepresentation2 getClassRepresentation() {
		return ClassRepresentation;
	}

	public void setClassRepresentation(ClassRepresentation2 classRepresentation) {
		ClassRepresentation = classRepresentation;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public MethodTrace2(Method2Representation methodRepresentation, Requirement2 requirement,
			ClassRepresentation2 classRepresentation, String gold, String subject) {
		super();
		MethodRepresentation = methodRepresentation;
		Requirement = requirement;
		ClassRepresentation = classRepresentation;
		this.gold = gold;
		this.subject = subject;
	}
	
	public  HashMap<Integer, MethodTrace2> ReadClassesRepresentations(Connection conn) throws SQLException {
		DatabaseReading2 db = new DatabaseReading2(); 
		ClassDetails2 classdet= new ClassDetails2(); 
		//CLASSESHASHMAP
		
		Statement st = conn.createStatement();
		ResultSet var = st.executeQuery("select count(*) from classes"); 
		
	
		int index=1; 
		 ResultSet myresults = st.executeQuery("SELECT traces.* from traces where id='"+ index +"'"); 
		 while(myresults.next()) {
			 MethodTrace2 mytrace= new MethodTrace2(); 
			 Requirement2 requirement = new Requirement2(); 
			 requirement.setID(myresults.getString("requirementid"));
			 requirement.setRequirementName(myresults.getString("requirement"));
			 mytrace.setRequirement(requirement);
			 
			 ClassRepresentation2 classrep = new ClassRepresentation2(); 
			 classrep.setClassid(myresults.getString("classid"));
			 classrep.setClassname(myresults.getString("classname"));
			 
			 Method2Representation methodrep= new Method2Representation(); 
			 methodrep.setMethodid(myresults.getString("methodid"));
			 methodrep.setMethodname(myresults.getString("method"));
			 mytrace.setMethodRepresentation(methodrep);
			 
			 mytrace.setClassRepresentation(classrep);
			 
			 mytrace.setGold(myresults.getString("gold"));
			 
			 mytrace.setSubject(myresults.getString("subject"));
			 methodtraceHashMap.put(index, mytrace); 
			 index++; 
			 myresults = st.executeQuery("SELECT traces.* from traces where id='"+ index +"'"); 
		 }
		 
		return methodtraceHashMap;
	}
	
	public void getElement(List<MethodTrace2> methodtraces2, String ID, String goldpred) {
		for(MethodTrace2 methodtrace: methodtraces2) {
			if(methodtrace.getMethodRepresentation().methodid.equals(ID)) {
				methodtrace.setGoldprediction(goldpred);
			}
		}
		
		
		
	}
}

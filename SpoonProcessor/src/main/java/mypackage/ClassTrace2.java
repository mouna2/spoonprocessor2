package mypackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ClassTrace2 {
	String ID; 
		Requirement2 requirement; 
		ClassRepresentation2 myclass; 
		String gold; 
		String subject;
		
		
		
		HashMap<Integer, ClassTrace2> classtraceHashMap= new HashMap<Integer, ClassTrace2> (); 
		
		
		public ClassTrace2(String iD, Requirement2 requirement, ClassRepresentation2 myclass, String gold, String subject) {
			super();
			ID = iD;
			this.requirement = requirement;
			this.myclass = myclass;
			this.gold = gold;
			this.subject = subject;
		}
		
		public ClassTrace2() {
			// TODO Auto-generated constructor stub
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public Requirement2 getRequirement() {
			return requirement;
		}
		public void setRequirement(Requirement2 requirement) {
			this.requirement = requirement;
		}
		public ClassRepresentation2 getMyclass() {
			return myclass;
		}
		public void setMyclass(ClassRepresentation2 myclass) {
			this.myclass = myclass;
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
		
		
		public  HashMap<Integer, ClassTrace2> ReadClassesRepresentations(Connection conn) throws SQLException {
			DatabaseReading2 db = new DatabaseReading2(); 
			ClassDetails2 classdet= new ClassDetails2(); 
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
			 ResultSet myresults = st.executeQuery("SELECT tracesclasses.* from tracesclasses where id='"+ index +"'"); 
			 while(myresults.next()) {
				 ClassTrace2 myclasstrace= new ClassTrace2(); 
				 Requirement2 requirement = new Requirement2(); 
				 
				 
				 
			 }
			 
			return classtraceHashMap;
		}
		
}

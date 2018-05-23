package mainPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Tables.CallerIDName;
import Tables.tracesmethods;
import Tables.tracesmethodscallees;
import spoon.reflect.factory.ClassFactory;

public class TracesProcessing {
	
	 List<tracesmethodscallees> TracesCalleesList= new ArrayList<tracesmethodscallees>();
	 List<tracesmethodscallees> TracesCallersList= new ArrayList<tracesmethodscallees>();
	
	 List<tracesmethods> TracesList = new ArrayList<tracesmethods>(); 
	public void ProcessTraces(java.sql.Statement st, ClassFactory classfactory) throws SQLException {
		// Rule: if method A calls method B and method A implements requirement X, then I can just assume that method B implements requirement X as well 
		// Retrieving the calleeid
		
		String rowcount = null; 
		ResultSet var = st.executeQuery("select count(*) from traces"); 
		while(var.next()){
			rowcount = var.getString("count(*)"); }
		System.out.println("ROW COUNT::::::"+rowcount); 
		int rowcountint= Integer.parseInt(rowcount); 
		int row=0; 
		String id=null;
		

		
		while(row<=rowcountint) {
			 List<CallerIDName>  Callers= new ArrayList<CallerIDName>(); 
			 List<CallerIDName>  CallersExecuted= new ArrayList<CallerIDName>(); 
			 List<CallerIDName>  Callees= new ArrayList<CallerIDName>(); 
			 List<CallerIDName>  CalleesExecuted= new ArrayList<CallerIDName>(); 
			
			
			
			ResultSet ids = st.executeQuery("SELECT traces.id from traces where id='"+row+"'"); 
			while(ids.next()){
				id = ids.getString("id"); }
			
			String methodid=null; 
		
			ResultSet tacemethodids = st.executeQuery("SELECT traces.methodid from traces  where id='"+row+"'"); 
			while(tacemethodids.next()){
				methodid = tacemethodids.getString("methodid"); }
			 
		
			String requirementid=null; 
			
			ResultSet requirements = st.executeQuery("SELECT traces.requirementid from traces  where id='"+row+"'"); 
			while(requirements.next()){
				requirementid = requirements.getString("requirementid"); }
			
			
	String requirement=null; 
			
			ResultSet requirementnames = st.executeQuery("SELECT traces.requirement from traces where id='"+row+"'"); 
			while(requirementnames.next()){
				requirement = requirementnames.getString("requirement"); }
		
			
	String shortmethod=null; 
			
			ResultSet shortmethods = st.executeQuery("SELECT traces.method from traces where id='"+row+"'"); 
			while(shortmethods.next()){
				shortmethod = shortmethods.getString("method"); 
				System.out.println("SHORT METHOD"+ shortmethod); 
			}
			
	String classname=null; 
			
			ResultSet classnames = st.executeQuery("SELECT traces.classname from traces where id='"+row+"'"); 
			while(classnames.next()){
				classname = classnames.getString("classname"); }
			

			
			
			
	String gold=null; 
			
			ResultSet golds = st.executeQuery("SELECT traces.gold from traces where id='"+row+"'"); 
			while(golds.next()){
				gold = golds.getString("gold"); }	
			
	String subject=null; 
			
			ResultSet subjects = st.executeQuery("SELECT traces.subject from traces where id='"+row+"'"); 
			while(subjects.next()){
				subject = subjects.getString("subject"); }		
			
	String classid=null; 
			
			ResultSet classids = st.executeQuery("SELECT traces.classid from traces where id='"+row+"'"); 
			while(classids.next()){
				classid = classids.getString("classid"); 
				
			}	
			
			
			String calleename=null; 
			String calleeid=null; 
				ResultSet calleesparsed = st.executeQuery("SELECT methodcalls.* from methodcalls where methodcalls.callermethodid ='"+methodid+"'"); 
				while(calleesparsed.next()){
					 calleeid = calleesparsed.getString("calleemethodid");
					 calleename = calleesparsed.getString("calleename"); 	 
					 System.out.println("calleename: "+calleename+ "calleeid: "+calleeid); 
					 CallerIDName call= new CallerIDName(calleeid.toString(), calleename); 
					 Callees.add(call); 
					 }
				
				
			
				String calleeidexecuted = null; 	
				String calleenameexecuted = null; 	
				ResultSet calleesexecuted = st.executeQuery("SELECT methodcallsexecuted.* from methodcallsexecuted where methodcallsexecuted.callermethodid ='"+methodid+"'"); 
				while(calleesexecuted.next()){
					 calleeidexecuted = calleesexecuted.getString("calleemethodid"); 
					 calleenameexecuted = calleesexecuted.getString("calleename"); 	 
					 System.out.println("calleename: "+calleename+ "calleeid: "+calleeidexecuted); 
					 CallerIDName call= new CallerIDName(calleeidexecuted.toString(), calleenameexecuted); 
					 CalleesExecuted.add(call); 
				}
				
				String callername=null; 
				String	callerid=null; 
				ResultSet callersparsed = st.executeQuery("SELECT methodcalls.* from methodcalls where methodcalls.calleemethodid ='"+methodid+"'"); 
				while(callersparsed.next()){
					  callerid = callersparsed.getString("callermethodid"); 
					  callername = callersparsed.getString("callername"); 
					  System.out.println("callerid: "+callerid+ "callername: "+callername); 
					  CallerIDName call= new CallerIDName(callerid.toString(), callername); 
					  Callers.add(call); 
				}
				
				
				String	callerexecutedid=null; 	
				String	callerexecutedname=null; 	   
				ResultSet callersexecuted = st.executeQuery("SELECT methodcallsexecuted.* from methodcallsexecuted where methodcallsexecuted.calleemethodid ='"+methodid+"'"); 
				while(callersexecuted.next()){
					 callerexecutedid = callersexecuted.getString("callermethodid"); 
					 callerexecutedname = callersexecuted.getString("callername"); 
					  System.out.println("callerexecutedid: "+callerexecutedid+ "callerexecutedname: "+callerexecutedname); 
					  CallerIDName call= new CallerIDName(callerexecutedid.toString(), callerexecutedname); 
					  CallersExecuted.add(call); 
				}
				
				
				
				

			
				tracesmethodscallees tmc;
				//insert into tracesmethodscallees a new object: if is found in the methodcalls table, then use the value from there 
			//otherwise, use the value from the methodcallsexecuted table 
				if(calleeid!=null && requirementid!=null) {
					 tmc= new tracesmethodscallees(requirement, requirementid, shortmethod, methodid, classname, classid, gold, subject, calleeid); 
					 TracesCalleesList.add(tmc); 
				}
				else if(calleeidexecuted!=null) {
					 tmc= new tracesmethodscallees(requirement, requirementid, shortmethod, methodid, classname, classid, gold, subject, calleeidexecuted); 
					 TracesCalleesList.add(tmc); 
				}
				
				if(callerid!=null && requirementid!=null) {
					 tmc= new tracesmethodscallees(requirement, requirementid, shortmethod, methodid, classname, classid, gold, subject, callerid); 
					 TracesCallersList.add(tmc); 
				}
				else if(callerexecutedid!=null) {
					 tmc= new tracesmethodscallees(requirement, requirementid, shortmethod, methodid, classname, classid, gold, subject, callerexecutedid); 
					 TracesCallersList.add(tmc); 
				}
				if(methodid!=null && requirementid!=null && classid!=null && subject!=null ) {
					tracesmethods tracem= new tracesmethods(methodid, requirement, requirementid, shortmethod, classname, classid, gold, subject, Callees, Callees.size(),  CalleesExecuted, CalleesExecuted.size(), Callers, Callers.size(), CallersExecuted
							, CallersExecuted.size()); 
					TracesList.add(tracem); 
				}
				
				
				setTracesCallersList(TracesCallersList);
				setTracesCalleesList(TracesCalleesList);
				row++; 
		}
		
		
		setTracesList(TracesList);
		
		
	}

	public List<tracesmethods> getTracesList() {
		return TracesList;
	}

	public void setTracesList(List<tracesmethods> tracesList) {
		TracesList = tracesList;
	}

	public List<tracesmethodscallees> getTracesCalleesList() {
		return TracesCalleesList;
	}
	public void setTracesCalleesList(List<tracesmethodscallees> tracesCalleesList) {
		TracesCalleesList = tracesCalleesList;
	}
	public List<tracesmethodscallees> getTracesCallersList() {
		return TracesCallersList;
	}
	public void setTracesCallersList(List<tracesmethodscallees> tracesCallersList) {
		TracesCallersList = tracesCallersList;
	}

	
		
}

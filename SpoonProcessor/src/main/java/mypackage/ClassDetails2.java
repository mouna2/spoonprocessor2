package mypackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import mypackage.*;


public class ClassDetails2 {
	ClassRepresentation2 classrep; 
	List<ClassRepresentation2> ChildClasses= new ArrayList<ClassRepresentation2>(); 
	List<ClassRepresentation2> ParentClasses=new ArrayList<ClassRepresentation2>(); 
	List<Interface2> Interfaces=new ArrayList<Interface2>(); 
	List<ClassField2> ClassFields=new ArrayList<ClassField2>(); ; 
	HashMap<Requirement2, ClassTrace2> ClassTraces= new HashMap<Requirement2, ClassTrace2> ();
	
	
	
	HashMap<Integer, ClassDetails2> ClassDetailsHashMap= new HashMap<Integer, ClassDetails2>(); 

	
	
	

	public ClassRepresentation2 getClassrep() {
		return classrep;
	}
	public void setClassrep(ClassRepresentation2 classrep) {
		this.classrep = classrep;
	}
	public List<ClassRepresentation2> getChildClasses() {
		return ChildClasses;
	}
	public void setChildClasses(List<ClassRepresentation2> childClasses) {
		ChildClasses = childClasses;
	}
	public List<ClassRepresentation2> getParentClasses() {
		return ParentClasses;
	}
	public void setParentClasses(List<ClassRepresentation2> parentClasses) {
		ParentClasses = parentClasses;
	}
	public List<Interface2> getInterfaces() {
		return Interfaces;
	}
	public void setInterfaces(List<Interface2> interfaces) {
		Interfaces = interfaces;
	}
	public List<ClassField2> getClassFields() {
		return ClassFields;
	}
	public void setClassFields(List<ClassField2> classFields) {
		ClassFields = classFields;
	}
	public HashMap<Requirement2, ClassTrace2> getClassTraces() {
		return ClassTraces;
	}
	public void setClassTraces(HashMap<Requirement2, ClassTrace2> classTraces) {
		ClassTraces = classTraces;
	}
	
	
	public ClassDetails2(ClassRepresentation2 classrep, List<ClassRepresentation2> childClasses,
			List<ClassRepresentation2> parentClasses, List<Interface2> interfaces, List<ClassField2> classFields,
			HashMap<Requirement2, ClassTrace2> classTraces, HashMap<Integer, ClassDetails2> classDetailsHashMap) {
		super();
		this.classrep = classrep;
		ChildClasses = childClasses;
		ParentClasses = parentClasses;
		Interfaces = interfaces;
		ClassFields = classFields;
		ClassTraces = classTraces;
		ClassDetailsHashMap = classDetailsHashMap;
	}
	public ClassDetails2() {
		// TODO Auto-generated constructor stub
	}
	public  HashMap<Integer, ClassDetails2> ReadClassesRepresentations(Connection conn) throws SQLException {
		// Rule: if method A calls method B and method A implements requirement X, then I can just assume that method B implements requirement X as well 
		// Retrieving the calleeid
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
		 ResultSet myresults = st.executeQuery("SELECT classes.* from classes where id='"+ index +"'"); 
		 while(myresults.next()) {
			 	 classdet= new ClassDetails2(); 
			     String id = myresults.getString("id"); 			
				 String classname = myresults.getString("classname"); 
				
				 ClassRepresentation2 classrep= new ClassRepresentation2(id, classname); 
				 classdet.setClassrep(classrep);
				 
				 this.ChildClasses= new ArrayList<ClassRepresentation2>(); 
				 ResultSet superclasses=st.executeQuery("select superclasses.* from superclasses where superclassid='" + classrep.classid+"'"); 
				 while(superclasses.next()) {
					 ClassRepresentation2 childclass= new ClassRepresentation2(); 
					 childclass.setClassid(superclasses.getString("ownerclassid"));
					 childclass.setClassname(superclasses.getString("childclassname"));
					 ChildClasses.add(childclass); 
					 classdet.setChildClasses(ChildClasses);
				 }
				 this.ParentClasses= new ArrayList<ClassRepresentation2>(); 
				  superclasses=st.executeQuery("select superclasses.* from superclasses where ownerclassid='" + classrep.classid+"'"); 
				 while(superclasses.next()) {
				 ClassRepresentation2 superclass= new ClassRepresentation2(); 
				 superclass.setClassid(superclasses.getString("superclassid"));
				 superclass.setClassname(superclasses.getString("superclassname"));
				 ParentClasses.add(superclass); 
				 classdet.setParentClasses(ParentClasses);
				 }
				 this.Interfaces= new ArrayList<Interface2>(); 
				 ResultSet interfaces=st.executeQuery("select interfaces.* from interfaces where ownerclassid='" + classrep.classid+"'"); 
				 while(interfaces.next()) {
					 Interface2 myinterface=new Interface2(); 
					 ClassRepresentation2 interfaceclass= new ClassRepresentation2(); 
					 interfaceclass.setClassid(interfaces.getString("interfaceclassid"));
					 interfaceclass.setClassname(interfaces.getString("interfacename"));
					 myinterface.setInterfaceClass(interfaceclass);
					 
					 ClassRepresentation2 ownerclass= new ClassRepresentation2(); 
					 ownerclass.setClassid(interfaces.getString("ownerclassid"));
					 ownerclass.setClassname(interfaces.getString("classname"));
					 myinterface.setOwnerClass(ownerclass);
					 Interfaces.add(myinterface); 
					 classdet.setInterfaces(Interfaces);
					
				 }
				 this.ClassFields= new ArrayList<ClassField2>(); 
				 ResultSet classfields=st.executeQuery("select fieldclasses.* from fieldclasses where ownerclassid='" + classrep.classid+"'"); 
				 while(classfields.next()) {
					 ClassField2 classfield= new ClassField2(); 
					 ClassRepresentation2 fieldtype= new ClassRepresentation2(); 
					 
					 classfield.setFieldName(classfields.getString("fieldname"));
					 classfield.setFieldName(classfields.getString("fieldname"));
					 fieldtype.setClassid(classfields.getString("fieldtypeclassid"));
					 fieldtype.setClassname(classfields.getString("fieldtype"));
					 classfield.setFieldType(fieldtype);
					 
					 ClassRepresentation2 ownerclass= new ClassRepresentation2(); 
					 ownerclass.setClassid(classfields.getString("ownerclassid"));
					 ownerclass.setClassname(classfields.getString("classname"));
					 classfield.setOwnerClass(ownerclass);
					 ClassFields.add(classfield); 
					 classdet.setClassFields(ClassFields);
				
					
				 }
				 this.ClassTraces= new HashMap<Requirement2, ClassTrace2> ();
				 ResultSet classtraces = st.executeQuery("SELECT tracesclasses.* from tracesclasses where classid ='"+classrep.classid+"'"); 
					//populateTables(classtraces, conn);
					while(classtraces.next()) {
						ClassTrace2 classtrace= new ClassTrace2();
						//classtrace.setID(classtraces.getString("id"));
						classtrace.setGold(classtraces.getString("gold"));
						classtrace.setSubject(classtraces.getString("subject"));
						Requirement2 r= new Requirement2();
						r.setID(classtraces.getString("requirementid"));
						r.setRequirementName(classtraces.getString("requirement"));
						classtrace.setRequirement(r);

						ClassRepresentation2 myclass= new ClassRepresentation2();
						myclass.setClassname(classtraces.getString("classname"));
						myclass.setClassid(classtraces.getString("classid"));
						classtrace.setMyclass(myclass);
						ClassTraces.put(r, classtrace);
						classdet.setClassTraces(ClassTraces);
					}
				 
				 ClassDetailsHashMap.put(index, classdet); 
				 index=index+1; 
				 myresults = st.executeQuery("SELECT classes.* from classes where id='"+ index +"'"); 
				 
		 }
		
		 
		 
		 
		
		 return ClassDetailsHashMap; 
	}
	
	
	
	
	
}

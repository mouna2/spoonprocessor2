package mainPackage;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class ClassRepresentation {
		int IDINT;
		String ID; 
		String ClassName;
		List<ClassRepresentation> ChildClasses; 
		List<ClassRepresentation> ParentClasses; 
		List<ClassField> ClassFields; 
		List<Interface> Interfaces; 
		HashMap<Requirement, ClassTrace> ClassTraces; 
		
		


		public String getID() {
			return ID;
		}




		public void setID(String iD) {
			ID = iD;
		}




		public String getClassName() {
			return ClassName;
		}




		public void setClassName(String className) {
			ClassName = className;
		}




		




		public List<ClassRepresentation> getParentClasses() {
			return ParentClasses;
		}




		public void setParentClasses(List<ClassRepresentation> parentClasses) {
			ParentClasses = parentClasses;
		}




		public List<ClassField> getClassFields() {
			return ClassFields;
		}




		public void setClassFields(List<ClassField> classFields) {
			ClassFields = classFields;
		}




		public List<Interface> getInterfaces() {
			return Interfaces;
		}




		public void setInterfaces(List<Interface> interfaces) {
			Interfaces = interfaces;
		}




		public HashMap<Requirement, ClassTrace> getClassTraces() {
			return ClassTraces;
		}




		public void setClassTraces(HashMap<Requirement, ClassTrace> classTraces) {
			ClassTraces = classTraces;
		}




//		public Class(int iD, String className, List<Class> childClasses, List<Class> parentClasses,
//				List<ClassField> classFields, List<Interface> interfaces,
//				HashMap<Requirement, ClassTrace> traceClasses) {
//			super();
//			ID = iD;
//			ClassName = className;
//			ChildClasses = childClasses;
//			ParentClasses = parentClasses;
//			ClassFields = classFields;
//			Interfaces = interfaces;
//			ClassTraces = traceClasses;
//		}

		
		
//		public Class(int iD, String className) {
//			super();
//			ID = iD;
//			ClassName = className;
//			
//		}

		public void addElementParentClass(ClassRepresentation classX) {
			ParentClasses.add(classX);
		}
		
		
		public void addElementChildClass(ClassRepresentation classX) {
			ChildClasses.add(classX);
		}

		public ClassRepresentation() {
			super();
		}




		@Override
		public String toString() {
			return "Class [ID=" + ID + ", ClassName=" + ClassName + "]";
		}




		public ClassRepresentation(int iDINT, String className) {
			super();
			IDINT = iDINT;
			ClassName = className;
		}




	
		
		
		
}	

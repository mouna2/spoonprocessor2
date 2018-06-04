package mainPackage;

import java.util.HashMap;
import java.util.List;

public class Class {
		int ID; 
		String ClassName;
		List<Class> ChildClasses; 
		List<Class> ParentClasses; 
		List<ClassField> ClassFields; 
		List<Interface> Interfaces; 
		HashMap<Requirement, ClassTrace> ClassTraces; 
		
		


		public Class(int iD, String className, List<Class> childClasses, List<Class> parentClasses,
				List<ClassField> classFields, List<Interface> interfaces,
				HashMap<Requirement, ClassTrace> traceClasses) {
			super();
			ID = iD;
			ClassName = className;
			ChildClasses = childClasses;
			ParentClasses = parentClasses;
			ClassFields = classFields;
			Interfaces = interfaces;
			ClassTraces = traceClasses;
		}




		@Override
		public String toString() {
			return "Class [ID=" + ID + ", ClassName=" + ClassName + "]";
		}




		public Class(int iD, String className) {
			super();
			ID = iD;
			ClassName = className;
		} 
		
		
		
		
}	

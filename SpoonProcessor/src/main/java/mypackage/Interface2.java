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

public class Interface2 {
	String ID; 
	public ClassRepresentation2 InterfaceClass; 
	public ClassRepresentation2 OwnerClass;
	public Interface2(String iD, ClassRepresentation2 interfaceClass, ClassRepresentation2 ownerClass) {
		super();
		ID = iD;
		InterfaceClass = interfaceClass;
		OwnerClass = ownerClass;
	}
	public Interface2() {
		// TODO Auto-generated constructor stub
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public ClassRepresentation2 getInterfaceClass() {
		return InterfaceClass;
	}
	public void setInterfaceClass(ClassRepresentation2 interfaceClass) {
		InterfaceClass = interfaceClass;
	}
	public ClassRepresentation2 getOwnerClass() {
		return OwnerClass;
	}
	public void setOwnerClass(ClassRepresentation2 ownerClass) {
		OwnerClass = ownerClass;
	}
	@Override
	public String toString() {
		return "Interface2 [ID=" + ID + ", InterfaceClass=" + InterfaceClass + ", OwnerClass=" + OwnerClass + "]";
	}
	 
	
	
	
}

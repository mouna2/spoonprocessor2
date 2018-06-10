import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import mypackage.DatabaseReading2;
import mypackage.Method2Representation;
import mypackage.MethodTrace2;
 
public class TableComboBoxByRow extends JFrame
{
    List<TableCellEditor> editors = new ArrayList<TableCellEditor>(4);
   static List<MethodTrace2> methodtraces2= new ArrayList<MethodTrace2>(); 
   
	
    public TableComboBoxByRow() throws SQLException
    {
    	DatabaseReading2 db = new DatabaseReading2(); 
    	DatabaseReading2.MakePredictions();
    	methodtraces2= db.getMethodtraces2(); 
    	int j=0; 
    	String[] items1 = new String [methodtraces2.size()]; 
    	String[] items2 = new String [methodtraces2.size()]; 
    	String[] items3 = new String [methodtraces2.size()]; 
    	String[] items4 = new String [methodtraces2.size()]; 
   	 Object[][] data = new Object[methodtraces2.size()][10000]; 
        // Create the editors to be used for each row
    	for(MethodTrace2 methodtrace: methodtraces2) {
    		data[j][0]= methodtrace.MethodRepresentation.getMethodid(); 
    		data[j][1]= methodtrace.MethodRepresentation.getMethodname(); 
    		data[j][2]= methodtrace.Requirement.getID(); 
    		data[j][3]= methodtrace.Requirement.getRequirementName(); 
    		data[j][4]= methodtrace.ClassRepresentation.classid; 
    		data[j][5]= methodtrace.ClassRepresentation.classname; 
    		data[j][6]= methodtrace.gold; 
    		data[j][7]= methodtrace.subject; 
    		data[j][8]= methodtrace.goldpredictionCaller; 
    		data[j][9]= methodtrace.goldpredictionCallee; 
    		
    		int i=0; 
    		items1 = new String[methodtrace.getCallersList().size()]; 
    		 for(Method2Representation caller: methodtrace.getCallersList()) {
	    		  items1[i]=caller.toString(); 
	    		  System.out.println(caller.toString());
	    		  i++; 
	    		  
	    	  }
    		// data[j][10]=items1; 
    		 int k=0; 
    		 items2 = new String[ methodtrace.getCalleesList().size()]; 
    		 for(Method2Representation caller: methodtrace.getCalleesList()) {
	    		  items2[k]=caller.toString(); 
	    		  System.out.println(caller.toString());
	    		  k++; 
	    		  
	    	  }
    		 int r=0; 
    		 items3 = new String[methodtrace.getCallersListExecuted().size()]; 
    		 for(Method2Representation caller: methodtrace.getCallersListExecuted()) {
	    		  items3[r]=caller.toString(); 
	    		  System.out.println(caller.toString());
	    		  r++; 
	    		  
	    	  }
    		 int z=0; 
    		 items4 = new String[10000]; 
    		 for(Method2Representation caller: methodtrace.getCalleesListExecuted()) {
	    		  items4[z]=caller.toString(); 
	    		  System.out.println(caller.toString());
	    		  z++; 
	    		  
	    	  }
    		 
    	
    		 
    		
    	        JComboBox comboBox1 = new JComboBox( items1 );
    	        DefaultCellEditor dce1 = new DefaultCellEditor( comboBox1 );
    	        editors.add( dce1 );
    	        
    	        JComboBox comboBox2 = new JComboBox( items2 );
    	        DefaultCellEditor dce2 = new DefaultCellEditor( comboBox2 );
    	        editors.add( dce2 );
    	 
    	        
    	        JComboBox comboBox3 = new JComboBox( items3 );
    	        DefaultCellEditor dce3 = new DefaultCellEditor( comboBox3 );
    	        editors.add( dce3 );
    	 
    	        
    	        JComboBox comboBox4 = new JComboBox( items4);
    	        DefaultCellEditor dce4 = new DefaultCellEditor( comboBox4 );
    	        editors.add( dce4 );
    	        
    	       
    	      
    	   	 j++; 
    	}
       
 
 
       /* String[] items2 = { "Circle", "Square", "Triangle" };
        JComboBox comboBox2 = new JComboBox( items2 );
        DefaultCellEditor dce2 = new DefaultCellEditor( comboBox2 );
        editors.add( dce2 );
 
        String[] items3 = { "Apple", "Orange", "Banana" };
        JComboBox comboBox3 = new JComboBox( items3 );
        DefaultCellEditor dce3 = new DefaultCellEditor( comboBox3 );
        editors.add( dce3 );*/
 
        //  Create the table with default data
 
       /* Object[][] data =
        {
            {"Color", "Red"},
            {"Shape", "Square"},
            {"Fruit", "Banana"},
            {"Plain", "Text"}
        };*/
        
        
        
        String[] columnNames = {"MethodID","MethodName", "RequirementID", "RequirementName", "ClassID", "ClassName", "Gold", "Subject", "GoldPredictionCaller", "GoldPredictionCallee", 
        		"Callers", "CallersExecuted", "Callees", "CalleesExecuted"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model)
        {
            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column)
            {
                int modelColumn = convertColumnIndexToModel( column );
 
                if (modelColumn == 10 && row < methodtraces2.size())
                    return editors.get(0);
               
                else
                    return super.getCellEditor(row, column);
            }
        };
        table.getColumnModel().getColumn(10).setWidth(10000);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );
//      table.getColumnModel().getColumn(1).setCellRenderer(new ComboBoxRenderer() );
    }
 
 
 
    class ComboBoxRenderer extends JComboBox implements TableCellRenderer
    {
 
        public ComboBoxRenderer()
        {
            setBorder(new EmptyBorder(0, 0, 0, 0));
        }
 
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column)
        {
//          setFocusable(false);
            removeAllItems();
            addItem( value );
            return this;
        }
    }
 
 
    public static void main(String[] args) throws SQLException
    {
    	
        TableComboBoxByRow frame = new TableComboBoxByRow();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }
}
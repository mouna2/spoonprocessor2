import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxEditor;

import org.eclipse.jdt.internal.core.nd.db.Database;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import mypackage.DatabaseReading2;
import mypackage.Method2Representation;
import mypackage.MethodTrace2;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

public class mygui {

	protected Shell shell;
	private Table table;
	
	static List<MethodTrace2> methodtraces2= new ArrayList<MethodTrace2>(); 
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		
			DatabaseReading2 databaseread= new DatabaseReading2(); 
			
			databaseread.MakePredictions();
			 methodtraces2 = databaseread.getMethodtraces2(); 
			mygui window = new mygui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(2000, 2000);
		shell.setText("TRACES TABLE");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 1900, 1900);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnMethodID = new TableColumn(table, SWT.NONE);
		tblclmnMethodID.setWidth(100);
		tblclmnMethodID.setText("MethodID");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		TableColumn tblclmnMethodName = new TableColumn(table, SWT.NONE);
		tblclmnMethodName.setWidth(100);
		tblclmnMethodName.setText("MethodName");
		
		TableColumn tblclmnRequirementID = new TableColumn(table, SWT.NONE);
		tblclmnRequirementID.setWidth(153);
		tblclmnRequirementID.setText("RequirementID");
		
		TableColumn tblclmnRequirementName = new TableColumn(table, SWT.NONE);
		tblclmnRequirementName.setWidth(150);
		tblclmnRequirementName.setText("RequirementName");
		
		TableColumn tblclmnClassID = new TableColumn(table, SWT.NONE);
		tblclmnClassID.setWidth(100);
		tblclmnClassID.setText("ClassID");
		
		TableColumn tblclmnClassName = new TableColumn(table, SWT.NONE);
		tblclmnClassName.setWidth(100);
		tblclmnClassName.setText("ClassName");
		
		TableColumn tblclmnGold = new TableColumn(table, SWT.NONE);
		tblclmnGold.setWidth(100);
		tblclmnGold.setText("Gold");
		
		TableColumn tblclmnSubject = new TableColumn(table, SWT.NONE);
		tblclmnSubject.setWidth(100);
		tblclmnSubject.setText("Subject");
		
		TableColumn tblclmnGoldpredictionCaller = new TableColumn(table, SWT.NONE);
		tblclmnGoldpredictionCaller.setWidth(200);
		tblclmnGoldpredictionCaller.setText("GoldPredictionCaller");
		
		TableColumn tblclmnGoldpredictionCallee = new TableColumn(table, SWT.NONE);
		tblclmnGoldpredictionCallee.setWidth(200);
		tblclmnGoldpredictionCallee.setText("GoldPredictionCallee");
		
		TableColumn tblclmnCallers = new TableColumn(table, SWT.NONE);
		tblclmnCallers.setWidth(100);
		tblclmnCallers.setText("Callers");
		// resize the row height using a MeasureItem listener
	/*	table.addListener(SWT.MeasureItem, new Listener() {
		   public void handleEvent(Event event) {
		      // height cannot be per row so simply set
		      event.height = 67;
		   }
		});*/
		
		TableColumn tblclmnCallersExecuted = new TableColumn(table, SWT.NONE);
		tblclmnCallersExecuted.setWidth(150);
		tblclmnCallersExecuted.setText("CallersExecuted");
		
		
		TableColumn tblclmnCallees = new TableColumn(table, SWT.NONE);
		tblclmnCallees.setWidth(100);
		tblclmnCallees.setText("Callees");
		
		
		
		TableColumn tblclmnCalleesExecuted = new TableColumn(table, SWT.NONE);
		tblclmnCalleesExecuted.setWidth(150);
		tblclmnCalleesExecuted.setText("CalleesExecuted");
		
	
		

		
		
		
		for(MethodTrace2 meth: methodtraces2) {
			TableItem item1 = new TableItem(table, SWT.NONE);
			List<Method2Representation> callees = meth.getCalleesList(); 
			List<Method2Representation> callers = meth.getCallersList(); 
			List<Method2Representation> callersExecuted = meth.getCallersListExecuted(); 
			List<Method2Representation> calleesExecuted = meth.getCalleesListExecuted(); 
		    item1.setText(new String[] { meth.MethodRepresentation.getMethodid(), meth.MethodRepresentation.getMethodname(), meth.Requirement.getID(), meth.Requirement.RequirementName, meth.ClassRepresentation.classid, meth.ClassRepresentation.classname, meth.gold
		    		, meth.subject, meth.goldpredictionCaller, meth.goldpredictionCallee});
		  //  Method2Representation methrep= new Method2Representation(); 
		    /*item1.setText(10, callees.toString());
		    item1.setText(11, callers.toString());
		    item1.setText(12, callersExecuted.toString());
		    item1.setText(13, calleesExecuted.toString());*/
		    
		   /* TableEditor editor = new TableEditor (table);
		    CCombo combo = new CCombo(table, SWT.NONE);
		    combo.setText("CCombo");
		    combo.add("item 1");
		    combo.add("item 2");
		    editor.grabHorizontal = true;
		    editor.setEditor(combo, item1, 0);*/
		}
	
		
		TableItem[] items = table.getItems();
		  TableEditor tableEditor = new TableEditor(table);
	    for (int i = 0; i < 100; i++) {
	    	  items = table.getItems();
	    	  CCombo  comboCallers = new CCombo(table, SWT.NONE);
	    	  comboCallers.setText("Callers");
		    	  for(Method2Representation caller: methodtraces2.get(i).getCallersList()) {
		    		  comboCallers.add(caller.toString());
		    		
		    	  }
		    	  
		    	  items = table.getItems();
		    	  CCombo   comboCallees = new CCombo(table, SWT.NONE);
		    	  comboCallees.setText("Callees");
			    	  for(Method2Representation callee: methodtraces2.get(i).getCalleesList()) {
			    		  comboCallees.add(callee.toString());
			    		  
			    	  }
	    	  
			    	  
			    	  
	    	items = table.getItems();
	    	CCombo  comboCallersExecuted = new CCombo(table, SWT.NONE);
	    	comboCallersExecuted.setText("CallersExecuted");
	    	  for(Method2Representation callerExecuted: methodtraces2.get(i).getCallersListExecuted()) {
	    		  comboCallersExecuted.add(callerExecuted.toString());
	    		  
	    	  }
	    	  items = table.getItems();
	      CCombo   comboCalleesExecuted = new CCombo(table, SWT.NONE);
	      comboCalleesExecuted.setText("CalleesExecuted");
	    	  for(Method2Representation calleeExecuted: methodtraces2.get(i).getCalleesListExecuted()) {
	    		  comboCalleesExecuted.add(calleeExecuted.toString());
	    		 
	    	  }
	    	  tableEditor.setEditor(comboCallers, items[i], 13);
	    	  tableEditor.setEditor(comboCallees, items[i], 10);
	    	  tableEditor.setEditor(comboCallersExecuted, items[i], 12);
	    	  tableEditor.setEditor(comboCalleesExecuted, items[i], 11);
	    	  
	    	  tableEditor.grabHorizontal = true;
		   
	    	 
	 	    
	      
	     
	     
	    }
	  
	    
		
		
		   
		 
		
		  /* final Combo c1 = new Combo(shell, SWT.READ_ONLY);
		    c1.setBounds(50, 50, 150, 65);
		    final Combo c2 = new Combo(shell, SWT.READ_ONLY);
		    c2.setBounds(50, 85, 150, 65);
		    c2.setEnabled(false);
		    String items[] = { "Item One", "Item Two", "Item Three", "Item Four",
		        "Item Five" };
		    c1.setItems(items);
		    c1.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        if (c1.getText().equals("Item One")) {
		          String newItems[] = { "Item One A", "Item One B",
		              "Item One C" };
		          c2.setItems(newItems);
		          c2.setEnabled(true);
		        } else if (c1.getText().equals("Item Two")) {
		          String newItems[] = { "Item Two A", "Item Two B",
		              "Item Two C" };
		          c2.setItems(newItems);
		          c2.setEnabled(true);
		        } else {
		          c2.add("Not Applicable");
		          c2.setText("Not Applicable");
		        }

		      }
		    });*/

	}
}

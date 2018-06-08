import java.util.List;

import org.eclipse.jdt.internal.core.nd.db.Database;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import mypackage.DatabaseReading2;
import mypackage.MethodTrace2;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;

public class mygui {

	protected Shell shell;
	private Table table;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		
			DatabaseReading2 databaseread= new DatabaseReading2(); 
			
			databaseread.MakePredictions();
			List<MethodTrace2> methodtraces2 = databaseread.getMethodtraces2(); 
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
		shell.setSize(1500, 1500);
		shell.setText("SWT Application TEST");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 1500, 1500);
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
		
		TableColumn tblclmnGoldprediction = new TableColumn(table, SWT.NONE);
		tblclmnGoldprediction.setWidth(100);
		tblclmnGoldprediction.setText("GoldPrediction");
		
		TableColumn tblclmnCallers = new TableColumn(table, SWT.NONE);
		tblclmnCallers.setWidth(100);
		tblclmnCallers.setText("Callers");
		
		TableColumn tblclmnCallees = new TableColumn(table, SWT.NONE);
		tblclmnCallees.setWidth(100);
		tblclmnCallees.setText("Callees");
		
		TableColumn tblclmnCallersExecuted = new TableColumn(table, SWT.NONE);
		tblclmnCallersExecuted.setWidth(150);
		tblclmnCallersExecuted.setText("CallersExecuted");
		
		TableColumn tblclmnCalleesExecuted = new TableColumn(table, SWT.NONE);
		tblclmnCalleesExecuted.setWidth(150);
		tblclmnCalleesExecuted.setText("CalleesExecuted");
		
		TableItem item1 = new TableItem(table, SWT.NONE);
	    item1.setText(new String[] { "Column1 text", "Column2 text", "Column3 text" });

	}
}

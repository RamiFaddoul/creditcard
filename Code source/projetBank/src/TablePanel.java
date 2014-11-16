import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class TablePanel extends JPanel {
	private JTable table;
	private DefaultTableModel mod;
	private Vector< Vector<Object> > data = new Vector< Vector<Object>>();
    private Vector<String> columnNames = new Vector<String>();
    int nbrcol=0;
    int nbrligne=0;
    public TablePanel(Vector<String> colnames)
    
    {
    	setLayout(new BorderLayout());
    	columnNames=colnames;
   
    	nbrcol= columnNames.size();
    	
    	 mod = new MyTableModel(data, columnNames);
    	 table=new JTable(mod);
    	 for(int i=0;i<table.getColumnCount();i++)
     	{
    		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    		 centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    		 table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
     	}
    	 JScrollPane scrollPane = new JScrollPane();
    	 scrollPane.setBounds(10, 119, 975, 300);
    	         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	        
    	         scrollPane.setViewportView(table);
    	 add(scrollPane);
		 add(table.getTableHeader(),BorderLayout.NORTH);
         add(scrollPane,BorderLayout.CENTER);
         table.getTableHeader().setReorderingAllowed(false);
    }
    
    void addRow(Vector<Object> vect){
    	Object[] ob=new Object[mod.getColumnCount()];	
    	for(int i=0;i<table.getColumnCount();i++)
    	{
    		ob[i]=vect.get(i);
    		
    	}
    	mod.addRow(ob);
    	
    	
    }
    
    void setRow(int index,Vector<Object> vect)
    {
    	for(int i=0; i<table.getColumnCount();i++){
    	table.setValueAt(vect.get(i),index ,i );
    	}
    }
    
    int getSelectedRowindex(){
    	return table.getSelectedRow();
    }
    
    void removeSelectedRow(){
    	
    	if(table.getSelectedRow()!=-1)mod.removeRow(table.getSelectedRow());
    	
    }
 void removeRow(int i){
    	
    	mod.removeRow(i);
    	
    }
   
  public Vector<Object> getRow(int index){
	  Vector<Object> v=new Vector<Object>();
	  for(int i=0;i< table.getColumnCount();i++){
		  v.add(table.getValueAt(index, i));
	  }
	  return v;
  }
  public int getColumnCount()
  {
	  return table.getColumnCount();
  }
  public int getRowCount()
  {
	  return table.getRowCount();
  }
  public void empty()
  {
	while(mod.getRowCount()>0){
		mod.removeRow(0);
	}
	
  }
  
  public void addRowSelectionListener(ListSelectionListener list){
	  table.getSelectionModel().addListSelectionListener(list);
  }
 
 
}

@SuppressWarnings("serial")
class MyTableModel extends DefaultTableModel {
	
	
	public MyTableModel(Vector data,Vector columnnames){
		super(data,columnnames);
		
	}
	@Override
	   public boolean isCellEditable(int row, int column) {
	
	       return false;
	   }
}

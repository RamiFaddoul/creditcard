import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PanelTableScoring extends JPanel implements ActionListener{
	 private TablePanel table;
	 private JButton btnAjouter;
	 private JButton btnVoir;
	/**
	 * Create the panel.
	 */
	 public void initialize()
	 {
		 setLayout(null);
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("Version Number");
	  	 columnNames.add("Date Début");
		 columnNames.add("Date Fin");
	     
	     table = new TablePanel(columnNames);
	     table.setBounds(10, 11, 673, 193);
	     add(table);
	    
	       
	        btnAjouter = new JButton("Nouvelle Version");
	       btnAjouter.addActionListener(this);
	       btnAjouter.setBounds(693, 11, 131, 23);
	       add(btnAjouter);
	       
	       btnVoir = new JButton("Voir");
	       btnVoir.addActionListener(this);
	       btnVoir.setBounds(693, 45, 131, 23);
	       add(btnVoir);

	       load();
	 }
	public PanelTableScoring() {

		 initialize();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
			 
			   if(e.getSource()==btnAjouter)
			  {
				  JFrame d= new TableParametre(this);
					
				  d.setVisible(true);
			  }
			   else if(e.getSource()==btnVoir){
				   TableParametre d= new TableParametre(this);
				d.remplir( Integer.parseInt(table.getRow(table.getSelectedRowindex()).get(0).toString()));
					  d.setVisible(true);
			   }
	}
	
	public void load(){
		try{
			  
		
			Statement s= FenetrePrincipale.connection.createStatement();
			  ResultSet res;
			  
			  res= s.executeQuery("select version,to_char(datedebut,'DD-MON-YYYY'),to_char(datefin,'DD-MON-YYYY') from tabledescoring where idbanque="+FenetrePrincipale.IdBanque+" order by version DESC");
			  int i=0;
			  while (res.next())
			   {
				   
				   Vector<Object> vect=new Vector<Object>();
				   vect.add(res.getString(1));
				   vect.add(res.getString(2));
				   vect.add(res.getString(3));
				   if (i<table.getRowCount())  table.setRow(i, vect);
				   else table.addRow(vect);
				   i++;
			   }
		       }
		       catch(Exception exc)
		       {
		    	   
		       }
		 
	}

	
	
}

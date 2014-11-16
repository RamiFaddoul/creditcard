import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

@SuppressWarnings("serial")
public class InfoFinanciere extends JPanel implements ActionListener{
	 public TablePanel table;
	 private JButton btnAjouter;
	 private JButton btnSupprimer;
	 public AjoutFinancier ajoutFin;
	/**
	 * Create the panel.
	 */
	public InfoFinanciere() {

		 setLayout(null);
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("Type");
	  	 columnNames.add("Salaire mensuel fixe");
		 columnNames.add("Salaire mensuel variable");
		
	     table = new TablePanel(columnNames);
	     table.setBounds(24, 11, 541, 239);
	     add(table);
	      btnSupprimer = new JButton("Supprimer");
	       btnSupprimer.addActionListener(this);
	       btnSupprimer.setBounds(586, 56, 105, 23);
	       add(btnSupprimer);
	      
	        btnAjouter = new JButton("Ajouter");
	       btnAjouter.addActionListener(this);
	       btnAjouter.setBounds(586, 23, 105, 23);
	       add(btnAjouter);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==btnSupprimer){
				try{
				table.removeSelectedRow();
			   }
			     catch(Exception a){}
			  }
			  else if(e.getSource()==btnAjouter)
			  {
				  ajoutFin= new AjoutFinancier(table);
				
					
				  ajoutFin.setVisible(true);
			  }
	}

}

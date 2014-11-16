import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;





@SuppressWarnings("serial")
public class PanelEngagementMensuel extends JPanel implements ActionListener{
	 public TablePanel table;
	 JButton btnAjouter;
	 JButton btnSupprimer;
	public PanelEngagementMensuel() {
       setLayout(null);
       Vector<String> columnNames = new Vector<String>();
       columnNames.add("Nom Débiteur");
  	   columnNames.add("Type Débiteur");
  	   columnNames.add("Type engagement");
	   columnNames.add("Valeur");
	   columnNames.add("Payement Mensuel");
	
       table = new TablePanel(columnNames);
     table.setBounds(10, 11, 616, 227);
       add(table);
       
      btnSupprimer = new JButton("Supprimer");
       btnSupprimer.addActionListener(this);
       btnSupprimer.setBounds(646, 58, 101, 23);
       add(btnSupprimer);
       
        btnAjouter = new JButton("Ajouter");
       btnAjouter.addActionListener(this);
       btnAjouter.setBounds(646, 22, 101, 23);
       add(btnAjouter);


	}
	TablePanel getTable(){return table;}
	
	public void actionPerformed(ActionEvent arg0) {
	  if(arg0.getSource()==btnSupprimer){
		try{
		table.removeSelectedRow();
	   }
	     catch(Exception e){}
	  }
	  else if(arg0.getSource()==btnAjouter)
	  {
		  AjoutEngagement AjEng = new AjoutEngagement(this);
		  
		  AjEng.setVisible(true);
		
	  }
   	}
}

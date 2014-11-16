import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanelProduit extends JPanel implements ActionListener{
	 public TablePanel table;
	 private JButton btnAjouter;
	 private JButton btnModifier;
	/**
	 * Create the panel.
	 */
	public PanelProduit() {

		 setLayout(null);
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("Nom du Produit");
	  	 columnNames.add("Score minimal");
		 columnNames.add("Limite Dépenses Min.");
		 columnNames.add("Limite Dépenses Max.");
		 columnNames.add("ID Début");
		 columnNames.add("ID Fin");
		 columnNames.add("Actif");
	     table = new TablePanel(columnNames);
	     table.setBounds(10, 11, 909, 301);
	     add(table);
	      btnModifier = new JButton("Modifier");
	       btnModifier.addActionListener(this);
	       btnModifier.setBounds(929, 61, 128, 23);
	       add(btnModifier);
	       
	        btnAjouter = new JButton("Ajouter");
	       btnAjouter.addActionListener(this);
	       btnAjouter.setBounds(929, 26, 128, 23);
	       add(btnAjouter);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==btnModifier){
				try{
					AjoutProduit d= new AjoutProduit(table);
					d.remplir(table.getSelectedRowindex());
					  d.setVisible(true);
			   }
			     catch(Exception a){}
			  }
			  else if(e.getSource()==btnAjouter)
			  {
				  AjoutProduit d= new AjoutProduit(table);
					
				  d.setVisible(true);
			  }
	}

}

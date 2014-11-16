import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JButton;


public class InfoComptes extends JPanel implements ActionListener{
	public TablePanel table;
	private JButton btnVoir;
	/**
	 * Create the panel.
	 */
	public InfoComptes() {
		 setLayout(null);
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("Numero Compte");
	  	 columnNames.add("Limite de dépense");
		 columnNames.add("Nombre de  Cartes");
		 columnNames.add("Dépenses moyennes par mois");
		 columnNames.add("Pourcentage Remboursement");

	
	     table = new TablePanel(columnNames);
	     table.setBounds(10, 11, 906, 233);
	     add(table);
	     
	     btnVoir = new JButton("Voir les cartes");
	     btnVoir.addActionListener(this);
	     btnVoir.setBounds(782, 256, 134, 23);
	     add(btnVoir);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnVoir){
			if(table.getSelectedRowindex()>-1){
			DetaillesCartes det=new DetaillesCartes(Integer.parseInt(table.getRow(table.getSelectedRowindex()).get(0).toString()));
			det.setBounds(100, 100, 950, 550);
			det.setVisible(true);
			}
		}
	}

}

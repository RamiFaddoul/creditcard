import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Choice;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import java.awt.Canvas;
import javax.swing.Box;
import java.awt.Font;

public class InfoCartes extends JPanel implements ActionListener {
	public TablePanel table;
	private JButton btnAjouter;
	 private JButton btnSupprimer;
	 private JLabel lblNewLabel;
	 public JTextField textNomCarte;
	 private JLabel lblNewLabel_1;
	 private JLabel lblServiceemail;
	 private JLabel lblServicelettre;
	 public Choice choiceSMS;
	 public Choice choiceEmail;
	 public Choice choiceLettre;
	 private JLabel lblCartePrimaire;
	 private JLabel lblCartesSupplementaires;
	 public Vector < HashMap<String,String>> vect= new Vector<HashMap<String,String>>();
	 private JButton btnVoir;
	 public AjoutCarte d;
	 
	 
	public InfoCartes() {

		setLayout(null);
	       Vector<String> columnNames = new Vector<String>();
	       columnNames.add("Nom");
	       columnNames.add("Prénom");
	  	   columnNames.add("Nom sur carte");
		   columnNames.add("Service SMS");
		   columnNames.add("Service Mail");
		   columnNames.add("Service Lettre");
	        table = new TablePanel(columnNames);
	       table.setBounds(21, 212, 662, 243);
	       add(table);
	       
	       btnSupprimer = new JButton("Supprimer");
	       btnSupprimer.addActionListener(this);
	       btnSupprimer.setBounds(693, 265, 103, 23);
	       add(btnSupprimer);
	       
	        btnAjouter = new JButton("Ajouter");
	       btnAjouter.addActionListener(this);
	       btnAjouter.setBounds(693, 212, 103, 23);
	       add(btnAjouter);
	       
	       JPanel panel = new JPanel();
	       panel.setBounds(20, 37, 358, 112);
	       add(panel);
	       panel.setLayout(null);
	       
	       lblNewLabel = new JLabel("Nom sur Carte ");
	       lblNewLabel.setBounds(10, 12, 145, 18);
	       panel.add(lblNewLabel);
	       
	       textNomCarte = new JTextField();
	       textNomCarte.setBounds(156, 11, 128, 20);
	       panel.add(textNomCarte);
	       textNomCarte.setColumns(10);
	       
	       lblNewLabel_1 = new JLabel("ServiceSMS");
	       lblNewLabel_1.setBounds(10, 39, 118, 18);
	       panel.add(lblNewLabel_1);
	       
	       choiceSMS = new Choice();
	       choiceSMS.setBounds(156, 37, 75, 20);
	       panel.add(choiceSMS);
	       
	       lblServiceemail = new JLabel("ServiceEmail");
	       lblServiceemail.setBounds(10, 62, 118, 20);
	       panel.add(lblServiceemail);
	       
	       choiceEmail = new Choice();
	       choiceEmail.setBounds(156, 62, 75, 20);
	       panel.add(choiceEmail);
	       
	       lblServicelettre = new JLabel("ServiceLettre");
	       lblServicelettre.setBounds(10, 88, 118, 20);
	       panel.add(lblServicelettre);
	       
	       choiceLettre = new Choice();
	       choiceLettre.setBounds(156, 88, 75, 20);
	       panel.add(choiceLettre);
	       
	       lblCartePrimaire = new JLabel("Carte Primaire :");
	       lblCartePrimaire.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblCartePrimaire.setBounds(20, 12, 108, 14);
	       add(lblCartePrimaire);
	       
	       lblCartesSupplementaires = new JLabel("Carte(s) Supplementaire(s) :");
	       lblCartesSupplementaires.setFont(new Font("Tahoma", Font.PLAIN, 14));
	       lblCartesSupplementaires.setBounds(20, 176, 258, 14);
	       add(lblCartesSupplementaires);
	       
	       btnVoir = new JButton("Voir");
	       btnVoir.setBounds(693, 239, 103, 23);
	       btnVoir.addActionListener(this);
	       add(btnVoir);
	       choiceLettre.add("Oui");
	       choiceLettre.add("Non");
	       choiceEmail.add("Oui");
	       choiceEmail.add("Non");
	       choiceSMS.add("Oui");
	       choiceSMS.add("Non");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnSupprimer){
			try{
				vect.remove(table.getSelectedRowindex());
			table.removeSelectedRow();
			
		   }
		     catch(Exception e){}
		  }
		  else if(arg0.getSource()==btnAjouter)
		  {
			  JDialog d= new AjoutCarte(table,vect);
			  
			  d.setVisible(true);
		  }
		  else if(arg0.getSource()==btnVoir && table.getSelectedRowindex()!=-1){
			 
			   d= new AjoutCarte(table,vect);		
			  d.remplir(table.getSelectedRowindex()) ;
			  d.setVisible(true);
		
		  }
		
	}
}

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class AjoutCarte extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textNomSurCarte;
	private Choice choiceLettre;
	private Choice choiceEmail;
	private Choice choiceSMS;
	private TablePanel table;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textLien;
	private JTextField textNationalite;
	private JTextField textnumtel;
	private JTextField textnumtelmob;
	private JTextField textadrEmail;
	private JButton okButton;
	private JButton cancelButton; 
	private JCheckBox chckbxM;
	private JCheckBox chckbxF;
	private JTextArea textAdr;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
    private int index=-1;

	private Vector<HashMap<String,String> > vect;
	
	public AjoutCarte(TablePanel t,Vector<HashMap<String,String> >v) {
		table=t;
		vect=v;
		setBounds(100, 100, 625, 294);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	    choiceLettre = new Choice();
		choiceLettre.add("Oui");
		choiceLettre.add("Non");
		choiceLettre.setBounds(131, 186, 81, 20);
		contentPanel.add(choiceLettre);
		
		JLabel lblServiceLettre = new JLabel("Service Lettre");
		lblServiceLettre.setBounds(21, 189, 104, 14);
		contentPanel.add(lblServiceLettre);
		
		JLabel label_1 = new JLabel("Service Email");
		label_1.setBounds(21, 160, 104, 14);
		contentPanel.add(label_1);
		
		choiceEmail = new Choice();
		choiceEmail.add("Oui");
		choiceEmail.add("Non");
		choiceEmail.setBounds(131, 157, 81, 20);
		contentPanel.add(choiceEmail);
		
		choiceSMS = new Choice();
		choiceSMS.add("Oui");
		choiceSMS.add("Non");
		choiceSMS.setBounds(131, 128, 81, 20);
		contentPanel.add(choiceSMS);
		
		JLabel label_2 = new JLabel("Service SMS");
		label_2.setBounds(21, 131, 104, 14);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Nom sur carte");
		label_3.setBounds(21, 102, 104, 14);
		contentPanel.add(label_3);
		
		textNomSurCarte = new JTextField();
	
		textNomSurCarte.setColumns(10);
		textNomSurCarte.setBounds(131, 99, 127, 20);
		contentPanel.add(textNomSurCarte);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(21, 15, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setBounds(21, 44, 104, 14);
		contentPanel.add(lblNewLabel_1);
		
		textNom = new JTextField();
		textNom.setBounds(131, 9, 127, 20);
		contentPanel.add(textNom);
		textNom.setColumns(10);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		textPrenom.setBounds(131, 38, 127, 20);
		contentPanel.add(textPrenom);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(271, 16, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setBounds(21, 73, 46, 14);
		contentPanel.add(lblSexe);
		
		chckbxM = new JCheckBox("M");
		buttonGroup.add(chckbxM);
		chckbxM.setBounds(131, 65, 53, 23);
		contentPanel.add(chckbxM);
		
		chckbxF = new JCheckBox("F");
		buttonGroup.add(chckbxF);
		chckbxF.setBounds(212, 65, 46, 23);
		contentPanel.add(chckbxF);
		
		JLabel lblNewLabel_3 = new JLabel("Lien de relativit\u00E9e");
		lblNewLabel_3.setBounds(310, 15, 103, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNationalit = new JLabel("Nationalit\u00E9");
		lblNationalit.setBounds(310, 44, 103, 14);
		contentPanel.add(lblNationalit);
		
		JLabel lblNumeroTelephone = new JLabel("Num\u00E9ro de Tel.");
		lblNumeroTelephone.setBounds(310, 73, 103, 14);
		contentPanel.add(lblNumeroTelephone);
		
		JLabel lblNumroDeTel = new JLabel("Num\u00E9ro de Tel. Mobile");
		lblNumroDeTel.setBounds(310, 102, 140, 14);
		contentPanel.add(lblNumroDeTel);
		
		JLabel lblAddresseEamil = new JLabel("Addresse Email");
		lblAddresseEamil.setBounds(310, 128, 103, 20);
		contentPanel.add(lblAddresseEamil);
		
		JLabel lblAddresse = new JLabel("Addresse :");
		lblAddresse.setBounds(310, 160, 103, 14);
		contentPanel.add(lblAddresse);
		
		textLien = new JTextField();
		textLien.setColumns(10);
		textLien.setBounds(443, 12, 140, 20);
		contentPanel.add(textLien);
		
		textNationalite = new JTextField();
		textNationalite.setColumns(10);
		textNationalite.setBounds(443, 41, 140, 20);
		contentPanel.add(textNationalite);
		
		textnumtel = new JTextField();
		textnumtel.setColumns(10);
		textnumtel.setBounds(443, 70, 140, 20);
		contentPanel.add(textnumtel);
		
		textnumtelmob = new JTextField();
		textnumtelmob.setColumns(10);
		textnumtelmob.setBounds(443, 99, 140, 20);
		contentPanel.add(textnumtelmob);
		
		textadrEmail = new JTextField();
		textadrEmail.setColumns(10);
		textadrEmail.setBounds(443, 128, 140, 20);
		contentPanel.add(textadrEmail);
		
		textAdr = new JTextArea();
		textAdr.setBounds(405, 160, 194, 51);
		contentPanel.add(textAdr);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
     
    public void remplir(int i)
    {
    	
    	textNom.setText(vect.get(i).get("Nom"));
    	   
	     textNomSurCarte.setText(vect.get(i).get("Nom sur Carte"));
	     	textPrenom.setText(vect.get(i).get( "Prenom"));
         textLien.setText(vect.get(i).get("Lien"));
	   	  textNationalite.setText(vect.get(i).get("Nationalite"));
	     	textnumtel.setText(vect.get(i).get("Num tel"));
	    if(choiceLettre.getItem(0)==vect.get(i).get("CLettre")) 	choiceLettre.select(0);
	    else choiceLettre.select(1);
	    if(choiceEmail.getItem(0)==vect.get(i).get("CEmail")) 	choiceEmail.select(0);
	    else choiceEmail.select(1);
	    if(choiceSMS.getItem(0)==vect.get(i).get("CSMS")) 	choiceSMS.select(0);
	    else choiceSMS.select(1);
		 if(vect.get(i).get("Sexe")=="M") chckbxM.setSelected(true);
		 else chckbxF.setSelected(true);
       	textnumtelmob.setText(vect.get(i).get( "Num tel mobile"));
	   	  textadrEmail.setText(vect.get(i).get( "Email"));
	  textAdr.setText(vect.get(i).get("Addresse"));
	  
	   index=i;
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == okButton)
		{
			
			   
		       Vector<Object> vectoro =new Vector<Object>();
		       HashMap<String,String> vectors =new HashMap <String,String>();
		       vectoro.add( textNom.getText());
		       vectoro.add( textPrenom.getText());
		       vectoro.add( textNomSurCarte.getText());
		       
		       vectors.put( "Nom sur Carte", textNomSurCarte.getText());
		       vectoro.add(   choiceSMS.getSelectedItem());
		       vectoro.add( choiceEmail.getSelectedItem());
		       vectoro.add( choiceLettre.getSelectedItem());
		      if(chckbxM.isSelected()) vectors.put( "Sexe", "M");  
		      else vectors.put( "Sexe", "F");  
		       vectors.put( "Nom", textNom.getText());
		       vectors.put("Prenom",textPrenom.getText());
               vectors.put(	"Lien", textLien.getText());
		   	   vectors.put("Nationalite",textNationalite.getText());
		     	vectors.put("Num tel",textnumtel.getText());
		     	 vectors.put("CLettre",choiceLettre.getSelectedItem());
			       vectors.put(	"CEmail", choiceEmail.getSelectedItem());
			       vectors.put( "CSMS", choiceSMS.getSelectedItem());	
	          vectors.put(	"Num tel mobile", textnumtelmob.getText());
		   	  vectors.put( "Email", textadrEmail.getText());
	           vectors.put("Addresse", textAdr.getText());
		   	  if(index>-1){ vect.set(table.getSelectedRowindex(), vectors) ; 
		   	 table.setRow(index, vectoro); index=-1;}
		   	  else {vect.add(vectors);
		       table.addRow(vectoro);
		   	  }
		       this.dispose();
			
		}
		else if(arg0.getSource() == cancelButton) {
			this.dispose();
			
		}
		
	}
}

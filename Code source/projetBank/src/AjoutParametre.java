import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JTextField;


public class AjoutParametre extends JDialog implements ActionListener,ItemListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textScore;
	private JTextField textValeur;
	private JTextField textValeurMax;
	private JTextField textValeurMin;
	private TablePanel table;
	private JButton okButton;
	private JButton cancelButton;
	private Choice choiceCA; 
	private Choice choiceParametre;
	private Choice choiceEtatCivil;
	private Choice choiceEtatHabitat;
	private Choice choiceEducation;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public AjoutParametre(TablePanel t) {
		table=t;
		setBounds(100, 100, 461, 277);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Condition admise");
			label.setBounds(30, 172, 108, 14);
			contentPanel.add(label);
		}
		{
			choiceCA = new Choice();
			choiceCA.add("Oui");
			choiceCA.add("Non");
			choiceCA.setBounds(163, 172, 180, 20);
			contentPanel.add(choiceCA);
		}
		{
			textScore = new JTextField();
			textScore.setColumns(10);
			textScore.setBounds(163, 140, 180, 20);
			contentPanel.add(textScore);
		}
		{
			JLabel label = new JLabel("Score");
			label.setBounds(30, 141, 108, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Valeur");
			label.setBounds(30, 110, 108, 14);
			contentPanel.add(label);
		}
		{
			textValeur = new JTextField();
			textValeur.setColumns(10);
			textValeur.setBounds(163, 108, 180, 20);
			contentPanel.add(textValeur);
			
			choiceEtatCivil =new Choice();
			choiceEtatCivil.setVisible(false);
			for(EtatCivil etat: EtatCivil.values())
			{
				choiceEtatCivil.add(etat.toString());
			}
			choiceEtatCivil.setBounds(163, 108, 180, 20);
			contentPanel.add(choiceEtatCivil);
			
			
			
			
			choiceEtatHabitat =new Choice();
			choiceEtatHabitat.setVisible(false);
			for(EtatHabitat etat: EtatHabitat.values())
			{
				choiceEtatHabitat.add(etat.toString());
			}
			choiceEtatHabitat.setBounds(163, 108, 180, 20);
			contentPanel.add(choiceEtatHabitat);
			
			choiceEducation =new Choice();
			choiceEducation.setVisible(false);
			for(NiveauEducation etat: NiveauEducation.values())
			{
				choiceEducation.add(etat.toString());
			}
			choiceEducation.setBounds(163, 108, 180, 20);
			contentPanel.add(choiceEducation);
			
			
		}
		{
			JLabel label = new JLabel("Valeur maximal");
			label.setBounds(30, 79, 103, 14);
			contentPanel.add(label);
		}
		{
			textValeurMax = new JTextField();
			textValeurMax.setColumns(10);
			textValeurMax.setBounds(163, 76, 180, 20);
			contentPanel.add(textValeurMax);
		}
		{
			JLabel label = new JLabel("Valeur minimal");
			label.setBounds(30, 48, 103, 14);
			contentPanel.add(label);
		}
		{
			textValeurMin = new JTextField();
			textValeurMin.setColumns(10);
			textValeurMin.setBounds(163, 44, 180, 20);
			contentPanel.add(textValeurMin);
		}
		{
			JLabel label = new JLabel("Param\u00E8tre");
			label.setBounds(30, 17, 103, 14);
			contentPanel.add(label);
		}
		
		choiceParametre = new Choice();
		choiceParametre.setBounds(163, 12, 180, 20);
		for(Parametres par: Parametres.values())
		{
			choiceParametre.add(par.toString());	
		}

		contentPanel.add(choiceParametre);
		choiceParametre.addItemListener(this);
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
		  textValeurMin.setEnabled(true);
		    textValeurMax.setEnabled(true);
			textValeur.setVisible(true);
			textValeur.setEnabled(false);
			choiceEtatCivil.setVisible(false);
			choiceEtatHabitat.setVisible(false);
			choiceEducation.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==okButton){
		 Vector<Object> vect =new Vector<Object>();
		       vect.add(choiceParametre.getSelectedItem());
		       vect.add(textValeurMin.getText());
		       vect.add(textValeurMax.getText());
		       if(textValeur.isVisible()) vect.add(textValeur.getText());
		       else if (choiceEtatCivil.isVisible()) vect.add(choiceEtatCivil.getSelectedItem());
		       else if(choiceEtatHabitat.isVisible())vect.add(choiceEtatHabitat.getSelectedItem());
		       else if(choiceEducation.isVisible()) vect.add(choiceEducation.getSelectedItem());
		       else vect.add("");
		       vect.add(textScore.getText());
		       vect.add(choiceCA.getSelectedItem());
		      table.addRow(vect);
		      this.dispose();
		}
		else if(e.getSource()==cancelButton)
		{
			
			this.dispose();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource()==choiceParametre){
			Parametres par=Parametres.AGE;
			for (Parametres p : Parametres.values()){
				if(choiceParametre.getSelectedItem()==p.toString()) par=p;
			}
			if (par==Parametres.ETAT_CIVIL)
			{
			    textValeurMin.setEnabled(false);
				textValeurMax.setEnabled(false);
				textValeur.setVisible(false);
				choiceEtatCivil.setVisible(true);
				choiceEtatHabitat.setVisible(false);
				choiceEducation.setVisible(false);
			}
			else if(par==Parametres.ETAT_ADRESSE){
				
				textValeurMin.setEnabled(false);
				textValeurMax.setEnabled(false);
				textValeur.setVisible(false);
				choiceEtatCivil.setVisible(false);
				choiceEtatHabitat.setVisible(true);
				choiceEducation.setVisible(false);
			}
			else if(par==Parametres.NIVEAU_EDUCATION){
				 textValeurMin.setEnabled(false);
				    textValeurMax.setEnabled(false);
				textValeur.setVisible(false);
				choiceEtatCivil.setVisible(false);
				choiceEtatHabitat.setVisible(false);
				choiceEducation.setVisible(true);
			}
			else if(par.getType()=="nombre"){
			    textValeurMin.setEnabled(true);
			    textValeurMax.setEnabled(true);
				textValeur.setVisible(true);
				textValeur.setEnabled(false);
				choiceEtatCivil.setVisible(false);
				choiceEtatHabitat.setVisible(false);
				choiceEducation.setVisible(false);
			}
			else if(par.getType()=="string")
			{
			    textValeurMin.setEnabled(false);
			    textValeurMax.setEnabled(false);
				textValeur.setVisible(true);
				textValeur.setEnabled(true);
				choiceEtatCivil.setVisible(false);
				choiceEtatHabitat.setVisible(false);
				choiceEducation.setVisible(false);
			}
		}
	}
}


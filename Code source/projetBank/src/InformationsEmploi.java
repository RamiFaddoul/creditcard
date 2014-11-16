import java.awt.Choice;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class InformationsEmploi extends JPanel {
	public Choice choiceEtatEmploi;
	public JTextField textProfession;
	public JTextField textTitre;
	public JTextField textSecteurActivité;
	public JTextField textCompagnie;
	public JTextField textAdresseEmail;
	public JTextField textJour;
	public JTextField textMois;
	public JTextField textAnne;
	public JTextField textSMV;
	public JTextField textSMF;
	public JTextField textTypeSalaire;

	/**
	 * Create the panel.
	 */
	void setEditable(boolean b)
	{
		choiceEtatEmploi.setEnabled(b);
		 textProfession.setEditable(b);
		 textTitre.setEditable(b);
		 textSecteurActivité.setEditable(b);
		 textCompagnie.setEditable(b);
		 textAdresseEmail.setEditable(b);
		 textJour.setEditable(b);
		 textMois.setEditable(b);
		 textAnne.setEditable(b);
		 textSMV.setEditable(b);
		 textSMF.setEditable(b);
		textAdresseEmail.setEditable(b);
		textTypeSalaire.setEditable(b);
	
		
	}
	public InformationsEmploi() {
		setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(225, 5, 0, 0);
		add(label);
		
		JLabel lblEtatEmploi = new JLabel("Etat Emploi");
		lblEtatEmploi.setBounds(10, 116, 119, 14);
		add(lblEtatEmploi);
		
		JLabel lblDateEmbauchement = new JLabel("Date Embauchement");
		lblDateEmbauchement.setBounds(10, 90, 131, 14);
		add(lblDateEmbauchement);
		
		JLabel lblProfession = new JLabel("Profession");
		lblProfession.setBounds(10, 38, 119, 14);
		add(lblProfession);
		
		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setBounds(10, 12, 46, 14);
		add(lblTitre);
		
		JLabel lblNomCompagnie = new JLabel("Nom Compagnie");
		lblNomCompagnie.setBounds(10, 64, 119, 14);
		add(lblNomCompagnie);
		
		JLabel lblSecteurActivité = new JLabel("Secteur Activit\u00E9");
		lblSecteurActivité.setBounds(10, 141, 119, 14);
		add(lblSecteurActivité);
		
		JLabel lblAdresseEmail = new JLabel("Adresse Email");
		lblAdresseEmail.setBounds(10, 168, 119, 14);
		add(lblAdresseEmail);
		
		choiceEtatEmploi = new Choice();
		for(EtatEmploi etat:EtatEmploi.values()){
			choiceEtatEmploi.add(etat.toString());
		}
		choiceEtatEmploi.setBounds(157, 113, 131, 20);
		add(choiceEtatEmploi);
		
		
		textProfession = new JTextField();
		textProfession.setBounds(157, 35, 131, 20);
		add(textProfession);
		textProfession.setColumns(10);
		
		textTitre = new JTextField();
		textTitre.setBounds(157, 9, 131, 20);
		add(textTitre);
		textTitre.setColumns(10);
		
		textSecteurActivité = new JTextField();
		textSecteurActivité.setBounds(157, 139, 131, 20);
		add(textSecteurActivité);
		textSecteurActivité.setColumns(10);
		
		textCompagnie = new JTextField();
		textCompagnie.setBounds(157, 61, 131, 20);
		add(textCompagnie);
		textCompagnie.setColumns(10);
		
		textAdresseEmail = new JTextField();
		textAdresseEmail.setBounds(157, 165, 131, 20);
		add(textAdresseEmail);
		textAdresseEmail.setColumns(10);
		
		textJour = new JTextField();
		textJour.setColumns(10);
		textJour.setBounds(157, 88, 24, 20);
		add(textJour);
		
		textMois = new JTextField();
		textMois.setColumns(10);
		textMois.setBounds(206, 87, 24, 20);
		add(textMois);
		
		textAnne = new JTextField();
		textAnne.setColumns(10);
		textAnne.setBounds(250, 87, 38, 20);
		add(textAnne);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(240, 91, 11, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(191, 91, 11, 14);
		add(label_2);
		
		textSMV = new JTextField();
		textSMV.setColumns(10);
		textSMV.setBounds(158, 242, 130, 20);
		add(textSMV);
		
		JLabel label_3 = new JLabel("Salaire mensuel variable");
		label_3.setBounds(10, 245, 145, 14);
		add(label_3);
		
		JLabel label_4 = new JLabel("Salaire mensuel fixe");
		label_4.setBounds(10, 220, 145, 14);
		add(label_4);
		
		textSMF = new JTextField();
		textSMF.setColumns(10);
		textSMF.setBounds(158, 217, 130, 20);
		add(textSMF);
		
		JLabel label_5 = new JLabel("Type");
		label_5.setBounds(10, 193, 145, 14);
		add(label_5);
		
		textTypeSalaire = new JTextField();
		textTypeSalaire.setText("Salaire");
		textTypeSalaire.setEditable(false);
		textTypeSalaire.setColumns(10);
		textTypeSalaire.setBounds(158, 191, 130, 20);
		add(textTypeSalaire);

	}
}

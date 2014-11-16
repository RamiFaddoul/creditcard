import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;

import java.text.NumberFormat;
import java.util.Locale;
import java.awt.Choice;

@SuppressWarnings("serial")
public class ClientInformation extends JPanel {
	public JTextField textNom=null;
	public JTextField textPr�nom=null;
	public JTextField textNomP�re=null;
	public JTextField textNomJeuneFille=null;
	public JTextField textNomM�re=null;
	public JTextField textJourNaissance=null;
	public JTextField textNum�roRegistre=null;
	public Choice  choiceNationalite=null;
	public JTextField textNombreD�pendants=null;
	public Choice choiceNiveauEducation=null;
	public JTextField textAdresseEmail=null;
	public JTextField textNum�roMobile=null;
	public JCheckBox chckbxSexeMale=null;
	public JCheckBox chckbxSexeFemale=null;
	public JTextField textMoisNaissance=null;
	public JTextField textAnneeNaissance=null;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public Choice choiceEtatCivil=null;

	/**
	 * Create the panel.
	 */
	void setEditable(boolean b)
	{
		textNom.setEditable(b);
		textPr�nom.setEditable(b);
		textNomP�re.setEditable(b);
		textNomJeuneFille.setEditable(b);
		textNomM�re.setEditable(b);
		textJourNaissance.setEditable(b);
		choiceNationalite.setEnabled(b);
		textNum�roRegistre.setEditable(b);
		choiceEtatCivil.setEnabled(b);
		textNombreD�pendants.setEditable(b);
		choiceNiveauEducation.setEnabled(b);
		textAdresseEmail.setEditable(b);
		textNum�roMobile.setEditable(b);
		chckbxSexeMale.setEnabled(b);
		chckbxSexeFemale.setEnabled(b);
		textMoisNaissance.setEditable(b);
		textAnneeNaissance.setEditable(b);
		
	}

	public ClientInformation() {
		setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 11, 129, 14);
		add(lblNom);
		
		JLabel lblPr�nom = new JLabel("Pr\u00E9nom");
		lblPr�nom.setBounds(10, 36, 129, 14);
		add(lblPr�nom);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setBounds(10, 61, 129, 14);
		add(lblSexe);
		
		JLabel lblNomP�re = new JLabel("Nom p\u00E8re");
		lblNomP�re.setBounds(10, 86, 129, 14);
		add(lblNomP�re);
		
		JLabel lblNomJeuneFille = new JLabel("Nom jeune fille");
		lblNomJeuneFille.setBounds(10, 111, 129, 14);
		add(lblNomJeuneFille);
		
		JLabel lblNomM�re = new JLabel("Nom m\u00E8re");
		lblNomM�re.setBounds(10, 136, 129, 14);
		add(lblNomM�re);
		
		JLabel lblDateNaissance = new JLabel("Date de Naissance");
		lblDateNaissance.setBounds(10, 161, 129, 14);
		add(lblDateNaissance);
		
		JLabel lblNationalit� = new JLabel("Nationalit\u00E9");
		lblNationalit�.setBounds(10, 186, 129, 14);
		add(lblNationalit�);
		
		JLabel lblNum�roRegistre = new JLabel("Num\u00E9ro du registre");
		lblNum�roRegistre.setBounds(10, 211, 129, 14);
		add(lblNum�roRegistre);
		
		JLabel lblEtatCivil = new JLabel("Etat civil");
		lblEtatCivil.setBounds(10, 236, 129, 14);
		add(lblEtatCivil);
		
		JLabel lblNombreD�pendants = new JLabel("Nombre de d\u00E9pendants");
		lblNombreD�pendants.setBounds(10, 261, 134, 14);
		add(lblNombreD�pendants);
		
		JLabel lblNiveauEducation = new JLabel("Niveau d'\u00E9ducation");
		lblNiveauEducation.setBounds(10, 286, 129, 14);
		add(lblNiveauEducation);
		
		JLabel lblAdresseEmail = new JLabel("Adresse Email");
		lblAdresseEmail.setBounds(10, 311, 129, 14);
		add(lblAdresseEmail);
		
		JLabel lblNum�roMobile = new JLabel("Num\u00E9ro mobile");
		lblNum�roMobile.setBounds(10, 336, 129, 14);
		add(lblNum�roMobile);
		
		textNom = new JTextField();
		textNom.setBounds(154, 5, 185, 20);
		add(textNom);
		textNom.setColumns(10);
		
		textPr�nom = new JTextField();
		textPr�nom.setBounds(154, 30, 185, 20);
		add(textPr�nom);
		textPr�nom.setColumns(10);
		
		textNomP�re = new JTextField();
		textNomP�re.setBounds(154, 83, 185, 20);
		add(textNomP�re);
		textNomP�re.setColumns(10);
		
		textNomJeuneFille = new JTextField();
		textNomJeuneFille.setBounds(154, 108, 185, 20);
		add(textNomJeuneFille);
		textNomJeuneFille.setColumns(10);
		
		textNomM�re = new JTextField();
		textNomM�re.setBounds(154, 133, 185, 20);
		add(textNomM�re);
		textNomM�re.setColumns(10);
		
		textJourNaissance = new JTextField();
		textJourNaissance.setBounds(154, 158, 42, 20);
		add(textJourNaissance);
		textJourNaissance.setColumns(10);
		
		textNum�roRegistre = new JTextField();
		textNum�roRegistre.setBounds(154, 208, 185, 20);
		add(textNum�roRegistre);
		textNum�roRegistre.setColumns(10);
		
		textNombreD�pendants = new JTextField();
		textNombreD�pendants.setBounds(154, 258, 185, 20);
		add(textNombreD�pendants);
		textNombreD�pendants.setColumns(10);
		
		choiceNiveauEducation = new Choice();
		for(NiveauEducation niv: NiveauEducation.values()){
			choiceNiveauEducation.add(niv.toString());
		}
		choiceNiveauEducation.setBounds(154, 283, 185, 20);
		add(choiceNiveauEducation);
		
		
		textAdresseEmail = new JTextField();
		textAdresseEmail.setBounds(154, 308, 185, 20);
		add(textAdresseEmail);
		textAdresseEmail.setColumns(10);
		
		textNum�roMobile = new JTextField();
		textNum�roMobile.setBounds(154, 333, 185, 20);
		add(textNum�roMobile);
		textNum�roMobile.setColumns(10);
		
		chckbxSexeMale = new JCheckBox("M");
		buttonGroup_1.add(chckbxSexeMale);
		chckbxSexeMale.setBounds(154, 57, 83, 23);
		add(chckbxSexeMale);
		
		chckbxSexeFemale = new JCheckBox("F");
		buttonGroup_1.add(chckbxSexeFemale);
		chckbxSexeFemale.setBounds(239, 57, 100, 23);
		add(chckbxSexeFemale);
		
		textMoisNaissance = new JTextField();
		textMoisNaissance.setColumns(10);
		textMoisNaissance.setBounds(216, 158, 42, 20);
		add(textMoisNaissance);
		
		textAnneeNaissance = new JTextField();
		textAnneeNaissance.setColumns(10);
		textAnneeNaissance.setBounds(280, 158, 59, 20);
		add(textAnneeNaissance);
		
		JLabel label = new JLabel("/");
		label.setBounds(206, 161, 10, 14);
		add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(270, 161, 10, 14);
		add(label_1);
		
		choiceNationalite = new Choice();
		String[] list = Locale.getISOCountries();
		
		Locale locales;
		for (int i=0;i<list.length;i++) {
			locales=new Locale(" ",list[i]);
			choiceNationalite.add(locales.getDisplayCountry());
			locales=null;
			
		}
		choiceNationalite.setBounds(154, 183, 185, 20);
		add(choiceNationalite);
		
		choiceEtatCivil = new Choice();
		for(EtatCivil niv: EtatCivil.values()){
			choiceEtatCivil.add(niv.toString());
		}
		choiceEtatCivil.setBounds(154, 232, 185, 20);
		add(choiceEtatCivil);
		
          
	}
}

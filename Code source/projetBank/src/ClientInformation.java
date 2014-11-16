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
	public JTextField textPrénom=null;
	public JTextField textNomPère=null;
	public JTextField textNomJeuneFille=null;
	public JTextField textNomMère=null;
	public JTextField textJourNaissance=null;
	public JTextField textNuméroRegistre=null;
	public Choice  choiceNationalite=null;
	public JTextField textNombreDépendants=null;
	public Choice choiceNiveauEducation=null;
	public JTextField textAdresseEmail=null;
	public JTextField textNuméroMobile=null;
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
		textPrénom.setEditable(b);
		textNomPère.setEditable(b);
		textNomJeuneFille.setEditable(b);
		textNomMère.setEditable(b);
		textJourNaissance.setEditable(b);
		choiceNationalite.setEnabled(b);
		textNuméroRegistre.setEditable(b);
		choiceEtatCivil.setEnabled(b);
		textNombreDépendants.setEditable(b);
		choiceNiveauEducation.setEnabled(b);
		textAdresseEmail.setEditable(b);
		textNuméroMobile.setEditable(b);
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
		
		JLabel lblPrénom = new JLabel("Pr\u00E9nom");
		lblPrénom.setBounds(10, 36, 129, 14);
		add(lblPrénom);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setBounds(10, 61, 129, 14);
		add(lblSexe);
		
		JLabel lblNomPère = new JLabel("Nom p\u00E8re");
		lblNomPère.setBounds(10, 86, 129, 14);
		add(lblNomPère);
		
		JLabel lblNomJeuneFille = new JLabel("Nom jeune fille");
		lblNomJeuneFille.setBounds(10, 111, 129, 14);
		add(lblNomJeuneFille);
		
		JLabel lblNomMère = new JLabel("Nom m\u00E8re");
		lblNomMère.setBounds(10, 136, 129, 14);
		add(lblNomMère);
		
		JLabel lblDateNaissance = new JLabel("Date de Naissance");
		lblDateNaissance.setBounds(10, 161, 129, 14);
		add(lblDateNaissance);
		
		JLabel lblNationalité = new JLabel("Nationalit\u00E9");
		lblNationalité.setBounds(10, 186, 129, 14);
		add(lblNationalité);
		
		JLabel lblNuméroRegistre = new JLabel("Num\u00E9ro du registre");
		lblNuméroRegistre.setBounds(10, 211, 129, 14);
		add(lblNuméroRegistre);
		
		JLabel lblEtatCivil = new JLabel("Etat civil");
		lblEtatCivil.setBounds(10, 236, 129, 14);
		add(lblEtatCivil);
		
		JLabel lblNombreDépendants = new JLabel("Nombre de d\u00E9pendants");
		lblNombreDépendants.setBounds(10, 261, 134, 14);
		add(lblNombreDépendants);
		
		JLabel lblNiveauEducation = new JLabel("Niveau d'\u00E9ducation");
		lblNiveauEducation.setBounds(10, 286, 129, 14);
		add(lblNiveauEducation);
		
		JLabel lblAdresseEmail = new JLabel("Adresse Email");
		lblAdresseEmail.setBounds(10, 311, 129, 14);
		add(lblAdresseEmail);
		
		JLabel lblNuméroMobile = new JLabel("Num\u00E9ro mobile");
		lblNuméroMobile.setBounds(10, 336, 129, 14);
		add(lblNuméroMobile);
		
		textNom = new JTextField();
		textNom.setBounds(154, 5, 185, 20);
		add(textNom);
		textNom.setColumns(10);
		
		textPrénom = new JTextField();
		textPrénom.setBounds(154, 30, 185, 20);
		add(textPrénom);
		textPrénom.setColumns(10);
		
		textNomPère = new JTextField();
		textNomPère.setBounds(154, 83, 185, 20);
		add(textNomPère);
		textNomPère.setColumns(10);
		
		textNomJeuneFille = new JTextField();
		textNomJeuneFille.setBounds(154, 108, 185, 20);
		add(textNomJeuneFille);
		textNomJeuneFille.setColumns(10);
		
		textNomMère = new JTextField();
		textNomMère.setBounds(154, 133, 185, 20);
		add(textNomMère);
		textNomMère.setColumns(10);
		
		textJourNaissance = new JTextField();
		textJourNaissance.setBounds(154, 158, 42, 20);
		add(textJourNaissance);
		textJourNaissance.setColumns(10);
		
		textNuméroRegistre = new JTextField();
		textNuméroRegistre.setBounds(154, 208, 185, 20);
		add(textNuméroRegistre);
		textNuméroRegistre.setColumns(10);
		
		textNombreDépendants = new JTextField();
		textNombreDépendants.setBounds(154, 258, 185, 20);
		add(textNombreDépendants);
		textNombreDépendants.setColumns(10);
		
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
		
		textNuméroMobile = new JTextField();
		textNuméroMobile.setBounds(154, 333, 185, 20);
		add(textNuméroMobile);
		textNuméroMobile.setColumns(10);
		
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

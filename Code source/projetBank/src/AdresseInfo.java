import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Choice;
import java.util.Locale;



@SuppressWarnings("serial")
public class AdresseInfo extends JPanel {
	public  JTextField textNuméroAppartement;
	public  JTextField textEtage;
	public JTextField textNomBatiment;
	public JTextField textRue;
	public JTextField textDistrict;
	public JTextField textVille;
	public JTextField textCaza;
	public JTextField textCodePostal;
	public JTextField textNuméroTéléphone;
	public JTextField textJour;
	public JTextField textMois;
	public JTextField textAnne;
	public Choice choicePays;
	public Choice choiceEtatHabitat;

	void setEditable(boolean b)
	{
		 choiceEtatHabitat.setEnabled( b);
		textNuméroAppartement.setEditable( b);
		textEtage.setEditable( b);
		textNomBatiment.setEditable( b);
		textRue.setEditable( b);
		textDistrict.setEditable( b);
		textVille.setEditable( b);
		textCaza.setEditable( b);
		choicePays.setEnabled( b);
		textCodePostal.setEditable( b);
		textNuméroTéléphone.setEditable( b);
		textJour.setEditable( b);
		textMois.setEditable( b);
		textAnne.setEditable( b);
		
	}
	public AdresseInfo()  {
		setLayout(null);
		
		JLabel lblEtatHabitat = new JLabel("Etat habitat");
		lblEtatHabitat.setBounds(10, 10, 116, 14);
		add(lblEtatHabitat);
		
		JLabel lblNuméroAppartement = new JLabel("Num\u00E9ro appartement");
		lblNuméroAppartement.setBounds(10, 35, 133, 14);
		add(lblNuméroAppartement);
		
		textNuméroAppartement = new JTextField();
		textNuméroAppartement.setBounds(153, 30, 133, 20);
		add(textNuméroAppartement);
		textNuméroAppartement.setColumns(10);
		
		JLabel lblEtage = new JLabel("Etage");
		lblEtage.setBounds(10, 58, 116, 14);
		add(lblEtage);
		
		textEtage = new JTextField();
		textEtage.setBounds(153, 55, 133, 20);
		add(textEtage);
		textEtage.setColumns(10);
		
		JLabel lblNomBatiment = new JLabel("Nom batiment");
		lblNomBatiment.setBounds(10, 83, 116, 14);
		add(lblNomBatiment);
		
		textNomBatiment = new JTextField();
		textNomBatiment.setBounds(153, 80, 133, 20);
		add(textNomBatiment);
		textNomBatiment.setColumns(10);
		
		JLabel lblRue = new JLabel("Rue");
		lblRue.setBounds(10, 108, 102, 14);
		add(lblRue);
		
		textRue = new JTextField();
		textRue.setBounds(153, 105, 133, 20);
		add(textRue);
		textRue.setColumns(10);
		
		JLabel lblDistinct = new JLabel("District");
		lblDistinct.setBounds(10, 133, 116, 14);
		add(lblDistinct);
		
		textDistrict = new JTextField();
		textDistrict.setBounds(153, 130, 133, 20);
		add(textDistrict);
		textDistrict.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(10, 158, 88, 14);
		add(lblVille);
		
		textVille = new JTextField();
		textVille.setBounds(153, 155, 133, 20);
		add(textVille);
		textVille.setColumns(10);
		
		JLabel lblCaza = new JLabel("Caza");
		lblCaza.setBounds(10, 183, 102, 14);
		add(lblCaza);
		
		textCaza = new JTextField();
		textCaza.setBounds(153, 180, 133, 20);
		add(textCaza);
		textCaza.setColumns(10);
		
		JLabel lblPays = new JLabel("Pays");
		lblPays.setBounds(10, 208, 116, 14);
		add(lblPays);
		
		JLabel lblDateDéménagement = new JLabel("Date de d\u00E9m\u00E9nagement");
		lblDateDéménagement.setBounds(10, 233, 151, 14);
		add(lblDateDéménagement);
		
		JLabel lblCodePostal = new JLabel("Code postal");
		lblCodePostal.setBounds(10, 258, 116, 14);
		add(lblCodePostal);
		
		textCodePostal = new JTextField();
		textCodePostal.setBounds(153, 255, 133, 20);
		add(textCodePostal);
		textCodePostal.setColumns(10);
		
		JLabel lblNuméroTéléphone = new JLabel("Num\u00E9ro t\u00E9l\u00E9phone");
		lblNuméroTéléphone.setBounds(10, 283, 133, 14);
		add(lblNuméroTéléphone);
		
		textNuméroTéléphone = new JTextField();
		textNuméroTéléphone.setBounds(153, 280, 133, 20);
		add(textNuméroTéléphone);
		textNuméroTéléphone.setColumns(10);
		this.setAutoscrolls(true);
		
		textJour = new JTextField();
		textJour.setColumns(10);
		textJour.setBounds(153, 230, 24, 20);
		add(textJour);
		
		textMois = new JTextField();
		textMois.setColumns(10);
		textMois.setBounds(197, 230, 24, 20);
		add(textMois);
		
		textAnne = new JTextField();
		textAnne.setColumns(10);
		textAnne.setBounds(248, 230, 38, 20);
		add(textAnne);
		
		JLabel label = new JLabel("/");
		label.setBounds(231, 233, 11, 14);
		add(label);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(187, 233, 11, 14);
		add(label_1);
		
		choicePays = new Choice();
		String[] list = Locale.getISOCountries();
		Locale locales;
		for (int i=0;i<list.length;i++) {
			locales=new Locale(" ",list[i]);
			choicePays.add(locales.getDisplayCountry());
			locales=null;
			
		}
		choicePays.setBounds(153, 204, 133, 20);
		add(choicePays);
		
		choiceEtatHabitat = new Choice();
		for(EtatHabitat etat: EtatHabitat.values()){
			choiceEtatHabitat.add(etat.toString());
		}
		
		
	
		choiceEtatHabitat.setBounds(153, 4, 133, 20);
		add(choiceEtatHabitat);

	}


}

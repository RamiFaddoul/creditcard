import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;


public class CarteSup extends JPanel {
	public JTextField textnom;
	public JTextField textprenom;
	public JTextField textLien;
	public JTextField textNationalite;
	public JTextField textnumtel;
	public JTextField textnumtelM;
	public JTextField textemail;
	public JTextArea textAdr;
	public JCheckBox checkBoxM;
	public JCheckBox checkBoxF;

	/**
	 * Create the panel.
	 */
	public CarteSup() {
		setLayout(null);
		
		JLabel label = new JLabel("Nom");
		label.setBounds(10, 17, 46, 14);
		add(label);
		
		textnom = new JTextField();
		textnom.setEnabled(false);
		textnom.setColumns(10);
		textnom.setBounds(120, 14, 127, 20);
		add(textnom);
		
		JLabel label_1 = new JLabel("Pr\u00E9nom");
		label_1.setBounds(10, 46, 104, 14);
		add(label_1);
		
		textprenom = new JTextField();
		textprenom.setEnabled(false);
		textprenom.setColumns(10);
		textprenom.setBounds(120, 43, 127, 20);
		add(textprenom);
		
		JLabel label_2 = new JLabel("Sexe");
		label_2.setBounds(10, 75, 46, 14);
		add(label_2);
		
		checkBoxM = new JCheckBox("M");
		checkBoxM.setEnabled(false);
		checkBoxM.setBounds(120, 71, 53, 23);
		add(checkBoxM);
		
		checkBoxF = new JCheckBox("F");
		checkBoxF.setEnabled(false);
		checkBoxF.setBounds(201, 71, 46, 23);
		add(checkBoxF);
		
		JLabel label_3 = new JLabel("Lien de relativit\u00E9e");
		label_3.setBounds(10, 103, 103, 14);
		add(label_3);
		
		textLien = new JTextField();
		textLien.setEnabled(false);
		textLien.setColumns(10);
		textLien.setBounds(120, 100, 127, 20);
		add(textLien);
		
		JLabel label_4 = new JLabel("Nationalit\u00E9");
		label_4.setBounds(283, 14, 103, 14);
		add(label_4);
		
		textNationalite = new JTextField();
		textNationalite.setEnabled(false);
		textNationalite.setColumns(10);
		textNationalite.setBounds(416, 11, 140, 20);
		add(textNationalite);
		
		JLabel label_5 = new JLabel("Num\u00E9ro de Tel.");
		label_5.setBounds(283, 43, 103, 14);
		add(label_5);
		
		textnumtel = new JTextField();
		textnumtel.setEnabled(false);
		textnumtel.setColumns(10);
		textnumtel.setBounds(416, 40, 140, 20);
		add(textnumtel);
		
		JLabel label_6 = new JLabel("Num\u00E9ro de Tel. Mobile");
		label_6.setBounds(283, 72, 140, 14);
		add(label_6);
		
		textnumtelM = new JTextField();
		textnumtelM.setEnabled(false);
		textnumtelM.setColumns(10);
		textnumtelM.setBounds(416, 69, 140, 20);
		add(textnumtelM);
		
		JLabel label_7 = new JLabel("Addresse Email");
		label_7.setBounds(283, 98, 103, 20);
		add(label_7);
		
		textemail = new JTextField();
		textemail.setEnabled(false);
		textemail.setColumns(10);
		textemail.setBounds(416, 98, 140, 20);
		add(textemail);
		
		JLabel label_8 = new JLabel("Addresse :");
		label_8.setBounds(283, 130, 103, 14);
		add(label_8);
		
		textAdr = new JTextArea();
		textAdr.setEnabled(false);
		textAdr.setBounds(378, 130, 194, 51);
		add(textAdr);

	}
}

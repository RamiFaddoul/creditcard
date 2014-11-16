import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;


@SuppressWarnings("serial")
public class Emploi extends JPanel {

	public AdresseInfo adinfo;
	public InformationsEmploi  infoem;
	
	 void setEditable(boolean b)
		{
		  infoem.setEditable(b);
		  adinfo.setEditable(b);
		}
	public Emploi() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Informations emploi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(92, 3, 142, 26);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Adresse emploi");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(404, 3, 105, 26);
		add(lblNewLabel_1);
		infoem  = new InformationsEmploi();
		infoem.setBounds(10, 29, 294, 274);
		add(infoem);
		adinfo = new AdresseInfo();
		adinfo.choiceEtatHabitat.removeAll();
		adinfo.choiceEtatHabitat.add("Inconnu");
		adinfo.choiceEtatHabitat.setEnabled(false);
		adinfo.setBounds(306, 29, 294, 351);
		add(adinfo);
		
	}

	
}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Choice;


public class AjoutEngagement extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNomDeb;
	private JTextField textTypeEng;
	private JTextField textValeur;
	private JTextField textPayMens;
	private PanelEngagementMensuel p;
	private JButton okButton;
	private JButton cancelButton;
	private Choice choiceTypeDeb;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public AjoutEngagement(PanelEngagementMensuel engMens) {
		p=engMens;
		setBounds(100, 100, 463, 235);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNomDbiteur = new JLabel("Nom d\u00E9biteur");
		lblNomDbiteur.setBounds(10, 15, 114, 14);
		contentPanel.add(lblNomDbiteur);
		
		JLabel lblTypedeb = new JLabel("Type d\u00E9biteur");
		lblTypedeb.setBounds(10, 44, 114, 14);
		contentPanel.add(lblTypedeb);
		
		textNomDeb = new JTextField();
		textNomDeb.setBounds(141, 10, 86, 20);
		contentPanel.add(textNomDeb);
		textNomDeb.setColumns(10);
		
		JLabel lblTypeEngagement = new JLabel("Type engagement");
		lblTypeEngagement.setBounds(10, 73, 114, 14);
		contentPanel.add(lblTypeEngagement);
		
		JLabel lblValeur = new JLabel("Valeur");
		lblValeur.setBounds(10, 102, 56, 14);
		contentPanel.add(lblValeur);
		
		JLabel lblPayementMensuel = new JLabel("Payement mensuel");
		lblPayementMensuel.setBounds(10, 131, 114, 14);
		contentPanel.add(lblPayementMensuel);
		
		textTypeEng = new JTextField();
		textTypeEng.setBounds(141, 70, 86, 20);
		contentPanel.add(textTypeEng);
		textTypeEng.setColumns(10);
		
		textValeur = new JTextField();
		textValeur.setBounds(141, 100, 86, 20);
		contentPanel.add(textValeur);
		textValeur.setColumns(10);
		
		textPayMens = new JTextField();
		textPayMens.setBounds(141, 130, 86, 20);
		contentPanel.add(textPayMens);
		textPayMens.setColumns(10);
		
		choiceTypeDeb = new Choice();
		choiceTypeDeb.setBounds(141, 40, 86, 20);
		choiceTypeDeb.add("Banque");
		choiceTypeDeb.add("Personne");
		contentPanel.add(choiceTypeDeb);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == okButton)
		{
			
		       Vector<Object> vect =new Vector<Object>();
		       vect.add(textNomDeb.getText());
		       vect.add(choiceTypeDeb.getSelectedItem());
		       vect.add(textTypeEng.getText());
		       vect.add(textValeur.getText());
		       vect.add(textPayMens.getText());
		       p.getTable().addRow(vect);
		       this.dispose();
			
		}
		else if(arg0.getSource() == cancelButton) {
			this.dispose();
			
		}
		
	}
	
}

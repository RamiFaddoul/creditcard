import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;


public class AjoutFinancier extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	public JTextField textType;
	public JTextField textSMF;
	public JTextField textSMV;
	public JButton okButton;
	public JButton cancelButton;
	public TablePanel table;
	/**
	 * Create the dialog.
	 */
	public AjoutFinancier(TablePanel t) {
		table=t;
		setBounds(100, 100, 426, 197);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblType = new JLabel("Type");
			lblType.setBounds(23, 20, 40, 14);
			contentPanel.add(lblType);
		}
		
		textType = new JTextField();
		textType.setBounds(188, 17, 116, 20);
		contentPanel.add(textType);
		textType.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Salaire Mensuel Fixe");
		lblNewLabel.setBounds(23, 54, 128, 14);
		contentPanel.add(lblNewLabel);
		
		textSMF = new JTextField();
		textSMF.setColumns(10);
		textSMF.setBounds(188, 51, 116, 20);
		contentPanel.add(textSMF);
		
		JLabel lblSalaireMensuelVariable = new JLabel("Salaire Mensuel Variable");
		lblSalaireMensuelVariable.setBounds(23, 88, 150, 14);
		contentPanel.add(lblSalaireMensuelVariable);
		
		textSMV = new JTextField();
		textSMV.setColumns(10);
		textSMV.setBounds(188, 82, 116, 20);
		contentPanel.add(textSMV);
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
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==okButton)
		{
			Vector<Object> vect =new Vector<Object>();
		       vect.add(textType.getText());
		       vect.add(textSMF.getText());
		       vect.add(textSMV.getText());
		  
		       table.addRow(vect);
		      
		       this.dispose();
		       
		}
		else if(e.getSource()==cancelButton)
		{
			this.dispose();
		}
	}
	
}

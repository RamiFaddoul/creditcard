import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.Color;


public class LoginDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textNomBanque;
	private JTextField textCodeBanque;
	private JPasswordField textpasswordBanque;
	private JTextField textUserName;
	private JPasswordField textpassword;
    private JButton loginBtn;
    private JLabel lblerror;
   private JButton cancelButton;
    boolean first=true;
	/**
	 * Create the dialog.
	 */
	public LoginDialog(boolean first) {
		this.first=first;
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 349, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Nom banque");
		label.setBounds(10, 14, 112, 14);
		contentPanel.add(label);
		
		textNomBanque = new JTextField();
		textNomBanque.setColumns(10);
		textNomBanque.setBounds(132, 9, 146, 20);
		contentPanel.add(textNomBanque);
		
		JLabel label_1 = new JLabel("Code Banque");
		label_1.setBounds(10, 42, 112, 14);
		contentPanel.add(label_1);
		
		textCodeBanque = new JTextField();
		textCodeBanque.setColumns(10);
		textCodeBanque.setBounds(132, 38, 146, 20);
		contentPanel.add(textCodeBanque);
		
		textpasswordBanque = new JPasswordField();
		textpasswordBanque.setBounds(132, 67, 146, 20);
		contentPanel.add(textpasswordBanque);
		
		JLabel label_2 = new JLabel("Password Banque");
		label_2.setBounds(10, 70, 112, 14);
		contentPanel.add(label_2);
		
		textUserName = new JTextField();
		textUserName.setColumns(10);
		textUserName.setBounds(132, 96, 146, 20);
		contentPanel.add(textUserName);
		
		textpassword = new JPasswordField();
		textpassword.setBounds(132, 125, 146, 20);
		contentPanel.add(textpassword);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBounds(10, 98, 112, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 126, 112, 14);
		contentPanel.add(lblPassword);
		
		lblerror = new JLabel("Incorrect Credentials");
		lblerror.setForeground(Color.RED);
		lblerror.setVisible(false);
		lblerror.setBounds(10, 158, 125, 14);
		contentPanel.add(lblerror);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				loginBtn = new JButton("Login");
				loginBtn.addActionListener(this);
				
				buttonPane.add(loginBtn);
				getRootPane().setDefaultButton(loginBtn);
			}
			{
				 cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setModal(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==loginBtn){
			
			Statement s;
			ResultSet res;
			try {
				
				s=FenetrePrincipale.connection.createStatement();
		        res= s.executeQuery("Select * from banque,employe where banque.IDBANQUE="+Integer.parseInt(textCodeBanque.getText())+" and banque.IDBANQUE=employe.IDBANQUE and employe.username='"+textUserName.getText()+"'");
				res.next();
				
			    if(Arrays.equals(textpasswordBanque.getPassword(),res.getString("MotDePasse").toCharArray()) && res.getString("NomBanque").equals(textNomBanque.getText()) && 
			    		res.getString("Username").equals(textUserName.getText()) && 
	                    Arrays.equals(textpassword.getPassword(),res.getString("password").toCharArray()))
			    {
			    	
			    	FenetrePrincipale.IdBanque=Integer.parseInt(textCodeBanque.getText());
			    	FenetrePrincipale.username=res.getString("Username");
			    	this.dispose();
			    	}
			    else{  
			    	lblerror.setVisible(true);
			    }
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				lblerror.setVisible(true);
			}
		}if(e.getSource()==cancelButton){
		try{
		if(first==true)	{System.exit(0);FenetrePrincipale.connection.close();}
		else this.dispose();
		}
		catch(Exception exc){}
		}
	}
}

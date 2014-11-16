import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;


public class CreateUser extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textUsername;
	private JButton okButton;
	private JPasswordField passwordField;
	private JCheckBox ckbxNouvApp;
	private JCheckBox ckbxInfoClient;
	private JCheckBox ckbxApp;
	private JCheckBox chckbxSystmeDeScoring;
	private JCheckBox ckbxProduits;
	private JCheckBox ckbxModifierPriv;
    private String username;
    private JButton cancelButton;
	

	/**
	 * Create the dialog.
	 */
	public CreateUser(String user) {
		username=user;

		setBounds(100, 100, 450, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 86, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 36, 86, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblPrivilges = new JLabel("Privil\u00E8ges:");
		lblPrivilges.setBounds(10, 61, 86, 14);
		contentPanel.add(lblPrivilges);
		
		textUsername = new JTextField();
		if(username!=null){ textUsername.setText(username); textUsername.setEditable(false);}
		textUsername.setBounds(106, 8, 86, 20);
		contentPanel.add(textUsername);
		textUsername.setColumns(10);
		
		ckbxNouvApp = new JCheckBox("Remplir une nouvelle application");
		if(this.havePrivilege("Nouvelle application")) ckbxNouvApp.setSelected(true);
		ckbxNouvApp.setBounds(102, 57, 250, 23);
		contentPanel.add(ckbxNouvApp);
		
		ckbxInfoClient = new JCheckBox("Voir informations clients");
		if(this.havePrivilege("Clients")) ckbxInfoClient.setSelected(true);
		ckbxInfoClient.setBounds(102, 83, 275, 23);
		contentPanel.add(ckbxInfoClient);
		
		ckbxApp = new JCheckBox("Voir applications");
		if(this.havePrivilege("Applications")) ckbxApp.setSelected(true);
		ckbxApp.setBounds(102, 109, 271, 23);
		contentPanel.add(ckbxApp);
		
		chckbxSystmeDeScoring = new JCheckBox("Syst\u00E8me de scoring");
		if(this.havePrivilege("Systeme de scoring")) chckbxSystmeDeScoring.setSelected(true);
		chckbxSystmeDeScoring.setBounds(102, 137, 250, 23);
		contentPanel.add(chckbxSystmeDeScoring);
		
		ckbxProduits = new JCheckBox("Voir produits");
		if(this.havePrivilege("Produits")) ckbxProduits.setSelected(true);
		ckbxProduits.setBounds(102, 164, 271, 23);
		contentPanel.add(ckbxProduits);
		
		ckbxModifierPriv = new JCheckBox("Modifier les utilisateurs");
		if(this.havePrivilege("Utilisateur")) ckbxModifierPriv.setSelected(true);
		ckbxModifierPriv.setBounds(102, 190, 271, 23);
		contentPanel.add(ckbxModifierPriv);
		
		passwordField = new JPasswordField();
		if(username!=null){ 
			Statement s;
			ResultSet res;
			try{
				s=FenetrePrincipale.connection.createStatement();
				res = s.executeQuery("select PASSWORD from employe where USERNAME='"+username+"' and IDBANQUE="+FenetrePrincipale.IdBanque);
				res.next();
				passwordField.setText(res.getString("PASSWORD"));
			}catch (Exception exc){
				
			}
		}
		passwordField.setBounds(106, 33, 86, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if(username!=null)
		 if(!username.equals(FenetrePrincipale.username)) this.passwordField.setEditable(false);
	}
	
	private boolean havePrivilege(String str){
		if(username==null) return false;
		Statement s;
		ResultSet res;
		try{
			s=FenetrePrincipale.connection.createStatement();
			res=s.executeQuery("select NOMPRIVILEGE from  PRIVILEGE,POSSEDE where PRIVILEGE.IDPRIVILEGE=POSSEDE.IDPRIVILEGE and POSSEDE.USERNAME='"+username+"'");
             while(res.next()){
            	 if(res.getString("NOMPRIVILEGE").equals(str)) return true;
             }
		}catch(Exception exc){}
		return false;
	}
	
	
	
	public void actionPerformed(ActionEvent arg)
	{
		if(arg.getSource()==okButton && username==null)
		{
			try{
			Statement s;
			ResultSet res;
			ResultSet res1;
			s=FenetrePrincipale.connection.createStatement();
			String pass="";
			for(int i=0;i<passwordField.getPassword().length;i++){
				pass+=passwordField.getPassword()[i];
			}
			System.out.println(pass);
			res=s.executeQuery("insert into Employe values('"+textUsername.getText()+"','"+FenetrePrincipale.IdBanque+"','"+pass+"')");
			
			if(ckbxNouvApp.isSelected()==true)
			{
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Nouvelle application' ");
				res1.next();
				res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
			}
			if(ckbxInfoClient.isSelected()==true)
			{
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Clients' ");
				res1.next();
				res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
			}
			if(ckbxApp.isSelected()==true)
			{
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Applications' ");
				res1.next();
				res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
			}
			if(chckbxSystmeDeScoring.isSelected()==true)
			{
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Systeme de scoring' ");
				res1.next();
				res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
			}
			if(ckbxProduits.isSelected()==true)
			{
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Produits' ");
				res1.next();
				res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
			}
			if(ckbxModifierPriv.isSelected()==true)
			{
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Modifier privileges' ");
				res1.next();
				res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
			}
		
			
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
				}
			this.dispose();
			
		}else if(arg.getSource()==okButton && username!=null){
			try{
				Statement s;
				ResultSet res;
				ResultSet res1;
				s=FenetrePrincipale.connection.createStatement();
				
				String pass="";
				for(int i=0;i<passwordField.getPassword().length;i++){
					pass+=passwordField.getPassword()[i];
				}
				
				res=s.executeQuery("update Employe set PASSWORD='"+pass+"' where username='"+username+"' and IDBANQUE="+FenetrePrincipale.IdBanque);
				
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Nouvelle application' ");
				res1.next();
				if(ckbxNouvApp.isSelected()==true && !this.havePrivilege("Nouvelle application"))
				{
					
					res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
				}
				else if(!ckbxNouvApp.isSelected() && this.havePrivilege("Nouvelle application")){
					
					s.execute("delete from possede where USERNAME='"+username+"' and IDPRIVILEGE="+res1.getString("idprivilege"));
				}
				
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Clients' ");
				res1.next();
				if(ckbxInfoClient.isSelected()==true && !this.havePrivilege("Clients"))
				{	
					res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
				}
				else if(!ckbxInfoClient.isSelected() && this.havePrivilege("Clients")){
					s.execute("delete from possede where USERNAME='"+username+"' and IDPRIVILEGE="+res1.getString("idprivilege"));

				}
				
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Applications' ");
				res1.next();
				if(ckbxApp.isSelected()==true && !this.havePrivilege("Applications"))
				{
					
					res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
				}else if(ckbxApp.isSelected()==false && this.havePrivilege("Applications")){
					s.execute("delete from possede where USERNAME='"+username+"' and IDPRIVILEGE="+res1.getString("idprivilege"));

				}
				
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Systeme de scoring' ");
				res1.next();
				if(chckbxSystmeDeScoring.isSelected()==true && !this.havePrivilege("Systeme de scoring"))
				{
					res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
				}else if(chckbxSystmeDeScoring.isSelected()==false && this.havePrivilege("Systeme de scoring")){
					s.execute("delete from possede where USERNAME='"+username+"' and IDPRIVILEGE="+res1.getString("idprivilege"));

				}
				
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Produits' ");
				res1.next();
				if(ckbxProduits.isSelected()==true && !this.havePrivilege("Produits"))
				{
					
					res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
				}
				else if(ckbxProduits.isSelected()==false && this.havePrivilege("Produits")){
					s.execute("delete from possede where USERNAME='"+username+"' and IDPRIVILEGE="+res1.getString("idprivilege"));

				}
				
				res1=s.executeQuery("select idprivilege from privilege where nomprivilege='Utilisateur'");
				res1.next();
				if(ckbxModifierPriv.isSelected()==true && !this.havePrivilege("Utilisateur"))
				{
					res=s.executeQuery("insert into possede values('"+res1.getString("idprivilege")+"','"+textUsername.getText()+"')");
				}else if(ckbxModifierPriv.isSelected()==false && this.havePrivilege("Utilisateur")){
					s.execute("delete from possede where USERNAME='"+username+"' and IDPRIVILEGE="+res1.getString("idprivilege"));

				}
			
				
				}
				catch(SQLException e){
					System.out.println(e.getMessage());
					}
				this.dispose();
		}
		else if(arg.getSource()==cancelButton) this.dispose();
	}
}

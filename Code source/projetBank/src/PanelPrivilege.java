import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class PanelPrivilege extends JPanel implements ActionListener,ListSelectionListener{
	public TablePanel table;
	private TablePanel priv;
	private JButton btnModifier;
	private boolean selecting=true;
	private JButton btnNouveau;
	/**
	 * Create the panel.
	 */
	public PanelPrivilege() {

		setLayout(null);
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("Nom Utilisateur");
	     table = new TablePanel(columnNames);
	     table.setBounds(10, 11, 200, 289);
	     table.addRowSelectionListener(this);
	     add(table);
	     
	     Vector<String> columNames = new Vector<String>();
	     columNames.add("Privilege");
	     priv = new TablePanel(columNames);
	     priv.setBounds(318, 11, 200, 289);
	     
	     add(priv);
	       
	       btnModifier = new JButton("Modifier");
	       btnModifier.addActionListener(this);
	       btnModifier.setBounds(220, 11, 89, 23);
	       add(btnModifier);
	       
	       btnNouveau = new JButton("Nouveau");
	       btnNouveau.addActionListener(this);
	       btnNouveau.setBounds(220, 45, 89, 23);
	       add(btnNouveau);
	       selecting=false;
	       load();
	       selecting=true;
	}
	
	public void load(){
		try{
	          Statement s;
			  ResultSet res;
			  s=FenetrePrincipale.connection.createStatement();
		res=s.executeQuery("select * from employe where IDBANQUE="+FenetrePrincipale.IdBanque);
		
		while(res.next())
		{
			Vector<Object> vector=new Vector<Object>();
			vector.add(res.getString("Username"));
			table.addRow(vector);
		
		}
		}
		catch(SQLException exc){}
	}
	
	public void actionPerformed(ActionEvent arg)
	{
		if(arg.getSource()==btnNouveau )
		{
			CreateUser creer=new CreateUser(null);
			creer.setVisible(true);
		}
		if(arg.getSource()==btnModifier && table.getSelectedRowindex()>-1)
		{
			CreateUser creer=new CreateUser(table.getRow(table.getSelectedRowindex()).get(0).toString());
			creer.setVisible(true);
		
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(!selecting && table.getSelectedRowindex()>-1) return;
	    
		priv.empty();
		
		try{
			Statement s=FenetrePrincipale.connection.createStatement();
			   ResultSet res;
			   res=s.executeQuery("select NOMPRIVILEGE from  PRIVILEGE,POSSEDE where PRIVILEGE.IDPRIVILEGE=POSSEDE.IDPRIVILEGE and POSSEDE.USERNAME='"+table.getRow(table.getSelectedRowindex()).get(0).toString()+"'");
			   
			  
			 
			   while (res.next()){
				  Vector<Object> vector=new Vector<Object>();
				
		          vector.add(res.getString("nomprivilege"));
		           priv.addRow(vector);
				 
			   }
		 }
			catch(Exception exc){
			  System.out.println(exc.getMessage());
			}
	}
}

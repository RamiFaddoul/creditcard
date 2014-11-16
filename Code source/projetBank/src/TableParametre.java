import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class TableParametre extends JFrame implements ActionListener{
	 private TablePanel table;
	 private JButton btnAjouter;
     private JPanel contentPane;
     private JButton btnSupprimer;
     private JButton btnAnnuler;
     private JButton btnConfirmer;
     private PanelTableScoring parent;
     private boolean editing=true;
	/**
	 * Create the panel.
	 */
	public TableParametre(PanelTableScoring p) {
		parent=p;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		 
	     Vector<String> columnNames = new Vector<String>();
	     columnNames.add("Paramètre");
	  	 columnNames.add("Valeur Min.");
		 columnNames.add("Valeur Max.");
		 columnNames.add("Valeur");
	  	 columnNames.add("Score");
		 columnNames.add("Conidtion Admise");
	     
	     table = new TablePanel(columnNames);
	     table.setBounds(10, 11, 659, 300);
	     contentPane.add(table);
	    
	       
	   btnAjouter = new JButton("Nouveau Paramètre");
	   btnAjouter.addActionListener(this);
	       btnAjouter.setBounds(692, 33, 147, 23);
	       contentPane.add(btnAjouter);
	       
	       btnSupprimer = new JButton("Supprimer");
	       btnSupprimer.addActionListener(this);
	       btnSupprimer.setBounds(692, 67, 147, 23);
	       contentPane.add(btnSupprimer);
	       
	      btnConfirmer = new JButton("Confirmer");
	       btnConfirmer.addActionListener(this);
	       btnConfirmer.setBounds(617, 337, 104, 23);
	       contentPane.add(btnConfirmer);
	       
	      btnAnnuler   = new JButton("Annuler");
	       btnAnnuler.addActionListener(this);
	       btnAnnuler.setBounds(731, 337, 97, 23);
	       contentPane.add(btnAnnuler);
		
	}
	
	public void remplir(int idversion){
		 btnAjouter.setEnabled(false);
		 btnSupprimer.setEnabled(false);
		 editing=false;
		try{
		Statement s=FenetrePrincipale.connection.createStatement();
		   ResultSet res;
		   res=s.executeQuery("select * from  parametres where idtable=(select idtable from tabledescoring where version="+
				   idversion+" and idbanque="+FenetrePrincipale.IdBanque+")");
		   while (res.next()){
			   Vector<Object> vect=new Vector<Object>();
			   vect.add(res.getString("parametre"));
			   vect.add(res.getString("valeurmin"));
			   vect.add(res.getString("valeurmax"));
			   vect.add(res.getString("valeur"));
			   vect.add(res.getString("score"));
			
			   if(res.getString("conditionadmise").equals("V")) vect.add("Oui");
			   else vect.add("Non");
			   table.addRow(vect);
		   }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
			 
			   if(e.getSource()==btnAjouter)
			  {
				  JDialog d= new AjoutParametre(table);
					
				  d.setVisible(true);
			  }
			   else if(e.getSource()==btnSupprimer)
			   {
				   table.removeSelectedRow();
			   }
			   else if(e.getSource()==btnAnnuler){
				   this.dispose();
			   }
			   else if(e.getSource()==btnConfirmer && editing)
			   {
				   
				   try {
				   Statement s=FenetrePrincipale.connection.createStatement();
				   ResultSet res;
				   
				   s.execute("update tabledescoring set datefin=CURRENT_DATE where datefin is NULL and idbanque="+FenetrePrincipale.IdBanque);
				   try{
					   s.execute("insert into tabledescoring values(seq_idtable.nextval,'"+FenetrePrincipale.IdBanque+"',CURRENT_DATE,(null),(select max(version)+1 from tabledescoring where idbanque="+FenetrePrincipale.IdBanque+ "))");
				   }
				   catch (Exception e1) {s.execute("insert into tabledescoring values(seq_idtable.nextval,'"+FenetrePrincipale.IdBanque+"',CURRENT_DATE,(null),1)");}
				   for( int i=0; i< table.getRowCount(); i++)
				   {
					  String b="";
					  if(table.getRow(i).get(5)=="Oui") b="V";
					  else b="F";
					  System.out.println(table.getRow(i).get(1).toString());
				      s.execute("insert into parametres values(seq_idparametre.nextval,seq_idtable.currval,'"+table.getRow(i).get(0).toString()+
				    		  "','"+table.getRow(i).get(1).toString()+"','"+table.getRow(i).get(2).toString()+"','"+table.getRow(i).get(3).toString()+
				    		  "','"+table.getRow(i).get(4).toString()+ "','"+b+"')");
				   }
				   
				   }
				   catch (Exception exc)
				   {
					   System.out.println(exc.getMessage());
				   }
				
				   parent.load();
				
				   this.dispose();
			   }
			   else{
				   this.dispose();
			   }
	}
}

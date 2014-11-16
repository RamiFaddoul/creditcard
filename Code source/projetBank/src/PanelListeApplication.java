import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


@SuppressWarnings("serial")
public class PanelListeApplication extends JPanel implements ActionListener, ListSelectionListener{
     private TablePanel table;
     private Recherche rech;
     private JButton btnSearch;
     private JButton btnAccepter;
     private JButton btnChanger;
     private JButton btnRejetter;
     private boolean selecting=true;
     private Vector<Integer> vectidapp=new Vector<Integer>();
	/**
	 * Create the panel.
	 */
	public PanelListeApplication() {
		setLayout(null);
	       Vector<String> columnNames = new Vector<String>();
	      
	       
	       columnNames.add("Nom Applicant");
	       columnNames.add("Prénom Applicant");
	       columnNames.add("Produit");
	       columnNames.add("Limite de dépense");
	  	   columnNames.add("Date Remplissage");
		   columnNames.add("Score Requis");
		   columnNames.add("Score Obtenu");
		   columnNames.add("État");
		
	       table = new TablePanel(columnNames);
	       table.setBounds(23, 68, 906, 317);
	       table.addRowSelectionListener(this);
	       add(table);
	       
	       JLabel lblSearchBy = new JLabel("Recherche par :");
	       lblSearchBy.setVerticalAlignment(SwingConstants.TOP);
	       lblSearchBy.setBounds(23, 24, 108, 14);
	       add(lblSearchBy);
	       
	  
	       rech=new Recherche();
	       rech.setBounds(141, 20, 613, 23);
	       add(rech);
	       
	       btnSearch = new JButton("Rechercher");
	       btnSearch.addActionListener(this);
	       btnSearch.setBounds(814, 20, 115, 23);
	       add(btnSearch);
	       
	       btnAccepter = new JButton("Accepter");
	       btnAccepter.addActionListener(this);
	       btnAccepter.setBounds(939, 68, 125, 23);
	       add(btnAccepter);
	       
	       btnRejetter = new JButton("Rejetter");
	       btnRejetter.addActionListener(this);
	       btnRejetter.setBounds(939, 102, 125, 23);
	       add(btnRejetter);
	       
	       btnChanger = new JButton("Changer");
	       btnChanger.addActionListener(this);
	       btnChanger.setBounds(939, 140, 125, 23);
	       add(btnChanger);
	       
	     
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Statement s;
		ResultSet res;
      if(e.getSource()==btnSearch){
			
			try{
				selecting=false;
				table.empty();
				vectidapp.clear();
				for(AppSearch app:AppSearch.values()){
					if(app.toString().equals(rech.choicesearch.getSelectedItem()))
					{
						s=FenetrePrincipale.connection.createStatement();
						if(app==AppSearch.IDCLIENT){
							res=s.executeQuery("select * from CLIENT,APPLICATION,PRODUIT,INFORMATIONSPERSONELLES,informationsemploi where CLIENT.IDCLIENT='"+rech.getVal()+"' and " +
									"CLIENT.IDBANQUE="+FenetrePrincipale.IdBanque+" and CLIENT.IDIP=APPLICATION.IDIP and APPLICATION.IDIP=informationspersonelles.idip" +
											" and APPLICATION.IDEMPLOI=informationsemploi.IDEMPLOI and Produit.idProduit=APPLICATION.idProduit");
							res.next();
							 Vector<Object> vect=new Vector<Object>();
							   vect.add(res.getString("NOM"));
							   vect.add(res.getString("PRENOM"));
							   vect.add(res.getString("NOMPRODUIT"));
							   vect.add(res.getString("LIMITEDEPENSE"));
							   vect.add(res.getString("DATEREMPLISSAGE").substring(8, 10)+"-"+res.getString("DATEREMPLISSAGE").substring(5, 7)+"-"+res.getString("DATEREMPLISSAGE").substring(0, 4));
							   vect.add(res.getString("SCOREMINIMALREQUIS"));
							   vect.add(res.getString("SCOREOBTENU"));
							   vect.add(res.getString("ETAT"));
							   vectidapp.add(res.getInt("IDAPPLICATION"));
							   table.addRow(vect);
						}else if(app==AppSearch.VERSION){
							
						}
						else{
							String q="";
							if(app.getType().equals("nombre")) q=" and "+app.getQuery()+">='"+rech.getVal()+"' and "+app.getQuery()+"<='"+rech.getValmax()+"'";
							else if(app.getType().equals("string")) q=" and "+app.getQuery()+"='"+rech.getVal()+"'";
				              res=s.executeQuery("select * from APPLICATION,PRODUIT,INFORMATIONSPERSONELLES,informationsemploi where APPLICATION.idbanque="+FenetrePrincipale.IdBanque+" and APPLICATION.IDIP=informationspersonelles.idip"
                            +" and APPLICATION.IDEMPLOI=informationsemploi.IDEMPLOI and Produit.idProduit=APPLICATION.idProduit"+q);
				              while(res.next()){
								  Vector<Object> vect=new Vector<Object>();
								   vect.add(res.getString("NOM"));
								   vect.add(res.getString("PRENOM"));
								   vect.add(res.getString("NOMPRODUIT"));
								   vect.add(res.getString("LIMITEDEPENSE"));
								   vect.add(res.getString("DATEREMPLISSAGE").substring(8, 10)+"-"+res.getString("DATEREMPLISSAGE").substring(5, 7)+"-"+res.getString("DATEREMPLISSAGE").substring(0, 4));
								  
								   vect.add(res.getString("SCOREMINIMALREQUIS"));
								   vect.add(res.getString("SCOREOBTENU"));
								   vect.add(res.getString("ETAT"));
								   vectidapp.add(res.getInt("IDAPPLICATION"));
								   table.addRow(vect);
							}
				              
						}
						break;
				   }
				}
				selecting=true;
			}
			catch(Exception exc){
				 System.out.println(exc.getMessage());
			}
		}
      else if(e.getSource()==btnRejetter && table.getSelectedRowindex()>-1){
    	  try{
    	  s=FenetrePrincipale.connection.createStatement();
    	  s.execute("update APPLICATION set ETAT='Rejete' where IDAPPLICATION="+vectidapp.get(table.getSelectedRowindex()));
    	  reload();
    	  }
    	  catch(Exception exc){
    		 
    	  }
      }
      else if(e.getSource()==btnAccepter && table.getSelectedRowindex()>-1){
    	  try{
    		  Statement s1,s2,s3,sta;
    		  ResultSet res1,res2,res3,result;
    		  long idapp=vectidapp.get(table.getSelectedRowindex());
    		  FenetrePrincipale.connection.setAutoCommit(false);
    	      s=FenetrePrincipale.connection.createStatement();
    	      sta=FenetrePrincipale.connection.createStatement();
    	      result=sta.executeQuery("select CLIENT.IDCLIENT from APPLICATION,CLIENT where APPLICATION.IDBANQUE=" +
    	      		FenetrePrincipale.IdBanque+" and  APPLICATION.IDIP=CLIENT.IDIP and APPLICATION.IDAPPLICATION="+idapp);
    	      
    	      s1=FenetrePrincipale.connection.createStatement();
    	      res1=s1.executeQuery("select * from APPLICATION,PRODUIT where APPLICATION.IDAPPLICATION="+idapp+" and " +
    	      		" APPLICATION.IDPRODUIT=PRODUIT.IDPRODUIT and APPLICATION.IDBANQUE="+FenetrePrincipale.IdBanque);
    	      res1.next();
    	      
    	      res=s.executeQuery("select SEQ_IDCLIENT.nextval from dual");
    	      res.next();
    	     
    	      long idclient=FenetrePrincipale.IdBanque*100+res.getInt("NEXTVAL");
    	      if(result.next()) {idclient=Integer.parseInt(result.getString("IDCLIENT"));
    	
    	      }
    	      else{      
    	      res=s.executeQuery("select INFORMATIONSPERSONELLES.IDIP,INFORMATIONSEMPLOI.IDEMPLOI from APPLICATION,INFORMATIONSPERSONELLES,INFORMATIONSEMPLOI where" +
    	      		" APPLICATION.IDBANQUE="+FenetrePrincipale.IdBanque+" and APPLICATION.IDIP=INFORMATIONSPERSONELLES.IDIP and " +
    	      				" INFORMATIONSEMPLOI.IDEMPLOI=APPLICATION.IDEMPLOI and APPLICATION.IDAPPLICATION='"+idapp+"'");
    	      res.next();
    	      s.execute("insert into CLIENT values('"+idclient+"',"+res.getInt("IDEMPLOI")+",'"+res.getInt("IDIP")+"','"+FenetrePrincipale.IdBanque+"')");
    	      }
    	     
    	      res=s.executeQuery("select SEQ_IDCOMPTE.nextval from dual");
    	      res.next();
    	      long idcompte=FenetrePrincipale.IdBanque*12345+res.getInt("NEXTVAL");
    	      s.execute("insert into COMPTE values('"+idcompte+"','"+idclient+"',"+FenetrePrincipale.IdBanque+",'"+res1.getInt("LIMITEDEPENSE")+"')");
    	      
    	      long idcarte=((long)FenetrePrincipale.IdBanque)*1000000000+(long)res1.getInt("CURRID");
    		  System.out.println(""+idcarte);
    		  System.out.println(Luhn.generateDigit(""+idcarte));
    	      int incr=0;
    	      s2=FenetrePrincipale.connection.createStatement();
    	    
    	      res2=s2.executeQuery("select * from INFOCARTE where infocarte.idapplication="+idapp+"and carteprincipale='V'");
    		  res2.next();
    		  
    		  s.execute("insert into CARTE values('"+idcarte+Luhn.generateDigit(""+idcarte)+"','"+idcompte+"','"+res1.getInt("IDIP")+"','"+res1.getInt("IDPRODUIT")+"','"+
    				    FenetrePrincipale.IdBanque+"',current_date,add_months(current_date,36),'V','"+res2.getString("NOMSURCARTE")+"','V','"+res2.getString("SERVICESMS")+"','"+res2.getString("SERVICEMAIL")+"','"+res2.getString("SERVICELETTRE")+"')");
    		  incr++;
    		  idcarte++;
    		  s3=FenetrePrincipale.connection.createStatement();
    		  res3=s3.executeQuery("select * from INFOCARTE,INFOCARTESUP where infocarte.idapplication="+idapp+" and carteprincipale='F' and INFOCARTE.IDc=INFOCARTESUP.IDC");
    		 
    		  while (res3.next()){
    			  System.out.println(res3.getString(10));
    			  s.execute("insert into CARTE values('"+idcarte+Luhn.generateDigit(""+idcarte)+"','"+idcompte+"','"+res1.getInt("IDIP")+"','"+res1.getInt("IDPRODUIT")+"','"+
      				    FenetrePrincipale.IdBanque+"',current_date,add_months(current_date,36),'V','"+res3.getString("NOMSURCARTE")+"','F','"+res3.getString("SERVICESMS")+"','"+res3.getString("SERVICEMAIL")+"','"+res3.getString("SERVICELETTRE")+"')");
    			  s.execute("insert into CARTESUPLEMENTAIRE values('"+idcarte+Luhn.generateDigit(""+idcarte)+"','"+res3.getString("NOM")+"','"+res3.getString("PRENOM")+"','"+res3.getString("SEXE")+"','"+res3.getString("NATIONALITE")+"','"+res3.getString("LEINRELATIVE")+"','"+res3.getString("NUMEROTELMOBILE")+"','"+res3.getString("NUMEROTEL")+"','"+res3.getString("ADDRESSEEMAIL")+"','"+res3.getString("ADDRESSE")+"')");
    			  incr++;
    			  idcarte++;
    		  }
    		  int cur=res1.getInt("CURRID")+incr;
    		  s.execute("update Produit set CURRID='"+cur+"' where PRODUIT.IDPRODUIT="+res1.getString("IDPRODUIT")+" and PRODUIT.IDBANQUE="+FenetrePrincipale.IdBanque);
    		  s.execute("update APPLICATION set ETAT='Accepte' where APPLICATION.IDAPPLICATION="+idapp+" and APPLICATION.IDBANQUE="+FenetrePrincipale.IdBanque);
    		  FenetrePrincipale.connection.commit();
    		  FenetrePrincipale.connection.setAutoCommit(true);
    		  reload();
    	  }catch(Exception exc){ 
    		  try {
				FenetrePrincipale.connection.rollback();
				FenetrePrincipale.connection.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		  
    		  System.out.println(exc.getMessage());
    		
    	}
    	  
      }else if(e.getSource()==btnChanger && table.getSelectedRowindex()>-1){
    	  ChangerApp w= new ChangerApp(vectidapp.get(table.getSelectedRowindex()),Integer.parseInt(table.getRow(table.getSelectedRowindex()).get(3).toString()),this );
    	  w.setVisible(true);
      }
     
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(selecting){
			if(!table.getRow(table.getSelectedRowindex()).get(7).equals("Attente")){
				btnAccepter.setEnabled(false);
				btnRejetter.setEnabled(false);
				btnChanger.setEnabled(false);
			}else{
				btnAccepter.setEnabled(true);
				btnRejetter.setEnabled(true);
				btnChanger.setEnabled(true);
			}
		}
	}
	public void reload() {
		// TODO Auto-generated method stub
		 this.actionPerformed(new ActionEvent(btnSearch,ActionEvent.ACTION_PERFORMED,null));
	}
}


import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.InputVerifier;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionEvent;
import java.awt.font.NumericShaper.Range;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import java.lang.Math;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class NouvelleApplication extends JPanel implements ActionListener, ItemListener {

	private JLabel lblError;
private JButton btnSave;
private AdresseInfo adresseInfo;
private Emploi emploi;
private InfoFinanciere infoFin;
public PanelEngagementMensuel engMens;
public InfoCartes infoCartes;
private JSpinner limitdepenses;
private JComboBox<String> choiceTypeProduit;
private ClientInformation clientInformation;
private Vector<Integer> idproduit=new Vector<Integer>();
private Vector<Pair> depenserange=new Vector<Pair>();
private JCheckBox checkBox;
private JTextField textIDClient;
private JLabel lblNewLabel;
private JButton btnRemplir;
private boolean changement=true;
	public NouvelleApplication() {
		initialize();
	
	}


	private void initialize() {
	
		setBounds(100, 100,1191, 602);

		setLayout(null);
		
	
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		
		clientInformation = new ClientInformation();
		tabs.add("Informations Personelles",clientInformation);
		tabs.add("Adresse", adresseInfo=new AdresseInfo());
		tabs.add("Emploi",emploi=new Emploi());
		tabs.add("Informations financières",infoFin=new InfoFinanciere());
		tabs.add("Engagement(s) Mensuel(s)",engMens=new PanelEngagementMensuel());
		tabs.add("Information(s) Carte(s)",infoCartes=new InfoCartes());

		tabs.setBounds(0, 36, 1165, 509);
		add(tabs);
		
		choiceTypeProduit = new JComboBox<String>();
		choiceTypeProduit.addItemListener(this);
		choiceTypeProduit.setBounds(88, 8, 110, 20);
		add(choiceTypeProduit);
		
		JLabel lblTypeProduit = new JLabel("Type produit");
		lblTypeProduit.setBounds(10, 11, 89, 14);
		add(lblTypeProduit);
		
		JLabel lblLimiteDeDpense = new JLabel("Limite de d\u00E9penses");
		lblLimiteDeDpense.setBounds(236, 11, 122, 14);
		add(lblLimiteDeDpense);
		
		limitdepenses = new JSpinner();
		limitdepenses.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(100)));
		limitdepenses.setBounds(363, 8, 89, 20);
		add(limitdepenses);
		
		btnSave = new JButton("Enregistrer");
		btnSave.addActionListener(this);
		btnSave.setBounds(1066, 556, 99, 23);
		add(btnSave);
		
		lblError = new JLabel("Incorrect credentials");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblError.setBounds(10, 556, 169, 14);
		add(lblError);
		
		checkBox = new JCheckBox("");
		checkBox.addActionListener(this);
		checkBox.setBounds(513, 6, 21, 23);
		add(checkBox);
		
		textIDClient = new JTextField();
		textIDClient.setEnabled(false);
		textIDClient.setBounds(615, 8, 110, 20);
		add(textIDClient);
		textIDClient.setColumns(10);
		
		lblNewLabel = new JLabel("ID Client");
		lblNewLabel.setBounds(540, 11, 65, 14);
		add(lblNewLabel);
		
		btnRemplir = new JButton("Remplir");
		btnRemplir.setEnabled(false);
		btnRemplir.addActionListener(this);
		btnRemplir.setBounds(735, 7, 89, 23);
		add(btnRemplir);
		lblError.setVisible(false);

		setVisible(true);
		loadProduits();
	}
	public void loadProduits(){
		Statement s;
		ResultSet res;
		try{
			s=FenetrePrincipale.connection.createStatement();
			res=s.executeQuery("select * from produit where actif='V' and idbanque="+FenetrePrincipale.IdBanque);
			changement=false;
			while(res.next()){
				
			idproduit.add(Integer.parseInt(res.getString("IDPRODUIT")));
			
			choiceTypeProduit.addItem(res.getString("NOMPRODUIT"));
			depenserange.add(new Pair(Integer.parseInt(res.getString("LIMITEDEPENSEMIN")),
					Integer.parseInt(res.getString("LIMITEDEPENSEMAX"))));
			}
			changement=true;
			limitdepenses.setModel(new SpinnerNumberModel( 
					depenserange.get(choiceTypeProduit.getSelectedIndex()).getmin() ,
					depenserange.get(choiceTypeProduit.getSelectedIndex()).getmin() , 
					depenserange.get(choiceTypeProduit.getSelectedIndex()).getmax(),100 ));

		}
		catch(Exception exc){
		
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		Statement s;
		ResultSet res;
	
		if(arg0.getSource()==btnSave)
		{
			int idapp=0;
			try{
				FenetrePrincipale.connection.setAutoCommit(false);
			s=FenetrePrincipale.connection.createStatement();
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		    SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
			
		    
		    Date date1 = format1.parse(""+Integer.parseInt(adresseInfo.textJour.getText())+"-"+
		    		adresseInfo.textMois.getText()+"-"+
						Integer.parseInt(adresseInfo.textAnne.getText()));
		    res= s.executeQuery("select SEQ_IDAPPLICAION.nextval from dual");
		    res.next();
		    idapp=Integer.parseInt(res.getString("NEXTVAL"));
		    /////////insert
	  if(checkBox.isEnabled()){
			s.execute("insert into addresse values(seq_idadresse.nextval,'"+adresseInfo.choiceEtatHabitat.getSelectedItem()+"','"+adresseInfo.textNuméroAppartement.getText()+"','"+adresseInfo.textEtage.getText()+"','"+adresseInfo.textNomBatiment.getText()+"','"+adresseInfo.textRue.getText()+"','"+adresseInfo.textDistrict.getText()+"','"+adresseInfo.textVille.getText()+"','"+adresseInfo.textCaza.getText()+"','"+adresseInfo.choicePays.getSelectedItem()+"','"+format2.format(date1)+"','"+adresseInfo.textCodePostal.getText()+"','"+adresseInfo.textNuméroTéléphone.getText()+"')");
			
			
			char sexe='M';
			if(clientInformation.chckbxSexeMale.isSelected()==true) sexe='M';
			else if(clientInformation.chckbxSexeFemale.isSelected()==true) sexe='F';
			    Date date = format1.parse(""+Integer.parseInt(clientInformation.textJourNaissance.getText())+"-"+
						   clientInformation.textMoisNaissance.getText()+"-"+
							Integer.parseInt(clientInformation.textAnneeNaissance.getText()));
			 
			    res=s.executeQuery("insert into informationspersonelles values(seq_idip.nextval,seq_idadresse.currval,'"+
			    clientInformation.textNom.getText()+"','"+clientInformation.textPrénom.getText()+"','"+
			    sexe+"','"+clientInformation.textNomPère.getText()+"','"+
			    clientInformation.textNomJeuneFille.getText()+"','"+clientInformation.textNomMère.getText()+"','"
				 +format2.format(date)+"','"+clientInformation.choiceNationalite.getSelectedItem()+"','"+clientInformation.textNuméroRegistre.getText()+"','"+clientInformation.choiceEtatCivil.getSelectedItem()+"','"+clientInformation.textNombreDépendants.getText()+"','"+clientInformation.choiceNiveauEducation.getSelectedItem()+"','"+clientInformation.textAdresseEmail.getText()+"','"+clientInformation.textNuméroMobile.getText()+"')");
			
			
	
	
	            res=s.executeQuery("insert into informationsfinancieres values(seq_idrevenu.nextval,null,'Salaire','"+emploi.infoem.textSMF.getText()+"','"+emploi.infoem.textSMV.getText()+"')");
	
	
	
	
	            Date date3 = format1.parse(""+Integer.parseInt(emploi.adinfo.textJour.getText())+"-"+
			    emploi.adinfo.textMois.getText()+"-"+
				Integer.parseInt(emploi.adinfo.textAnne.getText()));
	            res=s.executeQuery("insert into addresse values(seq_idadresse.nextval,'"+emploi.adinfo.choiceEtatHabitat.getSelectedItem()+"','"+emploi.adinfo.textNuméroAppartement.getText()+"','"+emploi.adinfo.textEtage.getText()+"','"+emploi.adinfo.textNomBatiment.getText()+"','"+adresseInfo.textRue.getText()+"','"+emploi.adinfo.textDistrict.getText()+"','"+emploi.adinfo.textVille.getText()+"','"+emploi.adinfo.textCaza.getText()+"','"+emploi.adinfo.choicePays.getSelectedItem()+"','"+format2.format(date3)+"','"+emploi.adinfo.textCodePostal.getText()+"','"+emploi.adinfo.textNuméroTéléphone.getText()+"')");
	
	
	            Date date2 = format1.parse(""+Integer.parseInt(emploi.infoem.textJour.getText())+"-"+
			    emploi.infoem.textMois.getText()+"-"+
				Integer.parseInt(emploi.infoem.textAnne.getText()));
	             res=s.executeQuery("insert into informationsEmploi values(seq_idemploi.nextval,seq_idrevenu.currval,seq_idadresse.currval,'"+emploi.infoem.choiceEtatEmploi.getSelectedItem()+"','"+format2.format(date2)+"','"+emploi.infoem.textProfession.getText()+"','"+emploi.infoem.textTitre.getText()+"','"+emploi.infoem.textCompagnie.getText()+"','"+emploi.infoem.textSecteurActivité.getText()+"','"+emploi.infoem.textAdresseEmail.getText()+"')");	
	             s.execute("insert into Application values("+idapp+",seq_idemploi.currval,"+idproduit.get(choiceTypeProduit.getSelectedIndex())+",'"+FenetrePrincipale.IdBanque+"',CURRENT_DATE,'"+limitdepenses.getValue().toString()+"','Attente',(null),seq_idip.currval)");
		    }
	  //////////////////// update
		    else{
		    	Statement sta=FenetrePrincipale.connection.createStatement();
		    	ResultSet result;
		    	int idc,idip,ida,ide,idr,idae;
		    
		    	idc = Integer.parseInt(textIDClient.getText());
		    	
		    	// requte des ids
		    	result=sta.executeQuery("select INFORMATIONSPERSONELLES.IDIP,INFORMATIONSPERSONELLES.IDADRESSE from client,INFORMATIONSPERSONELLES where client.idip=INFORMATIONSPERSONELLES.idip and client.idclient="+idc+" and client.idbanque="+FenetrePrincipale.IdBanque);
		    	
		    	result.next();
		    	idip=Integer.parseInt(result.getString("IDIP"));
		    	ida=Integer.parseInt(result.getString("IDADRESSE"));
		    	
		    	result=sta.executeQuery("select INFORMATIONSEMPLOI.IDEMPLOI,INFORMATIONSEMPLOI.IDADRESSE,INFORMATIONSEMPLOI.IDREVENU from client,INFORMATIONSEMPLOI"+ 
		    			" where client.IDEMPLOI=INFORMATIONSEMPLOI.IDEMPLOI and client.idclient="+idc+ " and client.idbanque="+FenetrePrincipale.IdBanque);
		    	result.next();
		    	ide=Integer.parseInt(result.getString("IDEMPLOI"));
		    	idae=Integer.parseInt(result.getString("IDADRESSE"));
		    	idr=Integer.parseInt(result.getString("IDREVENU"));
		////////// update des tables selon ids
		    	
		    	
		    	s.execute("update addresse set ETATHABITAT='"+adresseInfo.choiceEtatHabitat.getSelectedItem()+"',NUMEROAPPARTEMENT='"+adresseInfo.textNuméroAppartement.getText()+"',ETAGE='"+adresseInfo.textEtage.getText()+"',NOMBATIMENT='"+adresseInfo.textNomBatiment.getText()+"',RUE='"+adresseInfo.textRue.getText()+"',DISTRICT='"+adresseInfo.textDistrict.getText()+"',VILLE='"+adresseInfo.textVille.getText()+"',CAZA='"+adresseInfo.textCaza.getText()+"',PAYS='"+adresseInfo.choicePays.getSelectedItem()+"',DATEDEMENAGEMENT='"+format2.format(date1)+"',CODEPOSTAL='"+adresseInfo.textCodePostal.getText()+"',NUMEROTELEPHONE='"+adresseInfo.textNuméroTéléphone.getText()+"' where IDADRESSE="+ida);
		    	//System.out.println("ICI");
		    	
		    	
				char sexe='M';
				if(clientInformation.chckbxSexeMale.isSelected()==true) sexe='M';
				else if(clientInformation.chckbxSexeFemale.isSelected()==true) sexe='F';
				    Date date = format1.parse(""+Integer.parseInt(clientInformation.textJourNaissance.getText())+"-"+
							   clientInformation.textMoisNaissance.getText()+"-"+
								Integer.parseInt(clientInformation.textAnneeNaissance.getText()));
				 
				   s.execute("update informationspersonelles set NOM='"+
				    clientInformation.textNom.getText()+"',PRENOM='"+clientInformation.textPrénom.getText()+"',SEXE='"+
				    sexe+"',NOMPERE='"+clientInformation.textNomPère.getText()+"',NOMJEUNEFILLE='"+
				    clientInformation.textNomJeuneFille.getText()+"',NOMMERE='"+clientInformation.textNomMère.getText()+"',DATENAISSANCE='"
					 +format2.format(date)+"',NATIONALITE='"+clientInformation.choiceNationalite.getSelectedItem()+"',NUMEROREGISTRE='"+Integer.parseInt(clientInformation.textNuméroRegistre.getText())+"',ETATCIVIL='"+clientInformation.choiceEtatCivil.getSelectedItem()+"',NOMBREDEPENDANTS='"+Integer.parseInt(clientInformation.textNombreDépendants.getText())+"',NIVEAUEDUCATION='"+clientInformation.choiceNiveauEducation.getSelectedItem()+"',ADRESSEEMAIL='"+clientInformation.textAdresseEmail.getText()+"',NUMEROTELEPHONEMOBILE='"+Integer.parseInt(clientInformation.textNuméroMobile.getText())+"' where IDIP="+idip);
				
				  
		
		            s.execute("update informationsfinancieres set SALAIREMENSUELFIXE='"+Integer.parseInt(emploi.infoem.textSMF.getText())+"',SALAIREMENSUELVARIABLE='"+Integer.parseInt(emploi.infoem.textSMV.getText())+"' where IDREVENU="+idr);
		
		
		           // emploi.adinfo;
		           
		            Date date3 = format1.parse(""+Integer.parseInt(emploi.adinfo.textJour.getText())+"-"+
				    emploi.adinfo.textMois.getText()+"-"+
					Integer.parseInt(emploi.adinfo.textAnne.getText()));
		          
			    	s.execute("update addresse set ETATHABITAT='"+emploi.adinfo.choiceEtatHabitat.getSelectedItem()+"',NUMEROAPPARTEMENT='"+emploi.adinfo.textNuméroAppartement.getText()+"',ETAGE='"+emploi.adinfo.textEtage.getText()+"',NOMBATIMENT='"+emploi.adinfo.textNomBatiment.getText()+"',RUE='"+emploi.adinfo.textRue.getText()+"',DISTRICT='"+emploi.adinfo.textDistrict.getText()+"',VILLE='"+emploi.adinfo.textVille.getText()+"',CAZA='"+emploi.adinfo.textCaza.getText()+"',PAYS='"+emploi.adinfo.choicePays.getSelectedItem()+"',DATEDEMENAGEMENT='"+format2.format(date1)+"',CODEPOSTAL='"+emploi.adinfo.textCodePostal.getText()+"',NUMEROTELEPHONE='"+emploi.adinfo.textNuméroTéléphone.getText()+"' where IDADRESSE="+idae);
		
			    	
		            Date date2 = format1.parse(""+Integer.parseInt(emploi.infoem.textJour.getText())+"-"+
				    emploi.infoem.textMois.getText()+"-"+
					Integer.parseInt(emploi.infoem.textAnne.getText()));
		           
		             s.execute("update informationsEmploi set ETATEMPLOI='"+emploi.infoem.choiceEtatEmploi.getSelectedItem()+"',DATEEMBAUCHEMENT='"+format2.format(date2)+"',PROFESSION='"+emploi.infoem.textProfession.getText()+"',TITRE='"+emploi.infoem.textTitre.getText()+"',NOMCOMPAGNIE='"+emploi.infoem.textCompagnie.getText()+"',SECTEURACTIVITE='"+emploi.infoem.textSecteurActivité.getText()+"',ADRESSEEMAIL='"+emploi.infoem.textAdresseEmail.getText()+"' where IDEMPLOI="+ide);	
		            
		             s.execute("insert into Application values("+idapp+","+ide+","+idproduit.get(choiceTypeProduit.getSelectedIndex())+",'"+FenetrePrincipale.IdBanque+"',CURRENT_DATE,'"+Integer.parseInt(limitdepenses.getValue().toString())+"','Attente',(null),"+idip+")");
			    }
	/////////////////////////////////////
   
	  
	
	for(int i=0;i<infoFin.table.getRowCount();++i)
	{
		
	   res=s.executeQuery("insert into informationsfinancieres values(seq_idrevenu.nextval,SEQ_IDAPPLICAION.currval,'"+infoFin.table.getRow(i).get(0).toString()+"','"+Integer.parseInt(infoFin.table.getRow(i).get(1).toString())+"','"+Integer.parseInt(infoFin.table.getRow(i).get(2).toString())+"')");	
	}
	                                                                                                                            
	
	for(int j=0;j<engMens.table.getRowCount();++j)
	{
		
		res=s.executeQuery("insert into engagementmensuel values(seq_idengagement.nextval,SEQ_IDAPPLICAION.currval,'"+engMens.table.getRow(j).get(0).toString()+"','"+engMens.table.getRow(j).get(1).toString()+"','"+engMens.table.getRow(j).get(2).toString()+"','"+Integer.parseInt(engMens.table.getRow(j).get(3).toString())+"','"+Integer.parseInt(engMens.table.getRow(j).get(4).toString())+"')");	
	}
	
	
	
	char choice1='F',choice2='F',choice3='F';
	if(infoCartes.choiceSMS.getSelectedItem().equals("Oui")) choice1='V';
	if(infoCartes.choiceEmail.getSelectedItem().equals("Oui")) choice2='V';
	if(infoCartes.choiceLettre.getSelectedItem().equals("Oui")) choice3='V';
	
	
	
	res=s.executeQuery("insert into infocarte values(seq_idc.nextval,SEQ_IDAPPLICAION.currval,'V','"+infoCartes.textNomCarte.getText()+"','"+choice1+"','"+choice2+"','"+choice3+"')");
	
	
	         char choice4='F',choice5='F',choice6='F';
			for(int i=0;i<infoCartes.table.getRowCount();++i)
			{
				
				if(infoCartes.table.getRow(i).get(3).toString().equals("Oui")) choice4='V';
				if(infoCartes.table.getRow(i).get(4).toString().equals("Oui")) choice5='V';
				if(infoCartes.table.getRow(i).get(5).toString().equals("Oui")) choice6='V';
				
			 res=s.executeQuery("insert into infocarte values(seq_idc.nextval,SEQ_IDAPPLICAION.currval,'F','"+infoCartes.table.getRow(i).get(2).toString()+"','"+choice4+"','"+choice5+"','"+choice6+"')");
			 res=s.executeQuery("insert into infocartesup values(seq_idc.currval,'"+infoCartes.table.getRow(i).get(0).toString()+"','"+infoCartes.table.getRow(i).get(1).toString()+"','"+ infoCartes.vect.get(i).get("Sexe")+"','"+infoCartes.vect.get(i).get("Nationalite")+"','"+ infoCartes.vect.get(i).get("Lien")+"','"+ infoCartes.vect.get(i).get("Num tel mobile")+"','"+ infoCartes.vect.get(i).get("Num tel")+"','"+ infoCartes.vect.get(i).get("Email")+"','"+ infoCartes.vect.get(i).get("Addresse")+"')");			
			}
			
			FenetrePrincipale.connection.commit();
			lblError.setVisible(false);
			}
			catch(Exception e){
				lblError.setVisible(true);
			    System.out.println(e.getMessage());
				try {
					FenetrePrincipale.connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
				}
		
				}
			try {				
				FenetrePrincipale.connection.setAutoCommit(true);
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				
			    }
			/// FIN DE ENREGISTRER
			new Thread(new Scoring(idapp)).start();
		}
		else if(arg0.getSource()==btnRemplir){
			try{
			 s=FenetrePrincipale.connection.createStatement();
			int id=Integer.parseInt(textIDClient.getText());
			//requete  infopersonnelles et addresse
		    res=s.executeQuery("select * from client,INFORMATIONSPERSONELLES,ADDRESSE where INFORMATIONSPERSONELLES.IDADRESSE=ADDRESSE.IDADRESSE" +
		    		" and client.idip=INFORMATIONSPERSONELLES.idip and client.idbanque="
			+FenetrePrincipale.IdBanque+" and client.idclient="+id);
		     res.next();
		     ///infopersonnelles
		    clientInformation.textNom.setText(res.getString("NOM"));
		    clientInformation.textPrénom.setText(res.getString("PRENOM"));
		    if(res.getString("SEXE").equals("M")) clientInformation.chckbxSexeMale.setSelected(true);
		    else clientInformation.chckbxSexeFemale.setSelected(true);
		    clientInformation.textNomPère.setText(res.getString("NOMPERE"));
		    clientInformation.textNomMère.setText(res.getString("NOMMERE"));
		    clientInformation.textNomJeuneFille.setText(res.getString("NOMJEUNEFILLE"));
            String date=res.getString("DATENAISSANCE");
		    clientInformation.textJourNaissance.setText(date.substring(8, 10));
		    clientInformation.textMoisNaissance.setText(date.substring(5, 7));
		    clientInformation.textAnneeNaissance.setText(date.substring(0, 4));
		    clientInformation.choiceNationalite.select(res.getString("NATIONALITE"));
		    clientInformation.choiceEtatCivil.select(res.getString("ETATCIVIL"));
		    clientInformation.choiceNiveauEducation.select(res.getString("NIVEAUEDUCATION"));
		    clientInformation.textNuméroMobile.setText(res.getString("NUMEROTELEPHONEMOBILE"));
		    clientInformation.textNombreDépendants.setText(res.getString("NOMBREDEPENDANTS"));
		    clientInformation.textAdresseEmail.setText(res.getString("ADRESSEEMAIL"));
		    clientInformation.textNuméroRegistre.setText(res.getString("NUMEROREGISTRE"));
		    ////addresse
		      adresseInfo.choiceEtatHabitat.select(res.getString("ETATHABITAT"));
		      adresseInfo.choicePays.select(res.getString("PAYS"));
		      date=res.getString("DATEDEMENAGEMENT");
		      adresseInfo.textJour.setText(date.substring(8, 10));
		      adresseInfo.textMois.setText(date.substring(5, 7));
		      adresseInfo.textAnne.setText(date.substring(0, 4));
		      adresseInfo.textCaza.setText(res.getString("CAZA"));
		      adresseInfo.textCodePostal.setText(res.getString("CODEPOSTAL"));
		      adresseInfo.textDistrict.setText(res.getString("DISTRICT"));
		      adresseInfo.textEtage.setText(res.getString("ETAGE"));
		      adresseInfo.textNomBatiment.setText(res.getString("NOMBATIMENT"));
		      adresseInfo.textNuméroAppartement.setText(res.getString("NUMEROAPPARTEMENT"));
		      adresseInfo.textNuméroTéléphone.setText(res.getString("NUMEROTELEPHONE"));
		      adresseInfo.textRue.setText(res.getString("RUE"));
		      adresseInfo.textVille.setText(res.getString("VILLE"));
		     ///requete infoemploi et adresseemploi
		      res=s.executeQuery("select * from client,INFORMATIONSEMPLOI,ADDRESSE,INFORMATIONSFINANCIERES where INFORMATIONSEMPLOI.IDADRESSE=ADDRESSE.IDADRESSE" +
			    		" and client.IDEMPLOI=INFORMATIONSEMPLOI.IDEMPLOI and INFORMATIONSEMPLOI.IDREVENU=INFORMATIONSFINANCIERES.IDREVENU and client.idbanque="
				+FenetrePrincipale.IdBanque+" and client.idclient="+id);
		      res.next();
		      //emploi
		      emploi.infoem.choiceEtatEmploi.select(res.getString("ETATEMPLOI"));
		      emploi.infoem.textAdresseEmail.setText(res.getString("ADRESSEEMAIL"));
		      date=res.getString("DATEEMBAUCHEMENT");
		      emploi.infoem.textJour.setText(date.substring(8, 10));
		      emploi.infoem.textMois.setText(date.substring(5,7));
		      emploi.infoem.textAnne.setText(date.substring(0,4));		      
		      emploi.infoem.textCompagnie.setText(res.getString("NOMCOMPAGNIE"));
		      emploi.infoem.textProfession.setText(res.getString("PROFESSION"));
		      emploi.infoem.textSecteurActivité.setText(res.getString("SECTEURACTIVITE"));
		      emploi.infoem.textSMF.setText(res.getString("SALAIREMENSUELFIXE"));
		      emploi.infoem.textSMV.setText(res.getString("SALAIREMENSUELVARIABLE"));
		      emploi.infoem.textTitre.setText(res.getString("TITRE"));
		      
		      //addresse emploi
		      emploi.adinfo.choiceEtatHabitat.select(res.getString("ETATHABITAT"));
		      emploi.adinfo.choicePays.select(res.getString("PAYS"));
		      date=res.getString("DATEDEMENAGEMENT");
		      emploi.adinfo.textJour.setText(date.substring(8, 10));
		      emploi.adinfo.textMois.setText(date.substring(5, 7));
		      emploi.adinfo.textAnne.setText(date.substring(0, 4));
		      emploi.adinfo.textCaza.setText(res.getString("CAZA"));
		      emploi.adinfo.textCodePostal.setText(res.getString("CODEPOSTAL"));
		      emploi.adinfo.textDistrict.setText(res.getString("DISTRICT"));
		      emploi.adinfo.textEtage.setText(res.getString("ETAGE"));
		      emploi.adinfo.textNomBatiment.setText(res.getString("NOMBATIMENT"));
		      emploi.adinfo.textNuméroAppartement.setText(res.getString("NUMEROAPPARTEMENT"));
		      emploi.adinfo.textNuméroTéléphone.setText(res.getString("NUMEROTELEPHONE"));
		      emploi.adinfo.textRue.setText(res.getString("RUE"));
		      emploi.adinfo.textVille.setText(res.getString("VILLE"));
		      checkBox.setEnabled(false);
		      textIDClient.setEnabled(false);
		      
			}catch(Exception exc){}
		}else if(arg0.getSource()==checkBox){
			btnRemplir.setEnabled(!btnRemplir.isEnabled());
			textIDClient.setEnabled(!textIDClient.isEnabled());
			
		}
				
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==choiceTypeProduit && changement){
			limitdepenses.setModel(new SpinnerNumberModel( depenserange.get(choiceTypeProduit.getSelectedIndex()).getmin() , depenserange.get(choiceTypeProduit.getSelectedIndex()).getmin() , depenserange.get(choiceTypeProduit.getSelectedIndex()).getmax(),100 ));
		}
	}
	
}

 class Pair
{
    private final int min;
    private final int max;

    public Pair(int minval, int maxval)
    {
    	min   = minval;
    	max = maxval;
    }

    public int getmin()   { return min; }
    public int getmax() { return max; }
}

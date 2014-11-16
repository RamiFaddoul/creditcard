import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


public class InfoClient extends JFrame {

	private JPanel contentPane;
	private Emploi em;
	private AdresseInfo ad;
	private ClientInformation ci;
	private InfoComptes infoComptes;
	private Vector<Long> vectcomptes=new Vector<Long>();
	private Long id;
	public InfoClient(Long id) {
		this.id=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 954, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
         ci=new ClientInformation();
        ci.setEditable(false);
        ad=new AdresseInfo();
        ad.setEditable(false);
          em=new Emploi();
        em.setEditable(false);
    	infoComptes = new  InfoComptes();
		tabs.add("Informations Personnelles",ci);
		tabs.add("Adresse", ad);
		tabs.add("Emploi",em);	
	
		tabs.addTab("Informations comptes", null, infoComptes, null);

		tabs.setBounds(10, 10, 932, 490);
		contentPane.add(tabs);
	
		setContentPane(contentPane);
		setResizable(false);
		load();
	}
	public void load(){
		try{
			
			Statement s=FenetrePrincipale.connection.createStatement();
			ResultSet res;
			//requete  infopersonnelles et addresse
		    res=s.executeQuery("select * from client,INFORMATIONSPERSONELLES,ADDRESSE where INFORMATIONSPERSONELLES.IDADRESSE=ADDRESSE.IDADRESSE" +
		    		" and client.idip=INFORMATIONSPERSONELLES.idip and client.idbanque="
			+FenetrePrincipale.IdBanque+" and client.idclient="+id);
		     res.next();
		     ///infopersonnelles
		    ci.textNom.setText(res.getString("NOM"));
		    ci.textPrénom.setText(res.getString("PRENOM"));
		    if(res.getString("SEXE").equals("M")) ci.chckbxSexeMale.setSelected(true);
		    else ci.chckbxSexeFemale.setSelected(true);
		    ci.textNomPère.setText(res.getString("NOMPERE"));
		    ci.textNomMère.setText(res.getString("NOMMERE"));
		    ci.textNomJeuneFille.setText(res.getString("NOMJEUNEFILLE"));
            String date=res.getString("DATENAISSANCE");
		    ci.textJourNaissance.setText(date.substring(8, 10));
		    ci.textMoisNaissance.setText(date.substring(5, 7));
		    ci.textAnneeNaissance.setText(date.substring(0, 4));
		    ci.choiceNationalite.select(res.getString("NATIONALITE"));
		    ci.choiceEtatCivil.select(res.getString("ETATCIVIL"));
		    ci.choiceNiveauEducation.select(res.getString("NIVEAUEDUCATION"));
		    ci.textNuméroMobile.setText(res.getString("NUMEROTELEPHONEMOBILE"));
		    ci.textNombreDépendants.setText(res.getString("NOMBREDEPENDANTS"));
		    ci.textAdresseEmail.setText(res.getString("ADRESSEEMAIL"));
		    ci.textNuméroRegistre.setText(res.getString("NUMEROREGISTRE"));
		    ////addresse
		      ad.choiceEtatHabitat.select(res.getString("ETATHABITAT"));
		      ad.choicePays.select(res.getString("PAYS"));
		      date=res.getString("DATEDEMENAGEMENT");
		      ad.textJour.setText(date.substring(8, 10));
		      ad.textMois.setText(date.substring(5, 7));
		      ad.textAnne.setText(date.substring(0, 4));
		      ad.textCaza.setText(res.getString("CAZA"));
		      ad.textCodePostal.setText(res.getString("CODEPOSTAL"));
		      ad.textDistrict.setText(res.getString("DISTRICT"));
		      ad.textEtage.setText(res.getString("ETAGE"));
		      ad.textNomBatiment.setText(res.getString("NOMBATIMENT"));
		      ad.textNuméroAppartement.setText(res.getString("NUMEROAPPARTEMENT"));
		      ad.textNuméroTéléphone.setText(res.getString("NUMEROTELEPHONE"));
		      ad.textRue.setText(res.getString("RUE"));
		      ad.textVille.setText(res.getString("VILLE"));
		     ///requete infoemploi et adresseemploi
		      res=s.executeQuery("select * from client,INFORMATIONSEMPLOI,ADDRESSE,INFORMATIONSFINANCIERES where INFORMATIONSEMPLOI.IDADRESSE=ADDRESSE.IDADRESSE" +
			    		" and client.IDEMPLOI=INFORMATIONSEMPLOI.IDEMPLOI and INFORMATIONSEMPLOI.IDREVENU=INFORMATIONSFINANCIERES.IDREVENU and client.idbanque="
				+FenetrePrincipale.IdBanque+" and client.idclient="+id);
		      res.next();
		      //emploi
		      em.infoem.textAdresseEmail.setText(res.getString("ADRESSEEMAIL"));
		      date=res.getString("DATEEMBAUCHEMENT");
		      em.infoem.textJour.setText(date.substring(8, 10));
		      em.infoem.textMois.setText(date.substring(5,7));
		      em.infoem.textAnne.setText(date.substring(0,4));		      
		      em.infoem.textCompagnie.setText(res.getString("NOMCOMPAGNIE"));
		      em.infoem.textProfession.setText(res.getString("PROFESSION"));
		      em.infoem.textSecteurActivité.setText(res.getString("SECTEURACTIVITE"));
		      em.infoem.textSMF.setText(res.getString("SALAIREMENSUELFIXE"));
		      em.infoem.textSMV.setText(res.getString("SALAIREMENSUELVARIABLE"));
		      em.infoem.textTitre.setText(res.getString("TITRE"));
		      
		      //addresse emploi
		      em.adinfo.choiceEtatHabitat.select(res.getString("ETATHABITAT"));
		      em.adinfo.choicePays.select(res.getString("PAYS"));
		      date=res.getString("DATEDEMENAGEMENT");
		      em.adinfo.textJour.setText(date.substring(8, 10));
		      em.adinfo.textMois.setText(date.substring(5, 7));
		      em.adinfo.textAnne.setText(date.substring(0, 4));
		      em.adinfo.textCaza.setText(res.getString("CAZA"));
		      em.adinfo.textCodePostal.setText(res.getString("CODEPOSTAL"));
		      em.adinfo.textDistrict.setText(res.getString("DISTRICT"));
		      em.adinfo.textEtage.setText(res.getString("ETAGE"));
		      em.adinfo.textNomBatiment.setText(res.getString("NOMBATIMENT"));
		      em.adinfo.textNuméroAppartement.setText(res.getString("NUMEROAPPARTEMENT"));
		      em.adinfo.textNuméroTéléphone.setText(res.getString("NUMEROTELEPHONE"));
		      em.adinfo.textRue.setText(res.getString("RUE"));
		      em.adinfo.textVille.setText(res.getString("VILLE"));
		    ///requete comptes
		      Vector<Vector<Object>> vect=new Vector<Vector<Object>>();
		      ResultSet result,result2,result3;
		      Statement sta=FenetrePrincipale.connection.createStatement();
		      res=s.executeQuery("select * from compte where idclient="+id+" and idbanque="+FenetrePrincipale.IdBanque);
		   //liste des comptes
		      while(res.next()){
		    	 Vector<Object> v=new Vector<Object>();
		    	 Long idc=Long.parseLong(res.getString("NUMEROCOMPTE"));
		    	 
		    	 v.add(""+idc);
		    	 v.add(res.getString("LIMITEDEPENSE"));
		    	 
		    	 result=sta.executeQuery("select count(*) from CARTE where NUMEROCOMPTE="+idc+" and IDBANQUE="+FenetrePrincipale.IdBanque);
		    	 result.next();
		    	 v.add(result.getString("count(*)"));
		    	 
		    	 result=sta.executeQuery("select avg(DEPENSE) as avg from DEPENSE_REMBOURSEMENTCOMPTE where NUMEROCOMPTE="+idc+" and IDBANQUE="+FenetrePrincipale.IdBanque);
		    	 result.next();
		    	 v.add(result.getString("avg"));
		    	 
		    	 result2=sta.executeQuery("select (sum(REMBOURSEMENT)/sum(DEPENSE))*100 as prct from DEPENSE_REMBOURSEMENTCOMPTE where NUMEROCOMPTE="+idc+" and IDBANQUE="+FenetrePrincipale.IdBanque);
		    	 result2.next();
		    	 v.add(result2.getString("prct")+"%");
		    	 vectcomptes.add(idc);
		    	 vect.add(v);
		     }
		     
		      for(int i=0;i<vect.size();i++){
		      infoComptes.table.addRow(vect.get(i));
		      }
		      
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}

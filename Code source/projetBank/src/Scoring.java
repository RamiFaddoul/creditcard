import java.sql.ResultSet;
import java.sql.Statement;


public class Scoring implements Runnable {
	private int idapp;
    public Scoring(int id){
    	idapp=id;
    }

	@Override
	public void run() {
		if (idapp==0) return;
		boolean admise=true;
		int scoretotal=0;
		Statement s,sta1,sta2,sta3,sta4;
		ResultSet res,result1,result2,result3,result4;
		try{
			// conection
         s= FenetrePrincipale.connection.createStatement();
		sta1= FenetrePrincipale.connection.createStatement();
		sta2= FenetrePrincipale.connection.createStatement();
		sta3= FenetrePrincipale.connection.createStatement();
		sta4= FenetrePrincipale.connection.createStatement();
		// requete table de scoring
		res = s.executeQuery("select * from TABLEDESCORING,PARAMETRES where TABLEDESCORING.DATEFIN is null and TABLEDESCORING.IDBANQUE="+
				FenetrePrincipale.IdBanque+" and TABLEDESCORING.IDTABLE=PARAMETRES.IDTABLE");
		

		// requete infopers
	result1=sta1.executeQuery("select round((current_date-INFORMATIONSPERSONELLES.datenaissance)/365) as AGE,INFORMATIONSPERSONELLES.*,ADDRESSE.* from APPLICATION,INFORMATIONSPERSONELLES,ADDRESSE where APPLICATION.IDBANQUE="+FenetrePrincipale.IdBanque+
			" and APPLICATION.IDIP=INFORMATIONSPERSONELLES.IDIP and ADDRESSE.IDADRESSE=INFORMATIONSPERSONELLES.IDADRESSE and APPLICATION.IDAPPLICATION="+idapp);
		//requete emploi
    result2=sta2.executeQuery("select round((current_date-INFORMATIONSEMPLOI.DATEEMBAUCHEMENT)/365) as duree,PROFESSION,SALAIREMENSUELFIXE,SALAIREMENSUELVARIABLE from APPLICATION,INFORMATIONSEMPLOI,ADDRESSE,INFORMATIONSFINANCIERES where INFORMATIONSFINANCIERES.IDREVENU=INFORMATIONSEMPLOI.IDREVENU and" +
		                    " APPLICATION.IDBANQUE="+FenetrePrincipale.IdBanque+
                       " and APPLICATION.IDEMPLOI=INFORMATIONSEMPLOI.IDEMPLOI and ADDRESSE.IDADRESSE=INFORMATIONSEMPLOI.IDADRESSE  and APPLICATION.IDAPPLICATION="+idapp);
		
		// requete app revenu
		 result3=sta3.executeQuery("select SUM(SALAIREMENSUELFIXE) as SMF,SUM(SALAIREMENSUELVARIABLE) as SMV from INFORMATIONSFINANCIERES where IDAPPLICATION="+idapp);
		//requete app negagement
		 result4=sta4.executeQuery("select SUM(VALEUR) as SV,SUM(PAYEMENTMENSUEL) as SPM from ENGAGEMENTMENSUEL where IDAPPLICATION="+idapp);
		
		result1.next();
  		result2.next();
		result3.next();
		result4.next();
		while(res.next()) {
			// init parametre
			String strpar,val,cond;
			int score=0,valmin=-1,valmax=-1;
			if(res.getString("SCORE")!=null)
			      score=Integer.parseInt(res.getString("SCORE"));
			strpar=res.getString("PARAMETRE");
			cond=res.getString("CONDITIONADMISE");
			val=res.getString("VALEUR");
			if(res.getString("VALEURMIN")!=null)
		    valmin=Integer.parseInt(res.getString("VALEURMIN"));
			if(res.getString("VALEURMAX")!=null)
		    valmax=Integer.parseInt(res.getString("VALEURMAX"));
			/// 
			for(Parametres par: Parametres.values()){
				if(par.toString().equals(strpar)){
					ResultSet result=result1;
					if(par.getTab()==1){result=result1;}
                    else if(par.getTab()==2){result=result2;}
                    else if(par.getTab()==3){result=result3;}
                    else if(par.getTab()==4){result=result4;}
					
						if(par.getType()=="nombre"){
							
							if(valmin!=-1 && valmax!=-1){
								if(cond.equals("V") && valmin<Integer.parseInt(result.getString(par.getCol())) && valmax>Integer.parseInt(result.getString(par.getCol()))){scoretotal+=score; }
								else if(cond.equals("F") && valmin<Integer.parseInt(result.getString(par.getCol())) && valmax>Integer.parseInt(result.getString(par.getCol()))){admise=false;}
							}else if(valmin!=-1){
								if(cond.equals("V") && valmin<Integer.parseInt(result.getString(par.getCol())) ){scoretotal+=score; }
								else if(cond.equals("F") && valmin<Integer.parseInt(result.getString(par.getCol()))){admise=false;}
							}else if(valmax!=-1){
								if(cond.equals("V") && valmax>Integer.parseInt(result.getString(par.getCol())) ){scoretotal+=score; }
								else if(cond.equals("F") && valmax>Integer.parseInt(result.getString(par.getCol()))){admise=false;}
							}
							
						}
						else {
							if(cond.equals("V") && val.equals(result.getString( par.getCol() )) ){scoretotal+=score; }
							else if(cond.equals("F") && val.equals(result.getString( par.getCol() )) ){admise=false;}
						}
						
					
					
				       }
			 }
			
		  }
		if(admise) FenetrePrincipale.connection.createStatement().execute("update APPLICATION set SCOREOBTENU="+scoretotal+" where IDAPPLICATION="+idapp);
		else if(!admise) FenetrePrincipale.connection.createStatement().execute("update APPLICATION set SCOREOBTENU="+scoretotal+",ETAT='Rejeté' where IDAPPLICATION="+idapp);
		//System.out.println(""+scoretotal);
		}
		
		catch(Exception exc){
			System.out.println(exc.getMessage());
		}
	
    }
	
	
}

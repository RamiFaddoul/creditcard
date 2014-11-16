import java.util.HashMap;
import java.util.Vector;


public enum EtatCivil {
 MARIE("Marié(e)"),
DIVORCE("Divorcé(e)"),
CELIBATAIRE("Célibataire"),
VEUF("Veuf(ve)");
 
 private EtatCivil(final String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}

 enum NiveauEducation {
	 PRIM("Primaire"), SEC("Secondaire"), TEC("Technicien"), DIP("Diplômé"), POST_DIP("Poster diplômé");
	 
	 private NiveauEducation(final String text) {
	        this.text = text;
	    }

	    private final String text;

	    @Override
	    public String toString() {
	        return text;
	    }
	}

enum EtatHabitat {
	PROPRE("Propre Domicile"),
	PARENT("Avec les parents"),
	LOUER("Louer"),
	AUTRES("Autres");
	 
	 private EtatHabitat(final String text) {
	        this.text = text;
	    }

	    private final String text;

	    @Override
	    public String toString() {
	        return text;
	    }
}
enum EtatEmploi {
	INDEPENDANT("Indépendant"),ENTERPRISE("Dans une entreprise"),RETRAITE("Retraité"),ETUDAINT("Étudiant"),SEC_PUBLIC("Secteur publique"),ARME("Armée libanaise"),CHOMAGE("Au chômage");
	 
	 private EtatEmploi(final String text) {
	        this.text = text;
	    }

	    private final String text;

	    @Override
	    public String toString() {
	        return text;
	    }
}

enum Parametres {
	// info personnelles 
	AGE("Age","nombre"),		
	ETAT_CIVIL("Etat civil","choix"),
	NATIONALITE("Nationalité","string"),
	NOMBRE_DEPENDANTS("Nombre de dépendants","nombre"), 
	NIVEAU_EDUCATION("Niveau Education","choix"), 
	ETAT_ADRESSE("Etat Adresse","choix"), 
	// emploi
	DUREE_EMPLOI("Durée d'emploi","nombre"),
	PROFFESSION("Proffession","string"),
	SALAIRE_TOTAL("Salaire Total","nombre"),	
	SALAIRE_PRINCIPAL("Salaire Principal","nombre"),
	//revenu et engagement
	LIMITE_DEPENSE("Limite de dépenses","nombre"),
	REVENU_FIXE_TOTAL("Revenu Fixe Total","nombre"),
	REVENU_VARIABLE_TOTAL("Revenu Variable Total","nombre"),
	VALEUR_ENGAGEMENT("Valeur Totale des Engagements","nombre"),	
	PAYEMENT_MENSUEL("Payements Mensuels Totales","nombre");	
	
	 private final String type;
	 private final String text;
	 private Parametres(final String text,final String type) {
	        this.text = text;
	        this.type=type;
	    }

	  
        public String getCol(){
        	String s="";
        	if(this==AGE){
        		s="AGE";
        	}else if(this==	ETAT_CIVIL){
        		s="ETATCIVIL";
        	}else if(this==NATIONALITE){
        		s="NATIONALITE";
        	}else if(this==NOMBRE_DEPENDANTS){
        		s="NOMBREDEPENDANTS";
        	}else if(this==NIVEAU_EDUCATION){
        		s="NIVEAUEDUCATION";
        	}else if(this==ETAT_ADRESSE){
        		s="ETATHABITAT";
        	}else if(this==DUREE_EMPLOI){
        		s="duree";
        	}else if(this==PROFFESSION){
        		s="PROFESSION";
        	}else if(this==SALAIRE_TOTAL){
        		s="SALAIREMENSUELFIXE";
        	}else if(this==SALAIRE_PRINCIPAL){
        		s="SALAIREMENSUELVARIABLE";
        	}else if(this==LIMITE_DEPENSE){
        		s="LIMITEDEPENSE";
        	}else if(this==REVENU_FIXE_TOTAL){
        		s="SMF";
        	}else if(this==REVENU_VARIABLE_TOTAL){
        		s="SMV";
        	}else if(this==VALEUR_ENGAGEMENT){
        		s="SV";
        	}else if(this==PAYEMENT_MENSUEL){
        		s="SPM";
        	}
	
        	return s;
        }
        public int getTab(){
        	int r=0;
        	
        	if(this==AGE){
        		r=1;
        	}else if(this==	ETAT_CIVIL){
        		r=1;
        	}else if(this==NATIONALITE){
        		r=1;
        	}else if(this==NOMBRE_DEPENDANTS){
        		r=1;
        	}else if(this==NIVEAU_EDUCATION){
        		r=1;
        	}else if(this==ETAT_ADRESSE){
        		r=1;
        	}else if(this==DUREE_EMPLOI){
        		r=2;
        	}else if(this==PROFFESSION){
        		r=2;
        	}else if(this==SALAIRE_TOTAL){
        		r=2;
        	}else if(this==SALAIRE_PRINCIPAL){
        		r=2;
        	}else if(this==LIMITE_DEPENSE){
        		r=3;
        	}else if(this==REVENU_FIXE_TOTAL){
        		r=3;
        	}else if(this==REVENU_VARIABLE_TOTAL){
        		r=3;
        	}else if(this==VALEUR_ENGAGEMENT){
        		r=4;
        	}else if(this==PAYEMENT_MENSUEL){
        		r=4;
        	}
	
        	return r;
        }
	    @Override
	    public String toString() {
	        return text;
	    }
	    public String getType(){
	    	return type;
	    }
}

 enum Critere{
	ID("ID","string"),AGE("Age","nombre"),NOM("Nom","string"),PRENOM("Prénom","string"),SEXE("Sexe","choix"),ETAT_CIVIL("Etat civil","choix"),
	NATIONALITE("Nationalité","string"),NOMBRE_DEPENDANT("Nombre de dépendants","nombre"),
	NIVEAU_EDUCATION ("Niveau d'education","choix"),
	ETAT_HABITAT("Etat d'habitat","choix"),VILLE("Ville","string"),CAZA("Caza","string"),PAYS("Pays","string"),
	ETAT_EMPLOI ("Etat d'emploi","choix"), DUREE_EMBAUCHE("Durée d'embauchement","nombre"),
	TITRE("Titre","string"),SALAIRE_FIXE("Salaire","nombre"),SALAIRE_TOTAL("Salaire Total","nombre"),
	PRODUIT("Produit","string"),ID_COMPTE("Numero de compte","string");	
	
	  
	   
	 private Critere(final String text,final String type) {
	        this.text = text;
	        this.type= type;
	    }

	 private final String text;
	 private final String type;
       
	    @Override
	    public String toString() {
	        return text;
	    }
	    public String getType(){
	    	return type;
	    }
	    
	    public String getQuery(){
	    	String s="";
	    	if(this==ID){
	    		s="client.IDclient";
	    	}
	    	else if(this==AGE){
	    		s="round((current_date-informationspersonelles.datenaissance)/365)";
	    	}
             else if(this==NOM){
	    		s="informationspersonelles.nom";
	    	}else if(this==PRENOM){
	    		s="informationspersonelles.prenom";
	    	}else if(this==SEXE){
	    		s="informationspersonelles.sexe";
	    	}else if(this==ETAT_CIVIL){
	    		s="informationspersonelles.etatcivil";
	    	}else if(this==NATIONALITE){
	    		s="informationspersonelles.NATIONALITE";
	    	}else if(this==NOMBRE_DEPENDANT){
	    		s="informationspersonelles.NOMBREDEPENDANTS";
	    	}else if(this==NIVEAU_EDUCATION){
	    		s="informationspersonelles.NIVEAUEDUCATION";
	    	}else if(this==ETAT_HABITAT){
	    		s="addresse.ETATHABITAT";
	    	}else if(this==VILLE){
	    		s="addresse.VILLE";
	    	}else if(this==CAZA){
	    		s="addresse.CAZA";
	    	}else if(this==PAYS){
	    		s="addresse.PAYS";
	    	}else if(this==ETAT_EMPLOI){
	    		s="INFORMATIONSEMPLOI.ETATEMPLOI";
	    	}else if(this==DUREE_EMBAUCHE){
	    		s="round((current_date-DATEEMBAUCHEMENT)/365)";
            }else if(this==TITRE){
            	s="INFORMATIONSEMPLOI.TITRE";
            }else if(this==SALAIRE_FIXE){
            	s="INFORMATIONSFINANCIERES.SALAIREMENSUELFIXE";
            }else if(this==SALAIRE_TOTAL){
            	s="(INFORMATIONSFINANCIERES.SALAIREMENSUELFIXE+INFORMATIONSFINANCIERES.SALAIREMENSUELVARIABLE)";
            }else if(this==PRODUIT){
            	s="PRODUIT.NOMPRODUIT";
            }else if(this==ID_COMPTE){
            	s="COMPTE.NUMEROCOMPTE";
            }
	    	return s;
	    }
	    
}
 
 enum AppSearch{
	 PRODUIT("Produit","string"),IDCLIENT("ID Client","string"),SCORE("Score Obtenu","nombre"),
	 PRENOM("Prénom","string"),NOM("Nom","string"),ETAT("Etat","string"),LIMITE("Limite de dépenses","nombre"),
	 DATE("Date de Remplissage","nombre"),VERSION("Version de scoring","string"),PROFFESSOIN("Proffession","string"),NATIONALITE("Nationalité","string");
	 
	 private AppSearch(final String text,final String type) {
	        this.text = text;
	        this.type= type;
	    }

	 private final String text;
	 private final String type;
    
	    @Override
	    public String toString() {
	        return text;
	    }
	    public String getType(){
	    	return type;
	    }
	    
	    public String getQuery(){
	    	String s="";
	    	if(this==PRODUIT){
                   	   s="NOMPRODUIT";		
	    	}else if(this==IDCLIENT){
	    		 s="IDCLIENT";
	    	}else if(this==SCORE){
	    		s="SCOREOBTENU";	
	    	}else if(this==PRENOM){
	    		s="PRENOM";	
	    	}else if(this==NOM){
	    		s="NOM";	
	    	}else if(this==ETAT){
	    		s="ETAT";	
	    	}else if(this==LIMITE){
	    		s="LIMITEDEPENSE";	
	    	}else if(this==DATE){
	    		s="DATEREMPLISSAGE";	
	    	}else if(this==VERSION){
	    		s="VERSION";
	    	}else if(this==NATIONALITE){
	    		s="NOMPRODUIT";	
	    	}else if(this==PROFFESSOIN){
	    		s="PROFESSION";	
	    	}
	        return s;
	    }
	 
 }
 
 enum GroupBy{
	AGE("Age","nombre"),SEXE("Sexe","choix"),ETAT_CIVIL("Etat civil","choix"),
		NATIONALITE("Nationalité","string"),NOMBRE_DEPENDANT("Nombre de dépendants","nombre"),
		NIVEAU_EDUCATION ("Niveau d'education","choix"),
		ETAT_HABITAT("Etat d'habitat","choix"),VILLE("Ville","string"),CAZA("Caza","string"),PAYS("Pays","string"),
		ETAT_EMPLOI ("Etat d'emploi","choix"), DUREE_EMBAUCHE("Durée d'embauchement","nombre"),
		SALAIRE_FIXE("Salaire","nombre"),SALAIRE_TOTAL("Salaire Total","nombre"),
		PRODUIT("Produit","string");	
		
		  
		   
		 private GroupBy(final String text,final String type) {
		        this.text = text;
		        this.type= type;
		    }

		 private final String text;
		 private final String type;
	       
		    @Override
		    public String toString() {
		        return text;
		    }
		    public String getType(){
		    	return type;
		    }
		    
		    public String getQuery(){
		    	String s="";
		    
		    	 if(this==AGE){
		    		s="CONCAT( round(round((current_date-DATENAISSANCE)/365)/10)*10   ,CONCAT('-', round(round((current_date-DATENAISSANCE)/365)/10)*10 +10 ) )";
		    	}
	           else if(this==SEXE){
		    		s="informationspersonelles.sexe";
		    	}else if(this==ETAT_CIVIL){
		    		s="informationspersonelles.etatcivil";
		    	}else if(this==NATIONALITE){
		    		s="informationspersonelles.NATIONALITE";
		    	}else if(this==NOMBRE_DEPENDANT){
		    		s="informationspersonelles.NOMBREDEPENDANTS";
		    	}else if(this==NIVEAU_EDUCATION){
		    		s="informationspersonelles.NIVEAUEDUCATION";
		    	}else if(this==ETAT_HABITAT){
		    		s="addresse.ETATHABITAT";
		    	}else if(this==VILLE){
		    		s="addresse.VILLE";
		    	}else if(this==CAZA){
		    		s="addresse.CAZA";
		    	}else if(this==PAYS){
		    		s="addresse.PAYS";
		    	}else if(this==ETAT_EMPLOI){
		    		s="INFORMATIONSEMPLOI.ETATEMPLOI";
		    	}else if(this==DUREE_EMBAUCHE){
		    		s="CONCAT( round(round((current_date-DATEEMBAUCHEMENT)/365)/10)*10   ,CONCAT('-', round(round((current_date-DATEEMBAUCHEMENT)/365)/10)*10 +10 ) )";
		    
		    	}else if(this==SALAIRE_FIXE){
		    		
		    		s="CONCAT(round((INFORMATIONSFINANCIERES.SALAIREMENSUELFIXE)/500)*500,CONCAT('-',round((INFORMATIONSFINANCIERES.SALAIREMENSUELFIXE)/500)*500+500))";
	            }else if(this==SALAIRE_TOTAL){
		    		s="CONCAT(round((INFORMATIONSFINANCIERES.SALAIREMENSUELFIXE+INFORMATIONSFINANCIERES.SALAIREMENSUELVARIABLE)/500)*500,CONCAT('-',round((INFORMATIONSFINANCIERES.SALAIREMENSUELFIXE+INFORMATIONSFINANCIERES.SALAIREMENSUELVARIABLE)/500)*500+500))";
		    		
	            }else if(this==PRODUIT){
	            	s="PRODUIT.NOMPRODUIT";
	            }
		    	return s;
		    }
 }


